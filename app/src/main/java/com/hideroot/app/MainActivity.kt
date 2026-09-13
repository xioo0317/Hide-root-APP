package com.hideroot.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.hideroot.app.root.DefaultRootDetector
import com.hideroot.app.ui.screen.HomeScreen
import com.hideroot.app.ui.theme.HideRootTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 开启 edge-to-edge：状态栏/导航栏透明，内容铺到系统栏后方，
        // 状态栏区域与界面背景同色，并按明暗主题自动切换状态栏图标颜色。
        enableEdgeToEdge()

        // 把状态栏背景设为透明，并让状态栏图标颜色自动跟随浅色/深色主题
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView)
            .isAppearanceLightStatusBars = true

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
