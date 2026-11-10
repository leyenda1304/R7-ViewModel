
package udeg.cuvalles.r7_viewmodel.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    onStartGame: (String) -> Unit
) {
    var playerName by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "UNSCRAMBLE",
            fontSize = 32.sp,
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "¡Bienvenido al juego de palabras!",
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Ordena las letras y descubre la palabra."
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = playerName,
            onValueChange = {
                playerName = it
            },
            label = {
                Text("Nombre del jugador")
            },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                onStartGame(playerName.trim())
            },
            enabled = playerName.isNotBlank()
        ) {
            Text("Comenzar juego")
        }
    }
}
