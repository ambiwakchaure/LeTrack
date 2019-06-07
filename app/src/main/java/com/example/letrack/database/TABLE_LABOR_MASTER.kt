package com.example.letrack.database

import android.content.ContentValues
import android.database.Cursor
import com.example.letrack.modules.labbors.pojo.LaborsInfo
import com.example.letrack.support.MyApplication
import com.example.letrack.support.T

class TABLE_LABOR_MASTER {

    companion object {


        //table name
        val TABLE_NAME : String = "tbl_labors"

        //table column
        val ID : String = "id"
        val FULL_NAME : String = "full_name"
        val MOBILE : String = "mobile"
        val ADDRESS : String = "address"
        val CREATED_DATE : String = "created_date"


        var CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME
                + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FULL_NAME + " TEXT, "
                + MOBILE + " TEXT, "
                + ADDRESS + " TEXT, "
                + CREATED_DATE + " TEXT)")

        //function for add new labor details
        fun addNewLabor(name : String, mobile: String, address: String)
        {

            val db = MyApplication.db!!.getWritableDatabase()

            val values = ContentValues()
            values.put(FULL_NAME, name)
            values.put(MOBILE, mobile)
            values.put(ADDRESS, address)
            values.put(CREATED_DATE, T.getSystemDateTime())

            db.insert(TABLE_NAME, null, values)

        }
        //function for select labor details
        fun getLabor(): ArrayList<LaborsInfo> {

            var laborInfo = ArrayList<LaborsInfo>()
            var cursor: Cursor? = null

            try
            {
                val db = MyApplication.db!!.getReadableDatabase()
                val uQuery = "SELECT * FROM $TABLE_NAME"
                cursor = db.rawQuery(uQuery, null)

                if (cursor.count > 0)
                {
                    while (cursor!!.moveToNext())
                    {
                        var id = cursor.getString(cursor.getColumnIndex(ID))
                        var full_name = cursor.getString(cursor.getColumnIndex(FULL_NAME))
                        var mobile = cursor.getString(cursor.getColumnIndex(MOBILE))
                        var address = cursor.getString(cursor.getColumnIndex(ADDRESS))
                        var created_date = cursor.getString(cursor.getColumnIndex(CREATED_DATE))
                        var charges = "2000"
                        laborInfo.add(LaborsInfo(id,full_name,mobile,address,charges,created_date))

                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
            return laborInfo!!
        }
        //function for add new labor details
        fun addLabor(name : String, mobile: String, address: String)
        {

            val db = MyApplication.db!!.getWritableDatabase()

            val values = ContentValues()
            values.put(FULL_NAME, name)
            values.put(MOBILE, mobile)
            values.put(ADDRESS, address)
            values.put(CREATED_DATE, T.getSystemDateTime())

            db.insert(TABLE_NAME, null, values)

        }
        fun updateLabor(id : String,name : String, mobile: String, address: String)
        {
            val db = MyApplication.db!!.getReadableDatabase()
            val uQuery = "UPDATE $TABLE_NAME SET $FULL_NAME = '"+name+"', $MOBILE = '"+mobile+"',$ADDRESS = '"+address+"' WHERE $ID = '"+id+"'"
            db.execSQL(uQuery)
        }
    }
}