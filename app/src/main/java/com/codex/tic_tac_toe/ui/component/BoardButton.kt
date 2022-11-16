package com.codex.tic_tac_toe.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codex.tic_tac_toe.ui.theme.TicTacToeTheme

@Composable
fun BoardButton(
    modifier: Modifier = Modifier,
    playerText: Char,
    onClick: () -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        shape = CircleShape,
        modifier = modifier.padding(4.dp)
    ) {
        Text(text = playerText.toString(), style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun BoardButtonPreview() {
    TicTacToeTheme {
        BoardButton(playerText = 'X') {

        }
    }
}