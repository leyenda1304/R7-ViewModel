
package udeg.cuvalles.r7_viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import udeg.cuvalles.r7_viewmodel.ui.theme.R7ViewModelTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            R7ViewModelTheme {

                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AppNavigation()
                }

            }
        }
    }
}
