package com.example.letrack.support

import android.util.Log
import android.widget.EditText
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*


class T {

    companion object{

        //toast
        fun t(message : String)
        {
            Toast.makeText(MyApplication.context,message,Toast.LENGTH_LONG).show()
        }
        //log
        fun e(message: String)
        {
            Log.e(Constants.APP_LOG,message)
        }
        //get date and time
        fun getSystemDateTime(): String? {
            var systemTime: String? = null
            try {
                val df = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
                systemTime = df.format(Calendar.getInstance().getTime())
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return systemTime
        }
        //validation function goes from here
        fun validateEmptyField(editext : EditText, message: String) : Boolean
        {
            if(editext.text.toString().trim().isEmpty())
            {
                t(message)
                return false
            }
            else
            {
                return true
            }
        }
    }
}