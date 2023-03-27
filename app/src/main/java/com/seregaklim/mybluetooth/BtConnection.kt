package com.seregaklim.mybluetooth

import android.bluetooth.BluetoothAdapter

//отвечает за подключение
class BtConnection(private val adapter: BluetoothAdapter) {
    lateinit var cThread: ConnectThread
   //передаем макадрес
    fun connect(mac: String) {
      //если включен блютуз и мак не null
        if (adapter.isEnabled && mac.isNotEmpty()) {
            val device = adapter.getRemoteDevice(mac)
            device.let {
                cThread = ConnectThread(it)
                cThread.start()
            }
        }
    }

    fun sendMessage(message: String){
        cThread.rThread.sendMessage(message.toByteArray())
    }


}


