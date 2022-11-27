package com.codex.tic_tac_toe.ui.event

sealed class GameEvent {
    class OnMovePlayed(val rowIndex: Int, val colIndex: Int) : GameEvent()
    object GameComplete: GameEvent()
    object GameRestart: GameEvent()
}