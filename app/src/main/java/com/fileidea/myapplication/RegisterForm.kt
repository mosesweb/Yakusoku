package com.fileidea.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.fileidea.yakusoku.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.registerform.*


class RegisterForm : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        // ...
        // Initialize Firebase Auth
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

        Log.w("Info", "WE HERE")
        setContentView(R.layout.registerform)
    }

    fun RegisterFromDetails() {

        val username =  emailinput.text.toString()
        val password =  passwordinput.text.toString()

        auth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(this) { task ->
            if(task.isSuccessful) {
                Log.d("Info", "Registed")

                val returnIntent = Intent()
                val user: FirebaseUser? = auth.currentUser

                returnIntent.putExtra("result", user)
               // setResult(RESULT_OK, returnIntent)
                finish()
            }
            else {
                val err = task.exception?.message;
                Log.e("Info", err.toString())
            }
        }
    }

    fun RegisterFromDetailsClick(view: View) {

        RegisterFromDetails();

    }
}