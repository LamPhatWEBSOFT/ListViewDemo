package com.example.listviewdemo

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.employee_item.view.*

class CustomRecyclerViewAdapter constructor(val employees: ArrayList<Employee>): RecyclerView.Adapter<CustomHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomHolder {
        return CustomHolder.create(p0)
    }

    override fun getItemCount(): Int = employees.size

    override fun onBindViewHolder(p0: CustomHolder, p1: Int) {
        p0.bindTo(employees[p1])
    }

    fun updateEmployeeList(newEmployees: ArrayList<Employee>){
        val diffCallback = EmployeeDiffCallback(this.employees, newEmployees)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        employees.clear()
        employees.addAll(newEmployees)
        diffResult.dispatchUpdatesTo(this)
    }
}

class CustomHolder(val view: View) : RecyclerView.ViewHolder(view){

    fun bindTo(employee: Employee){
        employee?.let {
            view.txt_name.text = employee.name
        }
    }

    companion object {
        fun create(parent: ViewGroup): CustomHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.employee_item, parent, false)
            return CustomHolder(view)
        }
    }
}