package com.hihonor.android.security.identity;

import android.content.Context;
import android.os.CancellationSignal;
import java.util.Optional;

/* loaded from: android-magic.jar:com/hihonor/android/security/identity/FaceRecognition.class */
public class FaceRecognition {
    public static final int FACE_ACQUIRED_INSUFFICIENT = 1;
    public static final int FACE_ACQUIRED_NOT_DETECTED = 12;
    public static final int FACE_ACQUIRED_POOR_GAZE = 11;
    public static final int FACE_ACQUIRED_TOO_CLOSE = 4;
    public static final int FACE_ACQUIRED_TOO_FAR = 5;
    public static final int FACE_ACQUIRED_TOO_HIGH = 6;
    public static final int FACE_ACQUIRED_TOO_LEFT = 9;
    public static final int FACE_ACQUIRED_TOO_LOW = 7;
    public static final int FACE_ACQUIRED_TOO_MUCH_MOTION = 10;
    public static final int FACE_ACQUIRED_TOO_RIGHT = 8;
    public static final int FACE_ERROR_CANCELED = 5;
    public static final int FACE_ERROR_LOCKOUT = 7;
    public static final int FACE_ERROR_NOT_ENROLLED = 11;
    public static final int FACE_ERROR_SERVICE_UNAVAILABLE = 1;
    public static final int FACE_ERROR_TIMEOUT = 3;
    public static final int FACE_ERROR_UNABLE_TO_PROCESS = 2;

    FaceRecognition() {
        throw new RuntimeException("Stub!");
    }

    public static FaceRecognition getFaceRecognition(Context context) {
        throw new RuntimeException("Stub!");
    }

    public boolean hasEnrolledTemplates() {
        throw new RuntimeException("Stub!");
    }

    public Optional<FaceRecognitionCapability> getFaceRecognitionCapability() {
        throw new RuntimeException("Stub!");
    }

    public void authenticate(CancellationSignal cancel, AuthenticationCallback callback) {
        throw new RuntimeException("Stub!");
    }

    /* loaded from: android-magic.jar:com/hihonor/android/security/identity/FaceRecognition$AuthenticationCallback.class */
    public static abstract class AuthenticationCallback {
        public AuthenticationCallback() {
            throw new RuntimeException("Stub!");
        }

        public void onAuthenticationError(int errorCode, CharSequence errString) {
            throw new RuntimeException("Stub!");
        }

        public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
            throw new RuntimeException("Stub!");
        }

        public void onAuthenticationSucceeded() {
            throw new RuntimeException("Stub!");
        }

        public void onAuthenticationFailed() {
            throw new RuntimeException("Stub!");
        }
    }

    /* loaded from: android-magic.jar:com/hihonor/android/security/identity/FaceRecognition$FaceRecognitionCapability.class */
    public static final class FaceRecognitionCapability {
        FaceRecognitionCapability() {
            throw new RuntimeException("Stub!");
        }

        public boolean isSupported() {
            throw new RuntimeException("Stub!");
        }

        public boolean is3D() {
            throw new RuntimeException("Stub!");
        }
    }
}