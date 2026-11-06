package com.nao.lazycolumnkeysample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.nao.lazycolumnkeysample.ui.theme.LazyColumnKeySampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LazyColumnKeySampleTheme {
                Scaffold { innerPadding ->
                    LazyColumnKeyScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
