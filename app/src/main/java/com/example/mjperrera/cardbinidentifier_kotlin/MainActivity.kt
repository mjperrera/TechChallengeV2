package com.example.mjperrera.cardbinidentifier_kotlin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.mjperrera.cardbinidentifier_kotlin.Base.BaseActivity
import com.example.mjperrera.cardbinidentifier_kotlin.Base.Utils
import com.example.mjperrera.cardbinidentifier_kotlin.Dialog.ResultDialog
import com.example.mjperrera.cardbinidentifier_kotlin.Interface.IBinInfoPresenter
import com.example.mjperrera.cardbinidentifier_kotlin.Interface.IBinInfoView
import com.example.mjperrera.cardbinidentifier_kotlin.Object.BinResponse
import com.example.mjperrera.cardbinidentifier_kotlin.Presenter.BinInfoPresenter

class MainActivity : BaseActivity(), IBinInfoView, View.OnClickListener {
    private val TAG = "MAIN"
    private var iBinInfoPresenter: IBinInfoPresenter? = null
    private var edtCardNo: EditText? = null
    private var btnSearch: Button? = null
    private var progressBar: ProgressBar? = null

    private fun initProps() {
        edtCardNo = findViewById(R.id.edt_card_no)
        btnSearch = findViewById(R.id.btn_search)
        progressBar = findViewById(R.id.progressbar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.black)
        initProps()
        iBinInfoPresenter = BinInfoPresenter(this)
        btnSearch!!.setOnClickListener(this)
        val initValue = "45717360"
        edtCardNo!!.setText(formatStrWithSpaces(initValue))
        progressBar!!.visibility = View.GONE

        edtCardNo!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                btnSearch!!.isEnabled = edtCardNo!!.length() >= 4
                val origin = s.toString().replace(" ".toRegex(), "")
                val formatStr = formatStrWithSpaces(origin)
                if (s.toString() != formatStr) {
                    Utils.editTextSetContentMemorizeSelection(edtCardNo!!, formatStr)
                    if (before == 0 && count == 1 && formatStr[edtCardNo!!.selectionEnd - 1] == ' ') {
                        edtCardNo!!.setSelection(edtCardNo!!.selectionEnd + 1)
                    }
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })
        edtCardNo!!.setOnTouchListener(View.OnTouchListener { v, event ->
            val DRAWABLE_RIGHT = 2
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= edtCardNo!!.right -
                    edtCardNo!!.compoundDrawables[DRAWABLE_RIGHT].bounds.width()
                ) {
                    edtCardNo!!.setText("")
                    return@OnTouchListener true
                }
            }
            false
        })

    }

    override fun fetchBinInfoSuccess(binResponse: BinResponse) {
        progressBar!!.visibility = View.GONE
        Log.d(TAG, "fetchBinInfoSuccess")
        val dialog = ResultDialog(
            this, binResponse.number,
            binResponse.scheme, binResponse.type, binResponse.brand,
            binResponse.prepaid, binResponse.country, binResponse.bank
        )
        dialog.show()
    }

    override fun fetchBinFailed(message: String?) {
        progressBar!!.visibility = View.GONE
        Log.d(TAG, "fetchBinFailed")
        if (message != null) {
            Log.e(TAG, "Error occurred: $message")
            Toast.makeText(this, "Error occurred: $message", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_search -> {
                progressBar!!.visibility = View.VISIBLE
                iBinInfoPresenter!!.fetchBinInfo(
                    this, bRestInterface, edtCardNo!!.text
                        .toString().replace(" ", "")
                )
            }
        }
    }

    fun formatStrWithSpaces(str: CharSequence): String {
        val sb = StringBuffer()
        for (i in 0 until str.length) {
            if (i != 0 && i % 4 == 0) {
                sb.append(' ')
            }
            sb.append(str[i])
        }
        return sb.toString()
    }
}