package com.example.mr_ja.firstappwithkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val items = ArrayList<String>()
    var adapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        items.add("Hello")
        items.add("World")

        adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items)
        itemListView.adapter = adapter
    }


    public fun saveButtonClicked(view: View){
//        val textView: TextView = findViewById(R.id.text_view)
//        textView.text = "Good bye my frined"
//        textView.text = "Good bye my frined"

        val msg = itemEditText.text.toString()
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show()
        itemEditText.text.clear()
        items.add(msg)
//        adapter.add(msg)
        adapter?.notifyDataSetChanged()
//        adapter!!.notifyDataSetChanged()
    }
}
