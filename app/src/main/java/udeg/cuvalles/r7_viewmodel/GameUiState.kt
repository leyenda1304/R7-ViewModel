
package udeg.cuvalles.r7_viewmodel

data class GameUiState(
    val currentScrambledWord: String = "",
    val currentWordCount: Int = 1,
    val score: Int = 0,
    val userGuess: String = "",
    val isGuessedWordWrong: Boolean = false,
    val isGameOver: Boolean = false
)
