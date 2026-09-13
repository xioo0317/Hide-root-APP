# Hide-root-APP

一键隐藏 Root 的 Android 应用，目标支持 **Magisk / KernelSU / APatch** 各分支环境。

> 当前版本：`v0.1.0` —— 已搭好 **Kotlin + Jetpack Compose** 可编译工程骨架，root 检测/隐藏逻辑为占位实现，后续逐步补全。

## 技术栈

- 语言：Kotlin
- UI：Jetpack Compose（Material 3）
- 构建：Gradle（Kotlin DSL）+ AGP 8.5.2 + Gradle 8.9
- compileSdk / targetSdk：34，minSdk：26（Android 8.0）

## 目录结构

```
Hide-root-APP/
├── settings.gradle.kts            # 模块与仓库配置
├── build.gradle.kts               # 顶层插件版本声明
├── gradle.properties              # Gradle / AndroidX 配置
├── gradle/wrapper/                # Gradle Wrapper 版本声明
└── app/
    ├── build.gradle.kts           # 模块依赖与构建配置
    ├── proguard-rules.pro
    └── src/main/
        ├── AndroidManifest.xml
        ├── java/com/hideroot/app/
        │   ├── HideRootApp.kt          # Application 入口
        │   ├── MainActivity.kt         # 唯一 Activity，承载 Compose
        │   ├── root/                   # root 检测分层（后续扩展核心）
        │   │   ├── RootDetector.kt     # 检测器接口 + 占位实现
        │   │   └── RootStatus.kt       # 检测结果模型
        │   └── ui/
        │       ├── screen/HomeScreen.kt
        │       └── theme/              # Compose 主题（Color/Theme/Type）
        └── res/                        # 字符串、主题、自适应图标
```

## 快速开始

1. 用 **Android Studio（Koala 2024.1 或更新版本）** 打开本仓库根目录。
2. 等待 Gradle Sync 完成（首次会自动下载依赖与 Gradle 8.9）。
3. 连接设备或启动模拟器，点击 Run，即可看到展示 root 检测状态的首屏。

> 若命令行中没有 wrapper 脚本，可在 Android Studio 内执行一次 `gradle wrapper`，或使用本机已安装的 Gradle 8.9 运行 `gradle assembleDebug`。

## 路线图（TODO）

- [ ] 完善 Root 检测：`su` 提权验证、Magisk / KernelSU / APatch 特征识别
- [ ] 实现「一键隐藏」核心逻辑（按目标方案分流）
- [ ] 检测结果缓存与手动刷新
- [ ] 隐藏目标应用选择 / 白名单
- [ ] GitHub Actions 自动构建并产出 APK
