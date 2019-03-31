package com.example.listviewdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

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
        customAdapter = CustomRecyclerViewAdapter(initEmployee())
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
                val list = employees;
                list.add(Employee(6, "phat", "phat"))
                customAdapter.updateEmployeeList(list)
                true

            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
