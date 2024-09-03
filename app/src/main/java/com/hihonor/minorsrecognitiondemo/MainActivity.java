/*
 * Copyright (c) Honor Device Co., Ltd. 2024-2024. All rights reserved.
 */

package com.hihonor.minorsrecognitiondemo;

import static com.hihonor.android.security.identity.FaceRecognition.getFaceRecognition;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.os.CancellationSignal;
import androidx.annotation.RequiresApi;

import com.hihonor.android.security.identity.MinorsRecognition;
import com.hihonor.android.security.identity.FaceRecognition;

/**
 * Class of main activity.
 *
 * @since 2024-08-01
 */
public class MainActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "IdentityKitDemo";

    private static final String FACE_TAG = "FaceRecognition";

    private static final int MINORS_SUCCESS = 0;

    private static int SDK_INT = 31;

    private static int MAGIC_SDK_INT = 35;

    private static Context contextMR = null;

    private String packageName = null;

    private MinorsRecognition mMinorsRecognition = null;

    private boolean mIsSupported = false;

    private boolean mIs3D = false;

    private CancellationSignal mSignal = null;

    private FaceRecognition mFaceRecognition = null;

    private MinorsRecognition.MinorsRecognitionCallback callbackNaive
            = new MinorsRecognition.MinorsRecognitionCallback() {
        @Override
        public void onResult(int type, int result, float confidence) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "result: " + confidence, Toast.LENGTH_SHORT).show();
                }
            });
            Log.i(TAG, "result: " + confidence);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contextMR = this;
        packageName = getAppName(contextMR);
        mMinorsRecognition = new MinorsRecognition();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.in_getMinorsRecognitionApiTag:
                getMinorsRecognitionApiTag();
                break;
            case R.id.in_hasMinorsRecognition:
                hasMinorsRecognition();
                break;
            case R.id.in_enableAndGetResult1000:
                getRecognitionResult(1000);
                break;
            case R.id.in_enableAndGetResult1500:
                getRecognitionResult(1500);
                break;
            case R.id.in_enableAndGetResult2000:
                getRecognitionResult(2000);
                break;
            case R.id.in_disableRecognition:
                disableRecognition();
                break;
            case R.id.id_FaceRecognitionInit:
                initFaceRecognition();
                break;
            case R.id.id_FaceRecognition_DoAuth:
                doAuthFaceRecognition();
                break;
            case R.id.id_FaceRecognition_CancelAuth:
                doCancel();
                break;
            default:
                break;
        }
    }

    private void initFaceRecognition() {
        /* 验证设备品牌、版本、magic sdk版本 */
        if (Build.MANUFACTURER.equalsIgnoreCase("HONOR")
                && (Build.VERSION.SDK_INT >= SDK_INT)
                && (com.hihonor.android.os.Build.VERSION.MAGIC_SDK_INT >= MAGIC_SDK_INT)) {
            /* 通过验证，获取实例，检测设备是否支持人脸识别及模式并记录 */
            mFaceRecognition = getFaceRecognition(getApplicationContext());
            FaceRecognition.FaceRecognitionCapability capability
                    = mFaceRecognition.getFaceRecognitionCapability().orElse(null);
            if (capability != null) {
                mIsSupported = capability.isSupported();
                mIs3D = capability.is3D();
            }
        }
    }

    private void doAuthFaceRecognition() {
        /* 认证前检验是否获得人脸识别实例、设备是否支持人脸识别 */
        if ((mFaceRecognition == null) || !mIsSupported) {
            /* 验证未通过，return，不继续执行函数 */
            return;
        }
        /* 检验设备是否已录入人脸 */
        if (!mFaceRecognition.hasEnrolledTemplates()) {
            /* 检测到设备未录入人脸，不继续执行函数 */
            return;
        }
        /* 人脸识别回调函数初始化 */
        FaceRecognition.AuthenticationCallback callback = new FaceRecognition.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errMsgId, CharSequence errString) {
                /* 人脸识别过程中发生错误回调函数，可以表明本次人脸识别失败 */
                Toast.makeText(MainActivity.this, "Face Recognition err id:" + errMsgId
                        + "MSG:" + errString, Toast.LENGTH_SHORT).show();
                Log.i(FACE_TAG, "Face Recognition err id:" + errMsgId + "MSG:" + errString);
            }

            @Override
            public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                /* 人脸识别过程中提示信息回调函数，返回识别过程中提示信息 */
                Log.i(FACE_TAG, "help id:" + helpMsgId + "MSG:" + helpString);
            }

            @Override
            public void onAuthenticationSucceeded() {
                /* 人脸识别成功回调函数，可以表明本次人脸识别成功 */
                Toast.makeText(MainActivity.this, "Face Recognition suceessfull", Toast.LENGTH_SHORT).show();
                Log.i(FACE_TAG, "Face Recognition Success" );
            }

            @Override
            public void onAuthenticationFailed() {
                /* 人脸识别失败回调函数，可以表明本次人脸识别失败 */
                Toast.makeText(MainActivity.this, "Face Recognition Failed", Toast.LENGTH_SHORT).show();
                Log.i(FACE_TAG, "Face Recognition Failed" );
            }
        };
        /* 人脸识别取消信号初始化，用于取消人脸识别 */
        mSignal = new CancellationSignal();
        mFaceRecognition.authenticate(mSignal, callback);
    }

    private void doCancel() {
        if (mSignal == null) {
            /* 人脸识别取消信号为null，表明人脸识别认证未开启 */
            return;
        }

        if (mSignal.isCanceled()) {
            /* 人脸识别取消信号判断函数，true表明人脸识别认证已经取消 */
            return;
        }
        /* 设置人脸识别取消信号，取消当前人脸识别认证 */
        mSignal.cancel();
    }

    private void getMinorsRecognitionApiTag() {
        int apiTag = mMinorsRecognition.getMinorsRecognitionApiTag();
        Toast.makeText(this, "minors recognition api tag: " + apiTag, Toast.LENGTH_SHORT).show();
        Log.i(TAG, "minors recognition api tag: " + apiTag);
    }

    private void hasMinorsRecognition() {
        boolean support = mMinorsRecognition.hasMinorsRecognition();
        Toast.makeText(this, "whether this supports minors recognition: " + support, Toast.LENGTH_SHORT).show();
        Log.i(TAG, "whether this supports minors recognition: " + support);
    }


    private void getRecognitionResult(int timeout) {
        MinorsRecognition.MinorsRecognitionCallback mMinorsRecognitionCallback
                = new MinorsRecognition.MinorsRecognitionCallback() {
            @Override
            public void onResult(int type, int result, float confidence) {
                if (result != MINORS_SUCCESS) {
                    Log.e(TAG, "getRecognitionResult result error: " + result);
                    if (mMinorsRecognition != null) {
                        mMinorsRecognition.getRecognitionResult(callbackNaive);
                    }
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "result: " + confidence, Toast.LENGTH_SHORT).show();
                        }
                    });
                    Log.i(TAG, "result: " + confidence);
                }
            }
        };

        int result = mMinorsRecognition.enableRecognition(packageName);
        if (result != MINORS_SUCCESS) {
            Toast.makeText(this, "enableRecognition error: " + result, Toast.LENGTH_SHORT).show();
            Log.e(TAG, "enableRecognition error: " + result);
            return;
        }

        result = mMinorsRecognition.setTimeout(timeout);
        if (result != MINORS_SUCCESS) {
            Toast.makeText(this, "setTimeout error: " + result, Toast.LENGTH_SHORT).show();
            Log.e(TAG, "setTimeout error: " + result);
            return;
        }

        result = mMinorsRecognition.getRecognitionResult(mMinorsRecognitionCallback);
        if (result != MINORS_SUCCESS) {
            Toast.makeText(this, "getRecognitionResult error: " + result, Toast.LENGTH_SHORT).show();
            Log.e(TAG, "getRecognitionResult error: " + result);
        }
    }


    private void disableRecognition() {
        if (mMinorsRecognition == null) {
            mMinorsRecognition = new MinorsRecognition();
        }
        int result = mMinorsRecognition.disableRecognition(packageName);
        Toast.makeText(this, "disableRecognition result: " + result, Toast.LENGTH_SHORT).show();
        Log.i(TAG, "disableRecognition result: " + result);
    }

    private static synchronized String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.applicationInfo.packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
}