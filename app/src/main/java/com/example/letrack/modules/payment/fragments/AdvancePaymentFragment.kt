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
import com.example.letrack.database.TABLE_LABOR_PAYMENT
import com.example.letrack.support.*

class AdvancePaymentFragment : Fragment() {


    var month = ""
    var year = ""
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

                    month = details
                    month_btn.setText(details)
                }

            })


        }
        year_btn.setOnClickListener {

            F.getYears(activity, object : ReturnDetails{

                override fun returnDetails(details: String) {

                    year = details
                    year_btn.setText(details)

                    //get total advance amount
                    var total_advance_amount = TABLE_LABOR_PAYMENT.getAmount("1","06-2019",Constants.PAYMENT_ADVANCE)
                    //get total returned advance amount
                    var total_returned_advance_amount = TABLE_LABOR_PAYMENT.getAmount("1","06-2019",Constants.RETURN_ADVANCE)

                    var remaining_adv_amt = total_advance_amount.toInt() - total_returned_advance_amount.toInt()

                    val advance_bt = view!!.findViewById(R.id.advance_bt) as Button
                    val return_bt = view!!.findViewById(R.id.return_bt) as Button
                    val remain_bt = view!!.findViewById(R.id.remain_bt) as Button

                    advance_bt.setText("Advance : "+total_advance_amount)
                    return_bt.setText("Return : "+total_returned_advance_amount)
                    remain_bt.setText("Remain : "+remaining_adv_amt)

                    T.e("total_advance_amount : "+total_advance_amount)
                    T.e("total_returned_advance_amount : "+total_returned_advance_amount)
                    T.e("remaining_adv_amt : "+remaining_adv_amt)

                }

            })

        }
    }


}