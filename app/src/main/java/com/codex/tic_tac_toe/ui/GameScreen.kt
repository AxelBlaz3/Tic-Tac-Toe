package com.codex.tic_tac_toe.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedButton
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
import com.codex.tic_tac_toe.ui.event.GameEvent
import com.codex.tic_tac_toe.ui.theme.TicTacToeTheme

@Composable
fun GameScreen(
    modifier: Modifier = Modifier, gameViewModel: GameViewModel = hiltViewModel()
) {
    val gameState = gameViewModel.gameState.value

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        GameBoard(board = gameState.board) { i, j ->
            gameViewModel.onEvent(
                GameEvent.OnMovePlayed(rowIndex = i, colIndex = j)
            )
        }
        Spacer(modifier = modifier.height(8.dp))
        if (gameState.isGameComplete) {
            Text(text = stringResource(id = R.string.game_complete, gameState.winner))
        } else {
            if (gameState.isPlayerMove) {
                Text(text = stringResource(id = R.string.your_turn))
            } else {
                Text(text = stringResource(id = R.string.my_turn))
            }
        }

        if (gameState.isGameComplete) {
            Spacer(modifier = modifier.height(8.dp))
            OutlinedButton(onClick = { gameViewModel.onEvent(GameEvent.GameRestart) }) {
                Text(text = stringResource(id = R.string.restart))
            }
        }
    }
}

@Composable
fun GameBoard(
    modifier: Modifier = Modifier, board: List<List<Char>>, onClick: (Int, Int) -> Unit
) {
    Column {
        board.forEachIndexed { i, boardRow ->
            Row {
                boardRow.forEachIndexed { j, playerText ->
                    BoardButton(playerText = playerText) {
                        onClick(i, j)
                    }
                }
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