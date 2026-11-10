
package udeg.cuvalles.r7_viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())

    val uiState: StateFlow<GameUiState> =
        _uiState.asStateFlow()

    private lateinit var currentWord: String

    private var usedWords: MutableSet<String> = mutableSetOf()

    init {
        resetGame()
    }

    private fun pickRandomWordAndShuffle(): String {

        currentWord = allWords.filterNot {
            usedWords.contains(it)
        }.random()

        usedWords.add(currentWord)

        return shuffleCurrentWord(currentWord)
    }

    private fun shuffleCurrentWord(word: String): String {

        var shuffledWord: String

        do {
            shuffledWord = word.toList()
                .shuffled()
                .joinToString("")
        } while (shuffledWord == word)

        return shuffledWord
    }

    fun updateUserGuess(guessedWord: String) {

        _uiState.update {
            it.copy(
                userGuess = guessedWord,
                isGuessedWordWrong = false
            )
        }
    }

    fun checkUserGuess() {

        val userGuess = _uiState.value.userGuess

        if (userGuess.equals(currentWord, ignoreCase = true)) {

            val updatedScore =
                _uiState.value.score + SCORE_INCREASE

            updateGameState(updatedScore)

        } else {

            _uiState.update {
                it.copy(isGuessedWordWrong = true)
            }
        }
    }

    fun skipWord() {
        updateGameState(_uiState.value.score)
    }

    private fun updateGameState(updatedScore: Int) {

        if (usedWords.size == MAX_NO_OF_WORDS) {

            _uiState.update {
                it.copy(
                    score = updatedScore,
                    userGuess = "",
                    isGuessedWordWrong = false,
                    isGameOver = true
                )
            }

        } else {

            _uiState.update {
                it.copy(
                    currentScrambledWord =
                        pickRandomWordAndShuffle(),
                    currentWordCount =
                        it.currentWordCount + 1,
                    score = updatedScore,
                    userGuess = "",
                    isGuessedWordWrong = false
                )
            }
        }
    }

    fun resetGame() {

        usedWords.clear()

        _uiState.value = GameUiState(
            currentScrambledWord =
                pickRandomWordAndShuffle()
        )
    }
}
