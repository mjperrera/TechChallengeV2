package com.example.mjperrera.cardbinidentifier_kotlin.View

import android.content.Context
import com.example.mjperrera.cardbinidentifier_kotlin.Base.RestInterface
import com.example.mjperrera.cardbinidentifier_kotlin.Interface.IBinInfoView
import com.example.mjperrera.cardbinidentifier_kotlin.Object.BinResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class BinInfoView(private val context: Context, private val restInterface: RestInterface?) {
    private val disposables = CompositeDisposable()
    private var binInfoResponse: Response<BinResponse>? = null

    fun getBinInfo(iBinInfoView: IBinInfoView, cardNo: String) {
        disposables.add(
            restInterface!!.FETCH_BIN_DATA(cardNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<Response<BinResponse>?>() {
                    override fun onNext(binResponse: Response<BinResponse>) {
                        binInfoResponse = binResponse
                    }

                    override fun onError(e: Throwable) {
                        iBinInfoView.fetchBinFailed(e.message)
                    }

                    override fun onComplete() {
                        if (binInfoResponse!!.raw().code() == 200) {
                            iBinInfoView.fetchBinInfoSuccess(binInfoResponse!!.body())
                        } else {
                            var code = 0
                            try {
                                code = binInfoResponse!!.raw().code()
                                iBinInfoView.fetchBinFailed(code.toString())
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    }
                })
        )
    }
}