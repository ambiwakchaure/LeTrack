package com.example.letrack.modules.labbors.adapter

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.letrack.R
import com.example.letrack.modules.labbors.pojo.LaborPaymentInfo

class LaborPaymentAdapter (val userList: ArrayList<LaborPaymentInfo>) : RecyclerView.Adapter<LaborPaymentAdapter.ViewHolder>()
{
    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.labor_payment_row, parent, false)
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
        fun bindItems(user: LaborPaymentInfo)
        {
            val labor_name_tv = itemView.findViewById(R.id.labor_name_tv) as TextView
            val month_year_tv = itemView.findViewById(R.id.month_year_tv) as TextView
            val labor_charges_tv = itemView.findViewById(R.id.labor_charges_tv) as TextView
            val total_paid_amt_tv = itemView.findViewById(R.id.total_paid_amt_tv) as TextView
            val total_adv_amt_tv = itemView.findViewById(R.id.total_adv_amt_tv) as TextView
            val remain_adv_amt_tv = itemView.findViewById(R.id.remain_adv_amt_tv) as TextView

            labor_name_tv.setText(user.full_name)
            month_year_tv.setText(Html.fromHtml(user.month_year))
            labor_charges_tv.setText(Html.fromHtml("Labor Charges : <b>"+user.labor_charges+"</b>"))
            total_paid_amt_tv.setText(Html.fromHtml("Total Paid Amount : <b>"+user.total_paid_amount+"</b>"))
            total_adv_amt_tv.setText(Html.fromHtml("Total Advance Amount : <b>"+user.total_advance_amount+"</b>"))
            remain_adv_amt_tv.setText(Html.fromHtml("Remaining Advance Amount : <b>"+user.remaining_advance_amount+"</b>"))

        }
    }
}