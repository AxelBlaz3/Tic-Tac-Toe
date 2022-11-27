package com.codex.tic_tac_toe.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.codex.tic_tac_toe.ai.TicTacToeAI
import com.codex.tic_tac_toe.ui.event.GameEvent
import com.codex.tic_tac_toe.ui.state.GameState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val ticTacToeAI: TicTacToeAI
) : ViewModel() {

    private var _gameState: MutableState<GameState> = mutableStateOf(GameState())
    val gameState: State<GameState> = _gameState

    fun onEvent(event: GameEvent) {
        when (event) {
            is GameEvent.OnMovePlayed -> {

                if (event.rowIndex == -1 || event.colIndex == -1 || _gameState.value.isGameComplete) {
                    onEvent(GameEvent.GameComplete)
                    return
                }

                if (_gameState.value.board[event.rowIndex][event.colIndex] != '-') {
                    return
                }

                val newBoard = _gameState.value.board
                newBoard[event.rowIndex][event.colIndex] = if (_gameState.value.isPlayerMove) {
                    'X'
                } else {
                    'O'
                }

                _gameState.value = _gameState.value.copy(
                    board = newBoard, isPlayerMove = !_gameState.value.isPlayerMove
                )

                if (!_gameState.value.isPlayerMove) {
                    val aiMove = ticTacToeAI.getBestMove(
                        _gameState.value.board, player = 'O', opponent = 'X'
                    )

                    onEvent(GameEvent.OnMovePlayed(aiMove.rowIndex, aiMove.colIndex))

                }


                val score = ticTacToeAI.evaluate(
                    board = _gameState.value.board,
                    player = 'X',
                    opponent = 'O'
                )

                if (score != 0) {
                    if (score < 0) {
                        _gameState.value = _gameState.value.copy(winner = 'O')
                    } else {
                        _gameState.value = _gameState.value.copy(winner = 'X')
                    }
                    onEvent(GameEvent.GameComplete)
                    return
                }

                if (!ticTacToeAI.areMovesLeft(_gameState.value.board) || score != 0) {
                    onEvent(GameEvent.GameComplete)
                    return
                }
            }
            is GameEvent.GameComplete -> {
                _gameState.value = _gameState.value.copy(isGameComplete = true)
            }
            is GameEvent.GameRestart -> {
                _gameState.value = GameState()
            }
        }
    }
}