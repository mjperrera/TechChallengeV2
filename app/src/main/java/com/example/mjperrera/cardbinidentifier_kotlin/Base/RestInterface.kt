package com.example.mjperrera.cardbinidentifier_kotlin.Base

import retrofit2.http.GET
import com.example.mjperrera.cardbinidentifier_kotlin.Object.BinResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Path

interface RestInterface {
    @GET("/{cardNo}")
    fun FETCH_BIN_DATA(@Path("cardNo") cardNo: String): Observable<Response<BinResponse>>
}