package com.cabe.lib.ui.rowview.sample

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.core.content.FileProvider
import java.io.File
import java.lang.Exception


/**
 * RowView Demo Activity
 * Created by cabe on 16/3/29.
 */
class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    fun onClick(view: View) {
        Log.w("MainActivity", view.tag.toString() + "")
        val downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val targetFileName = "testDownload.apk"
        val downloadFile = File(downloadDir, targetFileName)
        if(downloadFile.exists()) {
            Log.w("MainActivity", "file is exist: $downloadFile")
            installApp(downloadFile)
            return
        }

        //创建下载任务,downloadUrl就是下载链接
//        val url = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2Fedpic%2Ffb%2Fa2%2F69%2Ffba2696b9fa4120d758eba82c04f1aad.jpg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1645692638&t=9c6f71c9e05e0c94f384f4b282058677"
        val url = "https://ucan.25pp.com/Wandoujia_web_seo_baidu_homepage.apk"
        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle("下载测试")
            .setDescription("测试下载功能")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        //指定下载路径和下载文件名
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, targetFileName)
        //获取下载管理器
        //将下载任务加入下载队列，否则不会进行下载
        (getSystemService(Context.DOWNLOAD_SERVICE) as? DownloadManager)?.let { downloadManager ->
            val downloadID = downloadManager.enqueue(request)
            Log.w("MainActivity", "download task id: $downloadID")
            registerReceiver(object: BroadcastReceiver() {
                @SuppressLint("Range")
                override fun onReceive(context: Context?, intent: Intent?) {
                    val query = DownloadManager.Query()
                    query.setFilterById(downloadID)
                    val cursor: Cursor = downloadManager.query(query)
                    if (cursor.moveToFirst()) {
                        val columnIndex: Int = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
                        if (DownloadManager.STATUS_SUCCESSFUL == cursor.getInt(columnIndex)) {
                            var downloadFileName: String = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI))
                            downloadFileName = downloadFileName.substring(downloadFileName.lastIndexOf('/') + 1)
                            val downloadFile = File(downloadDir, downloadFileName)
                            Log.w("MainActivity", "download file -> $downloadFile")
                            installApp(downloadFile)
                        }
                    }
                }
            }, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
        }
    }

    private fun installApp(file: File) {
        try {
            val uri = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                FileProvider.getUriForFile(this, "${BuildConfig.APPLICATION_ID}.fileprovider", file)
            } else Uri.fromFile(file)
            Log.w("MainActivity", "apk uri: $uri")
            val installIntent = Intent(Intent.ACTION_VIEW)
            installIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            installIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            installIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            installIntent.setDataAndType(uri, "application/vnd.android.package-archive")
            startActivity(installIntent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}