package com.codex.tic_tac_toe.ui.event

sealed class GameEvent {
    object onMovePlayed: GameEvent()
    object isGameFinished: GameEvent()
}