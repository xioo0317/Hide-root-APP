package com.hideroot.app.root

import java.io.File

/**
 * Root 环境检测器抽象。
 *
 * 业务层只依赖该接口，方便后续替换为更完整的实现
 * （Magisk / KernelSU / APatch 特征检测、挂载点检查等）。
 */
interface RootDetector {
    fun detect(): RootStatus
}

/**
 * 占位实现：仅做最基础的 su 路径探测，保证工程可编译、可运行。
 *
 * TODO: 扩展检测项
 *  - 执行 `su -c id` 验证是否真正可提权
 *  - 检测 Magisk 的 /sbin/.magisk、magisk 二进制与包名
 *  - 检测 KernelSU 的 /data/adb/ksu、ksud
 *  - 检测 APatch 的 /data/adb/ap、apd
 *  - 检查常见挂载点与系统属性
 */
class DefaultRootDetector : RootDetector {

    private val suCandidates = listOf(
        "/system/bin/su",
        "/system/xbin/su",
        "/sbin/su",
        "/system/sd/xbin/su",
        "/data/local/xbin/su",
        "/data/local/bin/su",
        "/vendor/bin/su",
        "/su/bin/su"
    )

    override fun detect(): RootStatus {
        val suPath = suCandidates.firstOrNull { File(it).exists() }
        val rooted = suPath != null

        return RootStatus(
            isRooted = rooted,
            solutions = if (rooted) listOf(RootSolution.UNKNOWN) else emptyList(),
            suPath = suPath,
            details = if (rooted) {
                "检测到 su 可执行文件，具体方案待进一步识别"
            } else {
                "未发现 su（当前为骨架实现，尚未检测具体方案）"
            }
        )
    }
}
