package com.example.aidlserver

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.aidlserver.databinding.ActivityMainBinding
import com.example.aidlserver.model.RecentClient

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        binding.connectionStatus.text =
            if (RecentClient.client == null) {
                binding.linearLayoutClientState.visibility = View.INVISIBLE
                getString(R.string.no_connected_client)
            } else {
                binding.linearLayoutClientState.visibility = View.VISIBLE
                getString(R.string.last_connected_client_info)
            }
        binding.txtPackageName.text = RecentClient.client?.clientPackageName
        binding.txtServerPid.text = RecentClient.client?.clientProcessId
        binding.txtData.text = RecentClient.client?.clientData
        binding.txtIpcMethod.text = RecentClient.client?.ipcMethod
    }
}