package com.codex.tic_tac_toe.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codex.tic_tac_toe.ui.theme.TicTacToeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoardButton(
    modifier: Modifier = Modifier,
    playerText: Char,
    onClick: () -> Unit,
) {
    Surface(
        onClick = onClick,
        color = MaterialTheme.colorScheme.secondary,
        modifier = modifier
            .padding(4.dp)
            .size(48.dp)
            .clip(CircleShape)
            .clickable { onClick() }
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = playerText.toString(),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
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