package com.codex.tic_tac_toe.ai

import com.codex.tic_tac_toe.model.Move

interface TicTacToeAI {
    fun getBestMove(board: List<MutableList<Char>>, player: Char, opponent: Char): Move
    fun minimax(
        board: List<MutableList<Char>>,
        depth: Int,
        isMaximizing: Boolean,
        player: Char,
        opponent: Char
    ): Int

    fun evaluate(board: List<List<Char>>, player: Char, opponent: Char): Int
    fun areMovesLeft(board: List<List<Char>>): Boolean
}