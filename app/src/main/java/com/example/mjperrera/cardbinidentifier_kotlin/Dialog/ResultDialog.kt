package com.example.mjperrera.cardbinidentifier_kotlin.Dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.example.mjperrera.cardbinidentifier_kotlin.Object.Bank
import com.example.mjperrera.cardbinidentifier_kotlin.Object.Country
import com.example.mjperrera.cardbinidentifier_kotlin.Object.Number
import com.example.mjperrera.cardbinidentifier_kotlin.R

class ResultDialog : Dialog, View.OnClickListener {
    private var mActivity: Activity? = null
    private var ivClose: ImageView? = null
    private var tvScheme: TextView? = null
    private var tvbrand: TextView? = null
    private var tvType: TextView? = null
    private var tvPrepaid: TextView? = null
    private var tvLength: TextView? = null
    private var tvLuhn: TextView? = null
    private var tvEmoji: TextView? = null
    private var tvCountry: TextView? = null
    private var tvLat: TextView? = null
    private var tvLong: TextView? = null
    private var tvBankName: TextView? = null
    private var tvBankPhone: TextView? = null
    private var tvBankCity: TextView? = null
    private var tvBankUrl: TextView? = null
    private var tvCurrency: TextView? = null
    private var length = 0
    private var number: Number? = null
    private var scheme: String? = null
    private var type: String? = null
    private var brand: String? = null
    private var prepaid = false
    private var country: Country? = null
    private var bank: Bank? = null

    constructor(
        activity: Activity, number: Number?, scheme: String?, type: String?,
        brand: String?, prepaid: Boolean, country: Country?, bank: Bank?
    ) : super(activity) {
        this.mActivity = activity
        this.number = number
        this.scheme = scheme
        this.type = type
        this.brand = brand
        this.prepaid = prepaid
        this.country = country
        this.bank = bank
    }

    private fun initProps() {
        ivClose = findViewById(R.id.iv_close)
        tvScheme = findViewById(R.id.tv_scheme)
        tvbrand = findViewById(R.id.tv_brand)
        tvType = findViewById(R.id.tv_type)
        tvPrepaid = findViewById(R.id.tv_prepaid)
        tvLength = findViewById(R.id.tv_length)
        tvLuhn = findViewById(R.id.tv_luhn)
        tvEmoji = findViewById(R.id.tv_emoji)
        tvCountry = findViewById(R.id.tv_country_name)
        tvLat = findViewById(R.id.tv_lat)
        tvLong = findViewById(R.id.tv_long)
        tvBankName = findViewById(R.id.tv_bank_name)
        tvBankPhone = findViewById(R.id.tv_bank_phone)
        tvBankCity = findViewById(R.id.tv_bank_city)
        tvBankUrl = findViewById(R.id.tv_bank_url)
        tvCurrency = findViewById(R.id.tv_country_currency)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_result_bin)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCancelable(false)
        initProps()
        ivClose!!.setOnClickListener(this)
        tvScheme!!.text = scheme
        tvbrand!!.text = brand
        tvType!!.text = type
        tvPrepaid!!.text = "No"
        if (prepaid) {
            tvPrepaid!!.text = "Yes"
        }
        tvLength!!.text = number!!.length.toString()
        tvLuhn!!.text = "No"
        if (number!!.luhn) {
            tvLuhn!!.text = "Yes"
        }
        if (country != null) {
            tvEmoji!!.text = country!!.emoji
            tvCountry!!.text = country!!.name
            tvLat!!.text = country!!.latitude.toString()
            tvLong!!.text = country!!.longitude.toString()
            tvCurrency!!.text = "(" + country!!.currency + ")"
        }
        if (bank != null) {
            tvBankName!!.text = bank!!.name
            tvBankPhone!!.text = bank!!.phone
            tvBankCity!!.text = bank!!.city
            tvBankUrl!!.text = bank!!.url
        }
    }


    override fun onClick(view: View) {
        when (view.id) {
            R.id.iv_close -> dismiss()
        }
    }
}