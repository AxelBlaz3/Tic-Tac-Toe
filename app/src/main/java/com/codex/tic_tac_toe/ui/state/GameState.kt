package com.codex.tic_tac_toe.ui.state

data class GameState(
    val board: List<Char> = listOf('-', '-', '-', '-', '-', '-', '-', '-', '-'),
    val isPlayerMove: Boolean = true,
)
