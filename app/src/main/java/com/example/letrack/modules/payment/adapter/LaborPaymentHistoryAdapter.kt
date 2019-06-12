package com.example.letrack.modules.payment.adapter

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.letrack.R
import com.example.letrack.modules.labbors.pojo.LaborPaymentInfo
import com.example.letrack.support.Constants

class LaborPaymentHistoryAdapter (val userList: ArrayList<LaborPaymentInfo>) : RecyclerView.Adapter<LaborPaymentHistoryAdapter.ViewHolder>()
{
    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.advance_payment_row, parent, false)
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
            val amount_tv = itemView.findViewById(R.id.amount_tv) as TextView
            val date_tv = itemView.findViewById(R.id.date_tv) as TextView
            val time_tv = itemView.findViewById(R.id.time_tv) as TextView
            val payment_type_img = itemView.findViewById(R.id.payment_type_img) as ImageView

            amount_tv.setText(user.payment_amount)
            date_tv.setText(user.payment_date)
            time_tv.setText(user.payment_time)

            if(user.payment_type.equals(Constants.PAYMENT_ADVANCE))
            {
                payment_type_img.setImageResource(R.drawable.ic_keyboard_arrow_right_black_24dp)
            }
            else if(user.payment_type.equals(Constants.RETURN_ADVANCE))
            {
                payment_type_img.setImageResource(R.drawable.ic_keyboard_arrow_left_black_24dp)
            }



        }
    }
}