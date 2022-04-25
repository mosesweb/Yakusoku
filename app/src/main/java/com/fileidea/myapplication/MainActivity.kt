package com.fileidea.yakusoku

import android.R.attr
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.fileidea.myapplication.RegisterForm
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import io.opencensus.stats.View
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception


const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"

class MainActivity : AppCompatActivity() {
    private val mAuth = FirebaseAuth.getInstance();
    private lateinit var mFirebaseAuthLis: FirebaseAuth.AuthStateListener

    override fun onStart() {
        try {
            mAuth.addAuthStateListener(mFirebaseAuthLis)
        }
        catch(e: Exception) {
            Log.e("Info","Error! ${e.message}")
        }
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser: FirebaseUser? = mAuth.currentUser
        Log.i("updateUI", "here you are logged in maybe ${currentUser?.email}" )

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


        mFirebaseAuthLis = FirebaseAuth.AuthStateListener { firebaseAuth ->
            var user = firebaseAuth.currentUser
            if (user != null) {
                Log.i("updateUI", "logged in" )
                Toast.makeText(applicationContext,"You are logged in as ${user.email}",Toast.LENGTH_SHORT).show()
                signup_button.visibility = android.view.View.GONE
                signout_button.visibility = android.view.View.VISIBLE
            } else {
                Log.i("updateUI", "youre not logged in" )
                signup_button.visibility = android.view.View.VISIBLE
                signout_button.visibility = android.view.View.GONE

                Toast.makeText(applicationContext,"You are not logged in",Toast.LENGTH_SHORT).show()
            }
        }


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun SignUpButtonEvent(view: android.view.View) {
            val intent = Intent(this, RegisterForm::class.java).apply {
            }
            startActivity(intent)
    }
    fun SignOutButtonEvent(view: android.view.View) {
        Log.i("Info", "logout..")
        mAuth.signOut();
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}
