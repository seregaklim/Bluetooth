package com.seregaklim.mybluetooth

import android.bluetooth.BluetoothSocket
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class ReceiveThread(val bSocket: BluetoothSocket) : Thread() {
    //входящий поток
    var inStream: InputStream? = null
    //исходящий поток
    var outStream: OutputStream? = null

    init {
        try {
            inStream = bSocket.inputStream
        } catch (i: IOException){

        }
        try {
            outStream = bSocket.outputStream
        } catch (i: IOException){

        }
    }

    //получаем данные
    override fun run() {
        /// чем больше объем передачи (тем больше поставить размер   val buf = ByteArray(2))
        val buf = ByteArray(2)
        while (true){
            try{
                //размер данных
                val size = inStream?.read(buf)
                //offset-0 (скачиваем сначала)
                val message = String(buf, 0, size!!)
                Log.d("MyLog","Message: $message")
           //если происходит ошибка прерываем поток
            } catch (i: IOException){
                break
            }
        }
    }

    //отправляем данные
    fun sendMessage(byteArray: ByteArray){
        try {
            outStream?.write(byteArray)
        } catch (i: IOException){

        }
    }
}