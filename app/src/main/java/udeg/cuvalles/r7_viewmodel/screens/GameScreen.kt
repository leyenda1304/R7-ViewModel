
package udeg.cuvalles.r7_viewmodel.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import udeg.cuvalles.r7_viewmodel.GameViewModel

@Composable
fun GameScreen(
    playerName: String,
    onExit: () -> Unit,
    gameViewModel: GameViewModel = viewModel()
) {

    val gameUiState by gameViewModel.uiState.collectAsState()

    if (gameUiState.isGameOver) {

        AlertDialog(
            onDismissRequest = {},
            title = {
                Text("¡Juego terminado!")
            },
            text = {
                Text(
                    "$playerName, tu puntuación final es: " +
                            "${gameUiState.score}"
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        gameViewModel.resetGame()
                    }
                ) {
                    Text("Jugar otra vez")
                }
            },
            dismissButton = {
                TextButton(onClick = onExit) {
                    Text("Salir")
                }
            }
        )

    } else {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "¡Hola, $playerName!",
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Palabra ${gameUiState.currentWordCount} de 10"
            )

            Text(
                text = "Puntos: ${gameUiState.score}"
            )

            Spacer(modifier = Modifier.height(35.dp))

            Text(
                text = gameUiState.currentScrambledWord,
                fontSize = 35.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = gameUiState.userGuess,
                onValueChange = {
                    gameViewModel.updateUserGuess(it)
                },
                label = {
                    Text("Escribe tu respuesta")
                },
                singleLine = true,
                isError = gameUiState.isGuessedWordWrong
            )

            if (gameUiState.isGuessedWordWrong) {
                Text(
                    text = "Respuesta incorrecta. Inténtalo nuevamente.",
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                horizontalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {

                Button(
                    onClick = {
                        gameViewModel.checkUserGuess()
                    }
                ) {
                    Text("Comprobar")
                }

                OutlinedButton(
                    onClick = {
                        gameViewModel.skipWord()
                    }
                ) {
                    Text("Siguiente")
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            TextButton(onClick = onExit) {
                Text("Volver al inicio")
            }
        }
    }
}
