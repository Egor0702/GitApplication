package com.example.gitapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.example.gitapplication.presenter.Presenter
import com.example.gitapplication.R

class DownloadInfo : AppCompatActivity() {

    lateinit var presenter: Presenter
    lateinit var  text : TextView
    lateinit var listView : ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download_info)

        presenter = Presenter()
        text = findViewById(R.id.textViewDown)
        listView = findViewById(R.id.list_view)

        var list = presenter.getAll(this).toMutableList()
        var array: Array<String?> = arrayOfNulls<String?>(list.size)
        for(i in 0 until list.size){
            var obj = list.get(i)
            var s = "${obj.id}. ${obj.nameAccount} - ${obj.nameRepository}."
            array.set(i, s)
        }
       val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array)

        listView.setAdapter(adapter)

    }
}