package com.github.mhelmi.thmanyahtask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.github.mhelmi.thmanyahtask.ui.MainNavHost
import com.github.mhelmi.thmanyahtask.ui.theme.ThmanyahTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      ThmanyahTaskTheme {
        MainNavHost()
      }
    }
  }
}