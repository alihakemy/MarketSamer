package com.hakemy.marketsamer.utils.services


import java.io.Serializable

data class BaseResponse<T>(
val key: String?,
   val token: String?,
    val status: Boolean?,
  val data: T?,
    val msg: String?,
val message: String?,
) : Serializable