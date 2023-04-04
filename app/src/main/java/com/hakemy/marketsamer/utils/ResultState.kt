package com.hakemy.marketsamer.utils

sealed class ResultState<out R> {
    data class Success<out T>(val data: T) : ResultState<T>()
    data class Error(val exceptionMessage: String) : ResultState<Nothing>()

    object Loading : ResultState<Nothing>()
}