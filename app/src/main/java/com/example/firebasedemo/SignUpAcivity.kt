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

class SignUpAcivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    lateinit var loginText : TextView
    lateinit var usernameEdittext : EditText
    lateinit var emailEdittext : EditText
    lateinit var passwordEdittext : EditText
    lateinit var signupButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_acivity)

        auth = Firebase.auth

        usernameEdittext = findViewById(R.id.signup_username)
        emailEdittext = findViewById(R.id.signup_email)
        passwordEdittext = findViewById(R.id.signup_password)
        signupButton = findViewById(R.id.signup_button)


        loginText = findViewById(R.id.signup_loginTv)
        loginText.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        signupButton.setOnClickListener {
            registerUser()
        }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
//            reload();
        }
    }

    private fun registerUser() {
        val email = emailEdittext.text.toString().trim()
        val username = usernameEdittext.text.toString().trim()
        val password = passwordEdittext.text.toString().trim()

        if (username.equals("")){
            usernameEdittext.setError("Enter username")
            usernameEdittext.requestFocus()
            return
        }
        if (email.equals("")){
            emailEdittext.setError("Enter email")
            emailEdittext.requestFocus()
            return
        }
        if (password.equals("")){
            passwordEdittext.setError("Enter password")
            passwordEdittext.requestFocus()
            return
        }



//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "createUserWithEmail:success")
//                    val user = auth.currentUser
//
//                    FirebaseDatabase.getInstance().getReference("users")
//                        .child(FirebaseAuth.getInstance().currentUser().uid)
//                        .setValue(user).addOnCompleteListener {
//                            if (task.isSuccessful){
//                                Toast.makeText(this,"User has been register successfully",Toast.LENGTH_SHORT).show()
//                            }else{
//                                Toast.makeText(this,"Failed to register! Try again!",Toast.LENGTH_SHORT).show()
//                            }
//                        }
//                    updateUI(user)
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
//                    Toast.makeText(baseContext, "Authentication failed.",
//                        Toast.LENGTH_SHORT).show()
//                    updateUI(null)
//                }
//            }


        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    intent = Intent(this,HomeActivity::class.java)
                    startActivity(intent)
            Toast.makeText(this,"User has been register successfully",Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this,"Failed to register! Try again!",Toast.LENGTH_SHORT).show()

                }
            }


//        val user = Firebase.auth.currentUser
//        user?.let {
//            // Name, email address, and profile photo Url
//            val name = user.displayName
//            val email = user.email
//            val photoUrl = user.photoUrl
//
//            // Check if user's email is verified
//            val emailVerified = user.isEmailVerified
//
//            // The user's ID, unique to the Firebase project. Do NOT use this value to
//            // authenticate with your backend server, if you have one. Use
//            // FirebaseUser.getToken() instead.
//            val uid = user.uid
//        }

    }
}