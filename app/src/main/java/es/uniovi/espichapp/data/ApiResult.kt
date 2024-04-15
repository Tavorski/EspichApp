package es.uniovi.espichapp.data

import es.uniovi.espichapp.status.AppStatus

sealed class ApiResult <out T> (val status: AppStatus, val data: T?, val message:String?) {

    data class Success<out R>(val dat: R): ApiResult<R>(
        status = AppStatus.SUCCESS,
        data = dat,
        message = null
    )

    data class Error(val exception: String): ApiResult<Nothing>(
        status = AppStatus.ERROR,
        data = null,
        message = exception
    )
}