package com.codeartist.starwar.common.base.usecase

interface BaseUseCase<in Q, out P> {
    suspend fun execute(requestValues: Q): P
}