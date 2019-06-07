package com.example.letrack.modules.labbors.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.letrack.R
import com.example.letrack.modules.labbors.pojo.LaborsInfo


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

            name_tv.text = user.full_name
            mobile_tv.text = user.mobile
            address_tv.text = user.address
            charges_tv.text = user.charges


            make_payment_btn.setOnClickListener {

            }
            view_payment_btn.setOnClickListener {

            }

        }
    }
}