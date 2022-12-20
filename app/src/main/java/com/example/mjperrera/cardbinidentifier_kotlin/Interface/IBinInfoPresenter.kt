package com.example.mjperrera.cardbinidentifier_kotlin.Interface

import android.content.Context
import com.example.mjperrera.cardbinidentifier_kotlin.Base.RestInterface

interface IBinInfoPresenter {
    fun fetchBinInfo(context: Context, restInterface: RestInterface?, cardNo: String)
}