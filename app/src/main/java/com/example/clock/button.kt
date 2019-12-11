package com.example.clock

import java.text.SimpleDateFormat
import java.util.*

class button {

}

class clock{
    var hour = 0
    var minute=0
    var second=0
    var df = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
    var ut = Date()
    var str = String()
    fun gettime(){
        ut = Date(System.currentTimeMillis())

    }
    fun print() :String{
        str = " %1$02d:%2$02d:%3$02d".format(hour,minute,second)

        return df.format(ut)
    }

    fun run(){

    }
}