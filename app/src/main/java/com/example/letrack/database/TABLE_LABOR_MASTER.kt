package com.example.letrack.database

import android.content.ContentValues
import android.database.Cursor
import android.util.Log
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
                val uQuery = "SELECT tbl_labors.id,full_name,mobile,address,tbl_labors.created_date,tbl_labor_charges.labor_charges FROM tbl_labors INNER JOIN tbl_labor_charges ON tbl_labors.id = tbl_labor_charges.labor_id"
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
                        var charges = cursor.getString(cursor.getColumnIndex(TABLE_LABOR_CHARGES.LABOR_CHARGES))
                        laborInfo.add(LaborsInfo(id,full_name,mobile,address,charges,created_date))

                    }
                }

            } catch (e: Exception) {
                T.e("getLabor() : "+e)
                e.printStackTrace()
            }
            return laborInfo!!
        }
        fun selectLaborMaxId(): String {

            var labor_maxid = "0"
            try
            {
                val db = MyApplication.db!!.getReadableDatabase()
                val uQuery = "SELECT $ID FROM $TABLE_NAME ORDER BY $ID DESC LIMIT 1"
                var cursor = db.rawQuery(uQuery, null)

                if (cursor.count > 0)
                {
                    if (cursor!!.moveToNext())
                    {
                        labor_maxid = cursor.getString(cursor.getColumnIndex(ID))
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
            return labor_maxid!!
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