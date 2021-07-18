package com.example.gitapplication

import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.room.Room


class MainActivity : AppCompatActivity() {
    lateinit var autoCompleteTextView : AutoCompleteTextView
    lateinit var buttonSearch: Button
    lateinit var nameRepository : TextView
    lateinit var spinner : Spinner
    lateinit var buttonOpen : Button
    lateinit var buttonDownload : Button
    private val accessWrite: String = android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    private val REQUEST_CODE = 1
    lateinit var retrofitImpl: RetrofitImpl
    lateinit var downloadRepository: DownloadRepository
    lateinit var manager: DownloadManager
    lateinit var dataBaseApp: DataBaseApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        autoCompleteTextView = findViewById(R.id.auto_complete)
        buttonSearch = findViewById(R.id.button_width)
        nameRepository = findViewById(R.id.nameRepository)
        spinner = findViewById(R.id.spinner)
        buttonOpen = findViewById(R.id.button_open)
        buttonDownload = findViewById(R.id.button_download)
        retrofitImpl = RetrofitImpl()
        downloadRepository = DownloadRepository()
        manager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        dataBaseApp = Room.databaseBuilder(this, DataBaseApp::class.java, "database").build()

        buttonSearch.setOnClickListener{}
        buttonOpen.setOnClickListener{}
        buttonDownload.setOnClickListener{}

        try{
            if(ActivityCompat.checkSelfPermission(this, accessWrite) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(accessWrite), REQUEST_CODE)
            }else{
//                Log.d(TAG, "мы в разрешении")
//                if (!flag)
//                    locationData.setNowLocation(this,this)
//                showData()
            }
        }catch(e:Exception){
            Log.d("Egor", "Error permission: ${e.message}")}

        retrofitImpl.getRequest("Egor0702")

    }
    override fun onRequestPermissionsResult(requestCode:Int, permissions : Array<String>, grantResults: IntArray){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_CODE -> if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                downloadRepository.downloadFile("Egor0702", "MyTestBank", "workVariantOne", manager)
            }
        }
    }
}
