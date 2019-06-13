package com.example.letrack.database

import android.content.ContentValues
import android.database.Cursor
import android.util.Log
import com.example.letrack.modules.labbors.pojo.LaborPaymentInfo
import com.example.letrack.modules.labbors.pojo.LaborsInfo
import com.example.letrack.support.Constants
import com.example.letrack.support.MyApplication
import com.example.letrack.support.T

class TABLE_LABOR_PAYMENT {

    companion object {
        //table name
        val TABLE_NAME : String = "tbl_labor_payment"

        //table column
        val ID : String = "id"
        val L_ID : String = "l_id"
        val PAYMENT_AMOUNT : String = "payment_amount"
        val PAYMENT_TYPE : String = "payment_type"
        val CREATED_DATE : String = "created_date"

        var CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME
                + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + L_ID + " TEXT, "
                + PAYMENT_AMOUNT + " TEXT, "
                + PAYMENT_TYPE + " TEXT, "
                + CREATED_DATE + " TEXT)")

        //function for add new labor details
        fun addNewPayment(l_id : String, payment_amount: String, payment_type: String)
        {

            val db = MyApplication.db!!.getWritableDatabase()

            val values = ContentValues()
            values.put(L_ID, l_id)
            values.put(PAYMENT_AMOUNT, payment_amount)
            values.put(PAYMENT_TYPE, payment_type)
            values.put(CREATED_DATE, T.getSystemDateTime())

            db.insert(TABLE_NAME, null, values)

        }
        /*//function for select labor details
        fun getLaborPayments(l_id : String,year : String): ArrayList<LaborPaymentInfo> {

            var laborInfo = ArrayList<LaborPaymentInfo>()
            var cursor: Cursor? = null

            try
            {
                val db = MyApplication.db!!.getReadableDatabase()
                val uQuery = "SELECT full_name,tbl_labor_charges.labor_charges FROM tbl_labors INNER JOIN tbl_labor_charges ON tbl_labors.id = tbl_labor_charges.labor_id WHERE tbl_labors.id = '"+l_id+"' AND tbl_labors.created_date LIKE '%"+year+"%'"
                cursor = db.rawQuery(uQuery, null)

                if (cursor.count > 0)
                {
                    while (cursor!!.moveToNext())
                    {

                        var full_name = cursor.getString(cursor.getColumnIndex(TABLE_LABOR_MASTER.FULL_NAME))
                        var charges = cursor.getString(cursor.getColumnIndex(TABLE_LABOR_CHARGES.LABOR_CHARGES))
                        //get total paid amount
                        var total_paid_amount = getAmount(l_id,"06-2019",Constants.PAYMENT_PAYMENT)
                        //get total_advance_amount
                        var total_advance_amount = getAmount(l_id,"06-2019",Constants.PAYMENT_ADVANCE)
                        //get remaining_advance_amount
                        var remaining_advance_amount =  total_advance_amount.toDouble() - total_paid_amount.toDouble()

                        if(!(total_paid_amount.equals("0") && total_advance_amount.equals("0") && remaining_advance_amount.equals("0")))
                        {
                            laborInfo.add(LaborPaymentInfo(full_name,charges,total_paid_amount,total_advance_amount,""+remaining_advance_amount,"Jan 2019"))
                        }

                    }
                }

            } catch (e: Exception) {
                T.e("getLabor() : "+e)
                e.printStackTrace()
            }
            return laborInfo!!
        }*/

        fun getAmount(l_id : String,month_year : String,payment_type : String): String {

            var amount_data = "0"
            try
            {
                val db = MyApplication.db!!.getReadableDatabase()
                val uQuery = "SELECT SUM($PAYMENT_AMOUNT) AS AMOUNT_DATA FROM $TABLE_NAME WHERE $L_ID = '"+l_id+"' AND $PAYMENT_TYPE = '"+payment_type+"' AND $CREATED_DATE LIKE '%"+month_year+"%'"
                var cursor = db.rawQuery(uQuery, null)

                if (cursor.count > 0)
                {
                    if (cursor!!.moveToNext())
                    {
                        amount_data = cursor.getString(cursor.getColumnIndex("AMOUNT_DATA"))
                    }
                }

            } catch (e: Exception) {
                T.e("getLabor() : "+e)
                e.printStackTrace()
            }
            return amount_data!!
        }
        /*//function for add new labor details
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
        }*/
    }
}