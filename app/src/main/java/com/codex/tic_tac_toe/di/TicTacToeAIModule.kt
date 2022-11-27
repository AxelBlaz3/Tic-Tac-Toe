package com.codex.tic_tac_toe.di

import com.codex.tic_tac_toe.ai.TicTacToeAI
import com.codex.tic_tac_toe.ai.impl.TicTacToeAIImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TicTacToeAIModule {

    @Provides
    fun provideTicTacToeAI(
    ): TicTacToeAI {
        return TicTacToeAIImpl()
    }
}