package com.example.letrack.support

import android.app.Application
import android.content.Context
import com.example.letrack.database.DBHelper

class MyApplication : Application() {

    companion object{
        lateinit var context: Context
        var db: DBHelper? = null
    }
    override fun onCreate() {
        super.onCreate()

        context = applicationContext
        //create db object
        if(db == null)
        {
            db = DBHelper(context)
            db!!.writableDatabase
        }
    }
}