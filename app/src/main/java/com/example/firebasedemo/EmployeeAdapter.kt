package com.example.firebasedemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmployeeAdapter(private val empList:ArrayList<EmployeeModel>) : RecyclerView.Adapter<EmployeeAdapter.Myholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeAdapter.Myholder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return Myholder(itemView)
    }

    override fun onBindViewHolder(holder: EmployeeAdapter.Myholder, position: Int) {

        holder.name.text = empList[position].empName
        holder.age.text = empList[position].empAge
        holder.designation.text = empList[position].empDesignation
    }

    override fun getItemCount(): Int {
        return empList.size
    }

    class Myholder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var name : TextView = itemView. findViewById(R.id.name_textview)
        var age : TextView = itemView. findViewById(R.id.age_textview)
        var designation : TextView = itemView. findViewById(R.id.designation_textview)

    }

}

