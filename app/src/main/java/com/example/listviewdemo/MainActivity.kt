package com.example.listviewdemo

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_update_name.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var customAdapter: CustomRecyclerViewAdapter
    private var employees = arrayListOf<Employee>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAdapter()
    }

    private fun initEmployee(): ArrayList<Employee>{
        for ( i in 1..5){
            val employee = Employee(i, "name $i", i.toString())
            employees.add(employee)
        }
        return employees
    }
    private fun initAdapter(){
        val linearLayoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)
        customAdapter = CustomRecyclerViewAdapter(initEmployee()){ employee: Employee, i: Int ->
            showDialog(employee, i)
        }
        rv_employee.layoutManager = linearLayoutManager
        rv_employee.adapter = customAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.add -> {
                val newId = employees.last().id+1
                customAdapter.addNewEmployee(Employee(newId, "name $newId", "phat"))
                true

            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun showDialog(employee: Employee, i: Int) {
        val dialogBuilder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_update_name, null)
        dialogBuilder.setView(dialogView)
        val alertDialog = dialogBuilder.create()
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogView.btn_ok.setOnClickListener {
            val newEmployee = Employee(employee.id, employee.name, employee.role)
            newEmployee.name = dialogView.et_name.text.toString()
            customAdapter.updateEmployeeName(newEmployee, i)
            alertDialog.dismiss()
        }
        dialogView.btn_cancel.setOnClickListener { alertDialog.dismiss()  }
        alertDialog.show()
    }
}
