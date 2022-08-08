package com.example.firebasedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    lateinit var singupText : TextView
    lateinit var emailEdittext : EditText
    lateinit var passwordEdittext : EditText
    lateinit var loginButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        singupText = findViewById(R.id.login_signupTv)
        emailEdittext = findViewById(R.id.login_email)
        passwordEdittext = findViewById(R.id.login_password)
        loginButton = findViewById(R.id.login_button)

        singupText.setOnClickListener{
            val intent = Intent(this,SignUpAcivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            userLogin()
        }
    }

    private fun userLogin() {
        var email:String = emailEdittext.text.toString().trim()
        var password:String = passwordEdittext.text.toString().trim()

        if (email.equals("")){
            emailEdittext.setError("Enter email")
            emailEdittext.requestFocus()
            return
        }

        if (password.equals("")){
            passwordEdittext.setError("Enter password ")
            passwordEdittext.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this) {task ->

            if (task.isSuccessful){

                intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Failed to login! Try again!", Toast.LENGTH_SHORT).show()

            }

        }
    }


}

