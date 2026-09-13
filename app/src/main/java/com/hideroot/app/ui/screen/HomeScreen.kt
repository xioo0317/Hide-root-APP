package com.hideroot.app.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hideroot.app.root.RootStatus

/**
 * 首屏：展示当前 Root 检测状态。
 * “一键隐藏”按钮先占位禁用，待核心逻辑实现后启用。
 */
@Composable
fun HomeScreen(status: RootStatus) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            // edge-to-edge 下避让状态栏/导航栏，保证内容不被系统栏遮挡
            .safeDrawingPadding()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Hide Root",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = if (status.isRooted) "检测到 Root 环境" else "未检测到 Root",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = status.details,
            style = MaterialTheme.typography.bodyMedium
        )
        status.suPath?.let { path ->
            Text(
                text = "su 路径：$path",
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { /* TODO: 接入一键隐藏逻辑 */ },
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("一键隐藏（待实现）")
        }
    }
}
