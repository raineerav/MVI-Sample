package com.live.bets.domain.utils

import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class APIUtils {
    companion object {
        fun resolveError(e: Throwable): DataState.Error {
            var error = e

            when (e) {
                is SocketTimeoutException -> {
                    error = NetworkErrorException(errorMessage = "connection error!")
                }

                is ConnectException -> {
                    error = NetworkErrorException(errorMessage = "no internet access!")
                }

                is UnknownHostException -> {
                    error = NetworkErrorException(errorMessage = "no internet access!")
                }

                is HttpException -> {
                    when (e.code()) {
                        502 -> {
                            error = NetworkErrorException(e.code(), "internal error!")
                        }

                        401 -> {
                            throw AuthenticationException("authentication error!")
                        }

                        400 -> {
                            error = NetworkErrorException.parseException(e)
                        }
                    }
                }
            }
            return DataState.Error(error)
        }
    }
}