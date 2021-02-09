package com.codeartist.trivagochallenge.common.usecase

interface BaseUseCase<in Q, out P> {
    suspend fun execute(requestValues: Q): P
}