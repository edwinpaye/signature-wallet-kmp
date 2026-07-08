package com.edwinpaye.signaturewallet.core.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

fun <T> Flow<T>.mapToResult(): Flow<Result<T>> {
    return this
        .map { Result.Success(it) as Result<T> }
        .catch { emit(Result.Error(it)) }
}

fun <T, R> Flow<Result<T>>.mapResult(transform: (T) -> R): Flow<Result<R>> {
    return this.map { result ->
        result.map(transform)
    }
}

fun <T> Flow<Result<T>>.filterSuccess(): Flow<T> {
    return this.map { result ->
        when (result) {
            is Result.Success -> result.data
            else -> null
        }
    }.filterNotNull()
}

private fun <T> Flow<T?>.filterNotNull(): Flow<T> {
    return this.map { it as T }
}
