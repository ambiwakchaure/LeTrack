package com.example.letrack.database

import android.content.ContentValues
import android.database.Cursor
import com.example.letrack.modules.labbors.pojo.LaborsInfo
import com.example.letrack.support.MyApplication
import com.example.letrack.support.T

class TABLE_LABOR_CHARGES {

    companion object {


        //table name
        val TABLE_NAME : String = "tbl_labor_charges"

        //table column
        val LABOR_ID : String = "labor_id"
        val LABOR_CHARGES : String = "labor_charges"


        var CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME
                + "(" + TABLE_LABOR_MASTER.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + LABOR_ID + " TEXT, "
                + LABOR_CHARGES + " TEXT, "
                + TABLE_LABOR_MASTER.CREATED_DATE + " TEXT)")

        //function for add new labor details
        fun addNewLaborCharges(labor_id : String, labor_charges: String)
        {

            val db = MyApplication.db!!.getWritableDatabase()

            val values = ContentValues()
            values.put(LABOR_ID, labor_id)
            values.put(LABOR_CHARGES, labor_charges)
            values.put(TABLE_LABOR_MASTER.CREATED_DATE, T.getSystemDateTime())

            db.insert(TABLE_NAME, null, values)

        }
        fun selectLaborChargesMaxId(): String {

            var labor_chrg_maxid = "0"
            try
            {
                val db = MyApplication.db!!.getReadableDatabase()
                val uQuery = "SELECT $TABLE_LABOR_MASTER.ID FROM $TABLE_NAME ORDER BY ID DESC LIMIT 1"
                var cursor = db.rawQuery(uQuery, null)

                if (cursor.count > 0)
                {
                    if (cursor!!.moveToNext())
                    {
                        labor_chrg_maxid = cursor.getString(cursor.getColumnIndex(TABLE_LABOR_MASTER.ID))
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
            return labor_chrg_maxid!!
        }
        fun updateLaborCharges(labor_id : String,charges : String)
        {
            val db = MyApplication.db!!.getReadableDatabase()
            val uQuery = "UPDATE $TABLE_NAME SET $LABOR_CHARGES = '"+charges+"' WHERE $LABOR_ID = '"+labor_id+"'"
            db.execSQL(uQuery)
        }
        /*//function for select labor details
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
        }*/
    }
}