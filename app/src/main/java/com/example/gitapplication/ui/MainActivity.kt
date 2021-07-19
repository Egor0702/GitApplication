package com.example.gitapplication.ui

import android.app.DownloadManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.gitapplication.data.bd.download.DownloadRepository
import com.example.gitapplication.presenter.Presenter
import com.example.gitapplication.R


class MainActivity : AppCompatActivity() {

    val TAG = "Egor"
    lateinit var autoCompleteTextView : AutoCompleteTextView
    lateinit var buttonSearch: Button
    lateinit var nameRepository : TextView
    lateinit var spinner : Spinner
    lateinit var buttonOpen : Button
    lateinit var buttonDownload : Button
    lateinit var presenter: Presenter
    lateinit var downloadRepository: DownloadRepository
    lateinit var manager: DownloadManager
    lateinit var mapForDownload: MutableMap<String, String>
    var accountName = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        autoCompleteTextView = findViewById(R.id.auto_complete)
        buttonSearch = findViewById(R.id.button_width)
        nameRepository = findViewById(R.id.nameRepository)
        spinner = findViewById(R.id.spinner)
        buttonOpen = findViewById(R.id.button_open)
        buttonDownload = findViewById(R.id.button_download)
        presenter = Presenter()
        manager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager



        buttonSearch.setOnClickListener{
            if (autoCompleteTextView.text != null){
                var name = autoCompleteTextView.text.toString().trim()
                accountName = name
                presenter.sendRequest(name, this, this)
                nameRepository.visibility = View.VISIBLE
                spinner.visibility = View.VISIBLE
                buttonDownload.visibility = View.VISIBLE
                buttonOpen.visibility = View.VISIBLE
        }else{
            Toast.makeText(this, R.string.not_text,Toast.LENGTH_SHORT).show()
            }
        }
        buttonOpen.setOnClickListener{
            val repositoryOpen = spinner.selectedItem.toString()
            presenter.openURL(accountName, repositoryOpen, this)
        }

        buttonDownload.setOnClickListener{
            val repository = spinner.selectedItem.toString()
            var defaultBranch = mapForDownload.get(repository)!!
            presenter.downloadFile(accountName, repository, defaultBranch, manager, this, this)
        }


    }
    fun setSpinner(mutableList: MutableList<String>){
        if (mutableList != null) {
            val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mutableList)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.setAdapter(adapter)
            Log.d(TAG, "Выполнено")

        }else
            Log.d(TAG, "Пустой массив")

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_settings -> {
                val intent = Intent(this, DownloadInfo::class.java)
            startActivity(intent)
            return true}
        }
        return super.onOptionsItemSelected(item)
    }
    }

