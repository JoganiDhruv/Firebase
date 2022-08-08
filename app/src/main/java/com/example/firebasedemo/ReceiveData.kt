package com.example.firebasedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class ReceiveData : AppCompatActivity() {

    private lateinit var recyclerVIew:RecyclerView
    private lateinit var empList:ArrayList<EmployeeModel>
    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receive_data)

        recyclerVIew = findViewById(R.id.recyclerview)
        recyclerVIew.layoutManager = LinearLayoutManager(this)
        empList = arrayListOf<EmployeeModel>()

        getEmployeeData()

    }

    private fun getEmployeeData() {

        databaseReference = FirebaseDatabase.getInstance().getReference("Employee")
        databaseReference.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                empList.clear()
                if (snapshot.exists()){
                    for (empSnap in snapshot.children){
                        val empData = empSnap.getValue(EmployeeModel::class.java)
                        empList.add(empData!!)
                    }
                    val adapter = EmployeeAdapter(empList)
                    recyclerVIew.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}