package com.codex.tic_tac_toe.ai.impl

import android.util.Log
import com.codex.tic_tac_toe.ai.TicTacToeAI
import com.codex.tic_tac_toe.model.Move
import javax.inject.Inject
import kotlin.math.max
import kotlin.math.min

class TicTacToeAIImpl @Inject constructor(

) : TicTacToeAI {
    override fun getBestMove(board: List<MutableList<Char>>, player: Char, opponent: Char): Move {
        var move = Move(rowIndex = -1, colIndex = -1)
        var bestScore = -1000

        for (i in 0..2) {
            for (j in 0..2) {
                // Check if there's unplaced tile.
                if (board[i][j] == '-') {
                    // Make a move.
                    board[i][j] = player

                    // Evaluate the move.
                    val moveScore = minimax(board, 0, false, player, opponent)

                    // Undo the move.
                    board[i][j] = '-'

                    if (moveScore > bestScore) {
                        bestScore = moveScore
                        move = move.copy(rowIndex = i, colIndex = j)
                    }
                }
            }
        }

        Log.d(this.javaClass.name, "Best move: (${move.rowIndex}, ${move.colIndex})")

        return move
    }

    override fun areMovesLeft(board: List<List<Char>>): Boolean {
        for (i in 0..2) {
            for (j in 0..2) {
                if (board[i][j] == '-') {
                    return true
                }
            }
        }
        return false
    }

    override fun evaluate(board: List<List<Char>>, player: Char, opponent: Char): Int {
        // Checking for Rows for X or O victory.
        for (row in 0..2) {
            if (board[row][0] == board[row][1] &&
                board[row][1] == board[row][2]
            ) {
                if (board[row][0] == player)
                    return +10
                else if (board[row][0] == opponent)
                    return -10
            }
        }

        // Checking for Columns for X or O victory.
        for (col in 0..2) {
            if (board[0][col] == board[1][col] &&
                board[1][col] == board[2][col]
            ) {
                if (board[0][col] == player)
                    return +10
                else if (board[0][col] == opponent)
                    return -10
            }
        }

        // Checking for Diagonals for X or O victory.
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == player)
                return +10
            else if (board[0][0] == opponent)
                return -10
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == player)
                return +10
            else if (board[0][2] == opponent)
                return -10
        }

        // Else if none of them have won then return 0
        return 0
    }

    override fun minimax(
        board: List<MutableList<Char>>,
        depth: Int,
        isMaximizing: Boolean,
        player: Char,
        opponent: Char
    ): Int {
        val score = evaluate(board, player, opponent)

        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 10)
            return score

        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -10)
            return score

        // If there are no more moves and
        // no winner then it is a tie
        if (!areMovesLeft(board))
            return 0

        // If this maximizer's move
        if (isMaximizing) {
            var bestScore = -1000

            // Traverse all cells
            for (i in 0..2) {
                for (j in 0..2) {
                    // Check if cell is empty
                    if (board[i][j] == '-') {
                        // Make the move
                        board[i][j] = player

                        // Call minimax recursively and choose
                        // the maximum value
                        bestScore = max(
                            bestScore, minimax(
                                board,
                                depth + 1, !isMaximizing, player, opponent
                            )
                        )

                        // Undo the move
                        board[i][j] = '-'
                    }
                }
            }
            return bestScore
        }

        // If this minimizer's move
        else {
            var bestScore = 1000

            // Traverse all cells
            for (i in 0..2) {
                for (j in 0..2) {
                    // Check if cell is empty
                    if (board[i][j] == '-') {
                        // Make the move
                        board[i][j] = opponent

                        // Call minimax recursively and choose
                        // the minimum value
                        bestScore = min(
                            bestScore, minimax(
                                board,
                                depth + 1, !isMaximizing, player, opponent
                            )
                        )

                        // Undo the move
                        board[i][j] = '-'
                    }
                }
            }
            return bestScore
        }
    }
}