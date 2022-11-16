package com.codex.tic_tac_toe.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codex.tic_tac_toe.R
import com.codex.tic_tac_toe.ui.component.BoardButton
import com.codex.tic_tac_toe.ui.theme.TicTacToeTheme

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel = hiltViewModel()
) {
    val gameState = gameViewModel.gameState.value

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        GameBoard(board = gameState.board)
        Spacer(modifier = modifier.height(8.dp))
        if (gameState.isPlayerMove) {
            Text(text = stringResource(id = R.string.your_turn))
        } else {
            Text(text = stringResource(id = R.string.my_turn))
        }
    }
}

@Composable
fun GameBoard(
    modifier: Modifier = Modifier,
    board: List<Char>
) {
    Column {
        BoardRow(board = board.slice(indices = 0..2))
        BoardRow(board = board.slice(indices = 3..5))
        BoardRow(board = board.slice(indices = 6..8))
    }
}

@Composable
fun BoardRow(
    modifier: Modifier = Modifier,
    board: List<Char>
) {
    Row {
        board.slice(indices = 0..2).forEach { it ->
            BoardButton(playerText = it) {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    TicTacToeTheme {
        GameScreen()
    }
}