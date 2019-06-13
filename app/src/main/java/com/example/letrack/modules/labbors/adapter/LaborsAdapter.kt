package com.example.letrack.modules.labbors.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.letrack.R
import com.example.letrack.database.TABLE_LABOR_CHARGES
import com.example.letrack.database.TABLE_LABOR_MASTER
import com.example.letrack.modules.labbors.pojo.LaborsInfo
import com.example.letrack.modules.payment.activity.MakePaymentActivity
import com.example.letrack.modules.payment.activity.PaymentHistoryActivity
import com.example.letrack.modules.payment.activity.ViewPaymentActivity

class LaborsAdapter (val userList: ArrayList<LaborsInfo>) : RecyclerView.Adapter<LaborsAdapter.ViewHolder>()
{
    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.labors_row, parent, false)
        return ViewHolder(v)
    }
    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bindItems(userList[position])
    }
    //this method is giving the size of the list
    override fun getItemCount(): Int
    {
        return userList.size
    }
    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        fun bindItems(user: LaborsInfo)
        {
            val name_tv = itemView.findViewById(R.id.name_tv) as TextView
            val mobile_tv = itemView.findViewById(R.id.mobile_tv) as TextView
            val address_tv = itemView.findViewById(R.id.address_tv) as TextView
            val charges_tv = itemView.findViewById(R.id.charges_tv) as TextView

            val make_payment_btn = itemView.findViewById(R.id.make_payment_btn) as Button
            val view_payment_btn = itemView.findViewById(R.id.view_payment_btn) as Button

            name_tv.setText(user.full_name)
            mobile_tv.setText(Html.fromHtml("<b>Mobile : </b>"+user.mobile))
            address_tv.setText(Html.fromHtml("<b>Address : </b>"+user.address))
            charges_tv.setText(Html.fromHtml("<b>Charges (per Day in ₹) : </b>"+user.charges))

            make_payment_btn.setOnClickListener {

                var i = Intent(itemView.context,MakePaymentActivity::class.java)

                i.putExtra(TABLE_LABOR_MASTER.ID,user.id)
                i.putExtra(TABLE_LABOR_MASTER.FULL_NAME,user.full_name)
                i.putExtra(TABLE_LABOR_MASTER.MOBILE,user.mobile)
                i.putExtra(TABLE_LABOR_MASTER.ADDRESS,user.address)
                i.putExtra(TABLE_LABOR_MASTER.CREATED_DATE,user.created_date)
                i.putExtra(TABLE_LABOR_CHARGES.LABOR_CHARGES,user.charges)

                itemView.context.startActivity(i)

            }
            view_payment_btn.setOnClickListener {

                var i = Intent(itemView.context, PaymentHistoryActivity::class.java)

                i.putExtra(TABLE_LABOR_MASTER.ID,user.id)

                itemView.context.startActivity(i)
            }

        }
    }
}