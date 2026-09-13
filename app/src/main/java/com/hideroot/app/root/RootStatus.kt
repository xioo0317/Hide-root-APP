package com.hideroot.app.root

/**
 * 设备上可能存在的 Root 方案。
 */
enum class RootSolution(val displayName: String) {
    MAGISK("Magisk"),
    KERNEL_SU("KernelSU"),
    APATCH("APatch"),
    UNKNOWN("未知方案"),
    NONE("未检测到")
}

/**
 * 一次 Root 环境检测的结果。
 *
 * @param isRooted   是否判定为已 Root
 * @param solutions  识别出的具体方案列表
 * @param suPath     命中的 su 可执行文件路径（如有）
 * @param details    供界面展示与排查的说明文本
 */
data class RootStatus(
    val isRooted: Boolean,
    val solutions: List<RootSolution>,
    val suPath: String?,
    val details: String
)
