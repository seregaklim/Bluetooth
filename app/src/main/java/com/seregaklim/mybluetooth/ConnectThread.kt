package com.seregaklim.mybluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.util.Log
import java.io.IOException
import java.util.*

//второстепепенный поток, на котором происходит подключение, принимаем и передаем данные
@SuppressLint("MissingPermission")
class ConnectThread(private val device: BluetoothDevice) : Thread() {

  // специальный индификатор
    val uuid = "00001101-0000-1000-8000-00805F9B34FB"
   //блютузсокет с помощью которого налаживаю соединение
    var mSocket: BluetoothSocket? = null
    lateinit var rThread: ReceiveThread

    init {
        try {
           //подключаемся к устройству по мак адресу
            mSocket = device.createRfcommSocketToServiceRecord(UUID.fromString(uuid))
        }catch (i: IOException){

        }
    }
     //получаем и принимаем данные, если не сработало подключение закрываем подключение
    override fun run() {
        try {
            Log.d("MyLog","Connecting...")
            mSocket?.connect()

            rThread = ReceiveThread(mSocket!!)
            rThread.start()

            Log.d("MyLog","Connected")
        }catch (i: IOException){
            Log.d("MyLog","Can not connect to device")
            closeConnection()
        }
    }

    //закрытие с обработкой ошибок
    fun closeConnection(){
        try {
            mSocket?.close()
        }catch (i: IOException){

        }
    }
}


