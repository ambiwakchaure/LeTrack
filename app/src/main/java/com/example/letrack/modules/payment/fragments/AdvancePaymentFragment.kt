package com.example.letrack.modules.payment.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import com.example.letrack.R
import com.example.letrack.database.TABLE_LABOR_PAYMENT
import com.example.letrack.modules.payment.adapter.LaborPaymentHistoryAdapter
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

                    var labor_history = TABLE_LABOR_PAYMENT.getLaborPaymentHistory("1","06-2019","advance")
                    if(labor_history.isEmpty())
                    {
                        T.t("Labor payment history not found.")
                    }
                    else
                    {

                        val payment_history_rv = view!!.findViewById(R.id.payment_history_rv) as RecyclerView
                        payment_history_rv.layoutManager = LinearLayoutManager(MyApplication.context, LinearLayout.VERTICAL, false)
                        val adapter = LaborPaymentHistoryAdapter(labor_history);
                        payment_history_rv.adapter = adapter
                        adapter.notifyDataSetChanged()


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


                    }



                }

            })

        }
    }


}