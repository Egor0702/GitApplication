package com.example.gitapplication

import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.core.content.ContextCompat.getSystemService

class DownloadRepository {
    fun downloadFile(accountName : String, repositoryName : String, defaultBranch : String, manager : DownloadManager) {
        val url = "https://api.github.com/repos/${accountName}/${repositoryName}/zipball/${defaultBranch}"
        val request : DownloadManager.Request = DownloadManager.Request(Uri.parse(url))
        request.setDescription("Ля-ля-ля")
           request.setTitle("Загрузка ${repositoryName}")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }

        request.setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS,
            "$repositoryName.zip"
        );
        manager.enqueue(request);
    }
}