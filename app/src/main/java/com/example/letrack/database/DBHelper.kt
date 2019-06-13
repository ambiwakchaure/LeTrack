package com.example.letrack.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.letrack.support.Constants
import com.example.letrack.support.T
import java.lang.Exception

class DBHelper (context : Context) : SQLiteOpenHelper(context, Constants.DATABASE_NAME,null,Constants.DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase?) {

        try
        {
            db!!.execSQL(TABLE_LABOR_MASTER.CREATE_TABLE)
            db!!.execSQL(TABLE_LABOR_CHARGES.CREATE_TABLE)
            db!!.execSQL(TABLE_LABOR_PAYMENT.CREATE_TABLE)
        }
        catch (e : Exception)
        {
            T.e(""+e)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {



    }


}