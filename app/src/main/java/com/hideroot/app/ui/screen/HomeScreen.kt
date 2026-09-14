package com.hideroot.app.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hideroot.app.api.ApiClient
import com.hideroot.app.root.RootStatus
import kotlinx.coroutines.launch

/**
 * 首屏：展示当前 Root 检测状态 + API 测试功能
 */
@Composable
fun HomeScreen(status: RootStatus) {
    val scope = androidx.compose.runtime.rememberCoroutineScope()
    val apiResult = remember { mutableStateOf<String>("") }
    val isServiceRunning = remember { mutableStateOf(false) }

    // Check service status on launch
    LaunchedEffect(Unit) {
        isServiceRunning.value = ApiClient.isServiceRunning()
        Log.d("HomeScreen", "Service running: ${isServiceRunning.value}")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
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

        Spacer(modifier = Modifier.height(16.dp))

        // API Test Section
        Text(
            text = "API 测试",
            style = MaterialTheme.typography.titleLarge
        )
        
        Text(
            text = if (isServiceRunning.value) "服务状态：运行中 ✓" else "服务状态：离线 ",
            style = MaterialTheme.typography.bodyMedium,
            color = if (isServiceRunning.value) 
                MaterialTheme.colorScheme.primary 
            else 
                MaterialTheme.colorScheme.error
        )

        Spacer(modifier = Modifier.height(8.dp))

        // API Action Buttons
        val actions = listOf("list_items", "create_item", "update_item", "delete_item")
        
        actions.forEach { action ->
            Button(
                onClick = {
                    scope.launch {
                        apiResult.value = "执行中..."
                        val result = ApiClient.execute(action)
                        apiResult.value = result
                        Log.d("HomeScreen", "$action result: $result")
                    }
                },
                enabled = isServiceRunning.value,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("执行 $action")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Result Display
        if (apiResult.value.isNotEmpty()) {
            Text(
                text = "执行结果：",
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = apiResult.value,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
