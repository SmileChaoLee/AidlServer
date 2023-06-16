package com.example.aidlserver.model

data class Client(
    var clientPackageName: String?,
    var clientProcessId: String,
    var clientData: String?,
    var ipcMethod: String
)