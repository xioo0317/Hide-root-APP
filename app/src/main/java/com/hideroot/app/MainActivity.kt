package com.hideroot.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.hideroot.app.root.DefaultRootDetector
import com.hideroot.app.ui.screen.HomeScreen
import com.hideroot.app.ui.theme.HideRootTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 占位：启动时做一次基础检测，后续替换为后台/可刷新的检测流程
        val rootStatus = DefaultRootDetector().detect()

        setContent {
            HideRootTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(status = rootStatus)
                }
            }
        }
    }
}
