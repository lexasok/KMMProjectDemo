package club.multilab.music.client.ui

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import club.multilab.music.client.ui.theme.AppTheme

@Composable
fun Material3App(content: @Composable () -> Unit) {
    AppTheme {
        Surface {
            content()
        }
    }
}

