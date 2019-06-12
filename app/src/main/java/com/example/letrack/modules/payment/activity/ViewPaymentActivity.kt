package com.example.letrack.modules.payment.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.example.letrack.R
import com.example.letrack.database.TABLE_LABOR_MASTER
import com.example.letrack.database.TABLE_LABOR_PAYMENT
import com.example.letrack.modules.payment.adapter.LaborPaymentHistoryAdapter
import com.example.letrack.support.MyApplication
import com.example.letrack.support.T
import kotlinx.android.synthetic.main.activity_view_payment.*

class ViewPaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_payment)

        setClickListner()
        getPaymentDetails()
    }

    private fun getPaymentDetails() {

        var bundle = intent.extras
        var ID = bundle.getString(TABLE_LABOR_MASTER.ID)
        var LABORS = TABLE_LABOR_PAYMENT.getLaborPayments(ID,"");

        if(LABORS.isEmpty())
        {
            T.t("Labor payment not found")
           // hide_layout.setVisibility(View.VISIBLE)
           // text_id.setText(getString(R.string.labor_not_found))
        }
        else
        {
           // hide_layout.setVisibility(View.GONE)
            view_payment_rv.layoutManager = LinearLayoutManager(MyApplication.context, LinearLayout.VERTICAL, false)
            val adapter = LaborPaymentHistoryAdapter(LABORS);
            view_payment_rv.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }

    private fun setClickListner() {

        payment_history_bt.setOnClickListener {

        }
    }
}
