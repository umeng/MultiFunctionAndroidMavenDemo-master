# QWEN.md - 项目指南

## 项目概述

这是一个 **Android** 项目，用于演示如何集成 **[友盟+ (Umeng)](https://www.umeng.com/)** 统计、推送、分享三合一组件化 SDK。

### 主要功能

| 模块 | 说明 |
|------|------|
| **统计 (Analytics)** | 移动应用数据统计与分析 |
| **推送 (Push)** | 消息推送服务 |
| **分享 (Share)** | 社交分享功能，支持 QQ、微信、微博、钉钉、抖音、支付宝、Twitter 等平台 |

### 技术栈

- **构建工具**: Gradle (Android)
- **Android Gradle Plugin**: 4.1.3
- **编译 SDK**: 30 (app), 33 (UMLibrary)
- **最低 SDK**: 21 (Android 5.0)
- **目标 SDK**: 30 (Android 11)
- **Java 版本**: 1.8

### 项目结构

```
MultiFunctionAndroidMavenDemo-master/
├── app/                    # 主应用模块
│   ├── src/main/java/      # Java 源代码
│   ├── src/main/res/       # 资源文件
│   └── libs/               # 本地 JAR 包 (QQ open SDK)
├── UMLibrary/              # 友盟库模块 (聚合分享资源)
├── share/                  # 分享平台资源
│   └── platforms/          # 各平台特定资源
│       ├── bytedance/      # 抖音
│       ├── twitter/        # Twitter
│       └── yixin/          # 易信
└── gradle/                 # Gradle 包装器
```

---

## 构建与运行

### 环境要求

- Android Studio (推荐)
- JDK 1.8+
- Android SDK

### 构建命令

```bash
# 构建 debug 版本
./gradlew assembleDebug

# 构建 release 版本
./gradlew assembleRelease

# 清理构建
./gradlew clean
```

### 运行

1. 在 Android Studio 中打开项目
2. 连接 Android 设备或启动模拟器
3. 运行 `app` 模块

---

## 集成说明

### 友盟 SDK 依赖

项目使用 Maven 依赖方式集成友盟 SDK，主要依赖包括：

```groovy
// 统计 SDK (必选)
implementation 'com.umeng.umsdk:common:+'
implementation 'com.umeng.umsdk:asms:+'

// 推送 SDK (必选)
implementation 'com.umeng.umsdk:push:+'

// 分享 SDK
implementation 'com.umeng.umsdk:share-core:+'
implementation 'com.umeng.umsdk:share-qq:+'
implementation 'com.umeng.umsdk:share-wx:+'
implementation 'com.umeng.umsdk:share-sina:+'
implementation 'com.umeng.umsdk:share-dingding:+'
```

### 混淆配置

Release 构建时需添加以下混淆规则：

```proguard
-keep class com.uc.** {*;}
```

### 重要配置

- **applicationId**: `com.umeng.soexample`
- **QQ App ID**: 在 `app/build.gradle` 的 `manifestPlaceholders` 中配置 (当前为占位符)
- **微信 App ID**: 需要在微信开放平台申请并配置
- **微博 App Key**: 需要在微博开放平台申请并配置

---

## 代码结构

### 核心类

| 类名 | 路径 | 说明 |
|------|------|------|
| `App.java` | `app/src/main/java/com/umeng/soexample/` | Application 类，负责 SDK 初始化 |
| `UmInitConfig.java` | `app/src/main/java/com/umeng/soexample/` | 友盟初始化配置 |
| `HomeActivity.java` | `app/src/main/java/com/umeng/soexample/` | 主界面 |
| `SplashTestActivity.java` | `app/src/main/java/com/umeng/soexample/push/` | 启动页 (含隐私协议) |

### 功能模块

- `analytics/` - 统计功能示例
- `push/` - 推送功能示例
- `share/` - 分享功能示例

---

## 注意事项

1. **版本兼容性**: 统计 SDK 9.3.0 不能和低版本推送、分享 SDK 混用
2. **隐私协议**: 需要在 App 启动时展示用户授权隐私协议
3. **平台配置**: 各分享平台需要在对应开放平台注册应用并获取 App ID
4. **NDK 架构**: 推送 SDK 目前只支持 `armeabi`, `armeabi-v7a`, `arm64-v8a`, `x86`

---

## 参考文档

- [友盟+ 官方文档](https://developer.umeng.com/)
- [友盟统计 SDK 集成指南](https://developer.umeng.com/docs/119267/detail/118588)
- [友盟推送 SDK 集成指南](https://developer.umeng.com/docs/119267/detail/118637)
- [友盟分享 SDK 集成指南](https://developer.umeng.com/docs/119267/detail/118642)