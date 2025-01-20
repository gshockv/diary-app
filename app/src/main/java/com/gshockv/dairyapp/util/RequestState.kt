package com.gshockv.dairyapp.util

sealed class RequestState<out T> {
  object Idle: RequestState<Nothing>()
  object Loading: RequestState<Nothing>()
  data class Success<T>(val data: T) : RequestState<T>()
}
