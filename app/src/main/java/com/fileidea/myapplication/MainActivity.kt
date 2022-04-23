package com.fileidea.yakusoku

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.fileidea.myapplication.RegisterForm
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"

class MainActivity : AppCompatActivity() {
    private val mAuth = FirebaseAuth.getInstance();

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser: FirebaseUser? = mAuth.currentUser
        updateUI(currentUser)
    }

    fun updateUI(user: FirebaseUser?) {
        Log.i("updateUI", "Update..")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        val db = FirebaseFirestore.getInstance()
        // Create a new user with a first and last name
        // Create a new user with a first and last name
        val user: MutableMap<String, Any> = HashMap()
        user["first"] = "Ada"
        user["last"] = "Lovelace"
        user["born"] = 1815

// Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    "Info",
                    "DocumentSnapshot added with ID: " + documentReference.id
                )
            }
            .addOnFailureListener { e -> Log.w("Info", "Error adding document", e) }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun SignUpButtonEvent(view: android.view.View) {
            val intent = Intent(this, RegisterForm::class.java).apply {
            }
            startActivity(intent)
    }
}
