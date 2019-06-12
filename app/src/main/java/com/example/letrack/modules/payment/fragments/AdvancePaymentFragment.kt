package com.example.letrack.modules.payment.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.example.letrack.R
import com.example.letrack.support.F
import com.example.letrack.support.MyApplication
import com.example.letrack.support.ReturnDetails
import com.example.letrack.support.T

class AdvancePaymentFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.advance_payment_fragment,container,false)
        setClickListner(view)
        return view
    }

    private fun setClickListner(view: View?) {

        val month_btn = view!!.findViewById(R.id.month_btn) as Button
        val year_btn = view!!.findViewById(R.id.year_btn) as Button

        month_btn.setOnClickListener {


            F.getMonths(activity, object : ReturnDetails{

                override fun returnDetails(details: String) {

                    month_btn.setText(details)
                }

            })


        }
        year_btn.setOnClickListener {

            F.getYears(activity, object : ReturnDetails{

                override fun returnDetails(details: String) {

                    year_btn.setText(details)
                }

            })

        }
    }


}