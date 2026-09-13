# 默认 ProGuard 规则。
# release 构建当前未开启混淆，后续如需开启可在此补充 keep 规则。

# 保留 native 方法（为后续 JNI / root 隐藏底层实现预留）
-keepclasseswithmembernames class * {
    native <methods>;
}
