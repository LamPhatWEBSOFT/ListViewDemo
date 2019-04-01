package com.example.listviewdemo

import android.os.Bundle
import android.support.v7.util.DiffUtil

class EmployeeDiffCallback (oldEmployeeList: ArrayList<Employee>, newEmployeeList: List<Employee>) :
    DiffUtil.Callback() {
    private val mOldEmployeeList: List<Employee> = oldEmployeeList
    private val mNewEmployeeList: List<Employee> = newEmployeeList

    override fun areItemsTheSame(p0: Int, p1: Int): Boolean {
        return mOldEmployeeList[p0].id == mNewEmployeeList[p1].id
    }

    override fun getOldListSize(): Int = mOldEmployeeList.size

    override fun getNewListSize(): Int = mNewEmployeeList.size

    override fun areContentsTheSame(p0: Int, p1: Int): Boolean {
        val oldEmployee = mOldEmployeeList[p0]
        val newEmployee = mNewEmployeeList[p1]
        return oldEmployee.name.equals(newEmployee.name)
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldEmployee = mOldEmployeeList[oldItemPosition]
        val newEmployee = mNewEmployeeList[newItemPosition]
        val bundle = Bundle()
        if (!oldEmployee.name.equals(newEmployee.name))
            bundle.putString("name", newEmployee.name)
        if (!oldEmployee.role.equals(oldEmployee.role))
            bundle.putString("role", newEmployee.role)
        if (bundle.size() != 0)
            return null
        return bundle
    }
}