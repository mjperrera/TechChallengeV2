package com.example.mjperrera.cardbinidentifier_kotlin.Presenter

import android.content.Context
import com.example.mjperrera.cardbinidentifier_kotlin.Base.RestInterface
import com.example.mjperrera.cardbinidentifier_kotlin.Interface.IBinInfoPresenter
import com.example.mjperrera.cardbinidentifier_kotlin.Interface.IBinInfoView
import com.example.mjperrera.cardbinidentifier_kotlin.View.BinInfoView

class BinInfoPresenter(private val iBinInfoView: IBinInfoView) : IBinInfoPresenter {
    override fun fetchBinInfo(context: Context, restInterface: RestInterface?, cardNo: String) {
        val binInfoView = BinInfoView(context, restInterface)
        binInfoView.getBinInfo(iBinInfoView, cardNo)
    }
}