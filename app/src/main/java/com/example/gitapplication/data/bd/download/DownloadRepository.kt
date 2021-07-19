package com.example.gitapplication.data.bd.download

import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getExternalFilesDirs
import com.example.gitapplication.presenter.Presenter


class DownloadRepository {
    private val accessWrite: String = android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    private val REQUEST_CODE = 1
    lateinit var manager: DownloadManager
    lateinit var request : DownloadManager.Request
    lateinit var context: Context
    lateinit var presenter: Presenter
    lateinit var repositoryName: String
    lateinit var accountName: String
    fun downloadFile(accountN : String, repositoryN : String, defaultBranch : String, manag : DownloadManager, cont: Context, activity: Activity) {
        manager = manag
        context = cont
        presenter = Presenter()
        repositoryName = repositoryN
        accountName = accountN
        val url = "https://api.github.com/repos/${accountName}/${repositoryName}/zipball/${defaultBranch}"
        request = DownloadManager.Request(Uri.parse(url))
           request.setTitle("Загрузка ${repositoryName}")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }

        request.setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS,
            "$repositoryName.zip"
        );
        if(ActivityCompat.checkSelfPermission(context, accessWrite) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf(accessWrite), REQUEST_CODE)
        }else{
            manager.enqueue(request)
            val repositFile = getExternalFilesDirs(context,"$repositoryName.zip")
            presenter.getDataBD(repositFile.get(0), accountName, repositoryName, context)
        }
        }
     fun onRequestPermissionsResult(requestCode:Int, permissions : Array<String>, grantResults: IntArray){
        when(requestCode){
            REQUEST_CODE -> if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                manager.enqueue(request)
                val repositFile = getExternalFilesDirs(context,"$repositoryName.zip")
                presenter.getDataBD(repositFile.get(0), accountName, repositoryName, context)
            }
        }
    }

    }

