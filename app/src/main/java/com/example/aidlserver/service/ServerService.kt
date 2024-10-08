package com.example.aidlserver.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Process
import android.text.TextUtils
import com.example.aidlserver.IMyAidlInterface
import com.example.aidlserver.model.Client
import com.example.aidlserver.model.RecentClient

class ServerService : Service() {

    companion object {
        private var connectionCount: Int = 0
        private const val NOT_SENT = "Not sent!"
    }
    private val binder = object : IMyAidlInterface.Stub() {
        override fun getPid(): Int {
            return Process.myPid()
        }

        override fun getConnectionCount(): Int {
            return ServerService.connectionCount
        }

        override fun setDisplayedValue(packageName: String?, pid: Int, data: String?) {
            val clientData =
                if (data == null || TextUtils.isEmpty(data)) NOT_SENT
                else data

            RecentClient.client = Client(
                packageName ?: NOT_SENT,
                pid.toString(),
                clientData,
                "AIDL"
            )
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        connectionCount++
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        RecentClient.client = null
        return super.onUnbind(intent)
    }
}