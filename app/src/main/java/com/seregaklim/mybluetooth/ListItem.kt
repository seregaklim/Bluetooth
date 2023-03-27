package com.seregaklim.mybluetooth

import java.io.Serializable

//устройство
data class ListItem(
    var name: String,
    var mac: String
): Serializable