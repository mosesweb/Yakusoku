package com.fileidea.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.fileidea.yakusoku.R

class RegisterForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.w("Info", "WE HERE")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registerform)
    }
}