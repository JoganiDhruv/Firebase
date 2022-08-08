package com.example.firebasedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HomeActivity : AppCompatActivity() {

    private lateinit var name :EditText
    private lateinit var age :EditText
    private lateinit var designation :EditText
    private lateinit var submit :Button
    private lateinit var receive : Button

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        name = findViewById(R.id.name_edittext)
        age = findViewById(R.id.age_edittext)
        designation = findViewById(R.id.designation_edittext)
        submit = findViewById(R.id.submit_button)
        receive = findViewById(R.id.receive_button)


        databaseReference = FirebaseDatabase.getInstance().getReference("Employee")

        submit.setOnClickListener {
            saveEmployeeData()

            val intent = Intent(this,ReceiveData::class.java)
            startActivity(intent)
        }
        receive.setOnClickListener {
            val intent = Intent(this,ReceiveData::class.java)
            startActivity(intent)
        }

    }

    private fun saveEmployeeData() {
        val empName = name.text.toString()
        val empAge = age.text.toString()
        val empDesignation = designation.text.toString()
        if(empName.isEmpty()){
            name.setError("Enter name")
            return
        }
        if(empAge.isEmpty()){
            age.setError("Enter Age")
            return
        }
        if(empDesignation.isEmpty()){
            designation.setError("Enter Designation")
            return
        }

        val empId = databaseReference.push().key!!

        val employee = EmployeeModel(empId,empName,empAge,empDesignation)
        databaseReference.child(empId).setValue(employee)
            .addOnCompleteListener {
                Toast.makeText(this,"Data inserted successfully",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{err->
                Toast.makeText(this,"Error ${err.message}",Toast.LENGTH_SHORT).show()

            }
        name.setText("")
        age.setText("")
        designation.setText("")

    }

}