package com.codex.tic_tac_toe.ui.state

data class GameState(
    var board: List<MutableList<Char>> = listOf(
        mutableListOf('-', '-', '-'),
        mutableListOf('-', '-', '-'),
        mutableListOf('-', '-', '-')
    ),
    var isPlayerMove: Boolean = true,
    var isGameComplete: Boolean = false,
    var winner: Char = '-'
)
