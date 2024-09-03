# Identity Kit示例代码（Android）

[![Apache-2.0](https://img.shields.io/badge/license-Apache-blue)](http://www.apache.org/licenses/LICENSE-2.0)
[![Open Source Love](https://img.shields.io/static/v1?label=Open%20Source&message=%E2%9D%A4%EF%B8%8F&color=green)]()
[![Java Language](https://img.shields.io/badge/language-java-green.svg)](https://www.java.com/en/)

[English](README.md) | 中文

## 目录

- [Identity Kit示例代码（Android）](#identity kit示例代码android)
  - [目录](#目录)
  - [介绍](#介绍)
  - [环境要求](#环境要求)
  - [硬件要求](#硬件要求)
  - [准备工作](#准备工作)
  - [安装](#安装)
  - [技术支持](#技术支持)
  - [许可证](#许可证)

## 介绍

在这个示例代码中，你将使用创建的演示项目来调用Identity Kit的 API。通过演示项目，你将：

1. 学习如何在你的应用中使用人脸识别。
2. 学习如何在你的应用中使用儿童人脸识别。

更多信息，请参考
[服务介绍](https://developer.honor.com/cn/docs/11020/guides/introduction)。

## 环境要求

推荐使用 Android targetSdkVersion 29 或更高版本，以及 JDK 1.8.211 或更高版本。

## 硬件要求

一台运行 Windows 10 或 Windows 7 的电脑（台式机或笔记本）
一部带有 USB 数据线的荣耀手机，用于调试

## 准备工作

1.	注册荣耀账号，成为荣耀开发者。
2.	创建应用，启动接口。
3.	构建本示例代码，需要先把它导入安卓集成开发环境（Android Studio的版本为2021.2.1及以上）。然后从[荣耀开发者服务平台](https://developer.hihonor.com/)下载应用的mcs-services.json文件，并添加到对应示例代码的app目录下（java语言为/javaapp/，kotlin语言为/KotlinApp/）。另外，需要生成签名证书指纹并将证书文件添加到项目中，然后将配置添加到build.gradle。详细信息，请参见[集成指南](https://developer.honor.com/cn/docs/11020/guides/app-registration)集成准备。

## 安装

* 方法1：在Android Studio中进行代码的编译构建。构建APK完成后，将APK安装到手机上，并调试APK。
* 方法2：在Android Studio中生成APK。使用ADB（Android Debug Bridge）工具通过adb install {YourPath/YourApp.apk} 命令将APK安装到手机，并调试APK。

## 技术支持

如果您对该示例代码还处于评估阶段，可在[荣耀开发者社区](https://developer.hihonor.com/cn/forum/?navation=dh11614886576872095748%2F1)获取关于Identity Kit的最新讯息，并与其他开发者交流见解。

如果您对使用该示例代码有疑问，请尝试：
- 开发过程遇到问题上[Stack Overflow](https://stackoverflow.com/questions/tagged/honor-developer-services?tab=Votes)，在`honor-developer-services`标签下提问，有荣耀研发专家在线一对一解决您的问题。

## 许可证

示例代码根据 [Apache 许可证 2.0](http://www.apache.org/licenses/LICENSE-2.0) 许可。
