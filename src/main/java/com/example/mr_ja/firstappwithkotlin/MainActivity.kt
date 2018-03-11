package com.example.mr_ja.firstappwithkotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    final val TASK_NAME_REQUEST_CODE = 100
    final val PREF_NAME = "task_list"
    final val TASK_LIST_PREF_KEY = "items"


    val items = ArrayList<String>()
    var adapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        items.add("Hello")

        adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items)
        itemListView.adapter = adapter

        restoreTaskList()
    }

    override fun onStop() {
        super.onStop()

        saveTaskList()
    }

    fun saveTaskList(){
        val builder = StringBuilder()
        for(st in items){
            builder.append(st)
            builder.append("#")
        }
        val data = builder.toString()
        val preference = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        val editor = preference.edit()
        editor.putString(TASK_LIST_PREF_KEY, data)
        editor.commit()

//        with(preference.edit()){
//            putString(TASK_LIST_PREF_KEY,data)
//            commit()
//        }
    }

    fun restoreTaskList(){
        val preference = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val data = preference.getString(TASK_LIST_PREF_KEY, null)
        if(data != null){
            for(st in data.split("#")) {
                if (st != "") {
                    items.add(st)
                }
            }
            adapter?.notifyDataSetChanged()

        }

    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == TASK_NAME_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                if(data != null){
                    val taskName = data.getStringExtra("TASK_NAME_KEY")
                    if(taskName != null){
                        items.add(taskName)
                        adapter?.notifyDataSetChanged()
                    }
                }

            }else{
                items.add("cancelled")
                adapter?.notifyDataSetChanged()
            }
        }
    }

    fun newButtonClicked(view: View){
        val intent = Intent(this, InputActivity::class.java)

        startActivityForResult(intent, TASK_NAME_REQUEST_CODE)
    }




//    public fun saveButtonClicked(view: View){
////        val textView: TextView = findViewById(R.id.text_view)
////        textView.text = "Good bye my frined"
////        textView.text = "Good bye my frined"
//
//        val msg = itemEditText.text.toString()
//        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show()
//        itemEditText.text.clear()
//        items.add(msg)
////        adapter.add(msg)
//        adapter?.notifyDataSetChanged()
////        adapter!!.notifyDataSetChanged()
//    }
}
