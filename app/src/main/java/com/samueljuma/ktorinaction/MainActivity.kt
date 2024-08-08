package com.samueljuma.ktorinaction

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.samueljuma.ktorinaction.screens.CommentsListScreen
import com.samueljuma.ktorinaction.ui.theme.KtorInActionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KtorInActionTheme {
                CommentsListScreen()
            }
        }
    }
}
