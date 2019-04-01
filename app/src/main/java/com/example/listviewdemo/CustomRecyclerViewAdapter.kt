package com.example.listviewdemo

import android.os.Bundle
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.employee_item.view.*

class CustomRecyclerViewAdapter constructor(private val employees: ArrayList<Employee>,
                                            private val itemListener: (employee: Employee, position: Int)-> Unit)
    : RecyclerView.Adapter<CustomHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomHolder {
        return CustomHolder.create(p0)
    }

    override fun getItemCount(): Int = employees.size

    override fun onBindViewHolder(p0: CustomHolder, p1: Int) {
        p0.bindTo(employees[p1], p1, itemListener)
    }

    fun addNewEmployee(newEmployee: Employee){
        val newList = employees.toMutableList()
        newList.add(newEmployee)
        updateAdapter(newList)
    }

    fun updateEmployeeName(employee: Employee, position: Int){
        val newList = employees.toMutableList()
        newList.set(position, employee)
        updateAdapter(newList)
    }

    private fun updateAdapter(newList: List<Employee>){
        val diffCallback = EmployeeDiffCallback(this.employees, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
        employees.clear()
        employees.addAll(newList)
    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads)
        else{
            val bundle = payloads[0] as Bundle
             for (key in bundle.keySet()){
                 if (key.equals("name")){
                     holder.view.txt_name.text = employees[position].name
                 }

             }
        }
    }
}

class CustomHolder(val view: View) : RecyclerView.ViewHolder(view){

    fun bindTo(employee: Employee, position: Int, itemListener: (employee: Employee, position: Int)-> Unit){
        employee?.let {
            view.txt_name.text = employee.name
            view.setOnClickListener { itemListener(employee, position) }
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