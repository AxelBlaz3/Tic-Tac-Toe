package com.codex.tic_tac_toe.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.codex.tic_tac_toe.ui.event.GameEvent
import com.codex.tic_tac_toe.ui.state.GameState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(

): ViewModel() {

    private var _gameState: MutableState<GameState> = mutableStateOf(GameState())
    val gameState: State<GameState> = _gameState

    fun onEvent(event: GameEvent) {
        when (event) {
            is GameEvent.onMovePlayed -> {}
            is GameEvent.isGameFinished -> {}
        }
    }
}