package com.example.mjperrera.cardbinidentifier_kotlin.Interface

import com.example.mjperrera.cardbinidentifier_kotlin.Object.BinResponse

interface IBinInfoView {
    fun fetchBinInfoSuccess(binResponse: BinResponse)
    fun fetchBinFailed(message: String?)
}