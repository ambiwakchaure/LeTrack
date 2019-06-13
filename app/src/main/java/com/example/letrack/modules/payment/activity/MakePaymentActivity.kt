package com.example.letrack.modules.payment.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.EditText
import android.widget.RadioGroup
import com.example.letrack.R
import com.example.letrack.database.TABLE_LABOR_CHARGES
import com.example.letrack.database.TABLE_LABOR_MASTER
import com.example.letrack.database.TABLE_LABOR_PAYMENT
import com.example.letrack.support.Constants
import com.example.letrack.support.T
import kotlinx.android.synthetic.main.activity_make_payment.*
import kotlinx.android.synthetic.main.activity_make_payment.submit_btn

class MakePaymentActivity : AppCompatActivity() {

    var flag = ""

    var ID = ""
    var FULL_NAME = ""
    var MOBILE = ""
    var ADDRESS = ""
    var CREATED_DATE = ""
    var LABOR_CHARGES = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_payment)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        supportActionBar!!.setTitle("Make Payment")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        setClickListner()
        getIntentData();
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_make_payment, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        var id = item?.getItemId();
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getIntentData() {

        var bundle = intent.extras

        ID = bundle.getString(TABLE_LABOR_MASTER.ID)
        FULL_NAME = bundle.getString(TABLE_LABOR_MASTER.FULL_NAME)
        MOBILE = bundle.getString(TABLE_LABOR_MASTER.MOBILE)
        ADDRESS = bundle.getString(TABLE_LABOR_MASTER.ADDRESS)
        CREATED_DATE = bundle.getString(TABLE_LABOR_MASTER.CREATED_DATE)
        LABOR_CHARGES = bundle.getString(TABLE_LABOR_CHARGES.LABOR_CHARGES)

        labor_name_edt.setText(FULL_NAME)


    }

    private fun setClickListner() {

        flag = Constants.PAYMENT_ADVANCE
        radioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->

            if(checkedId == R.id.advance_rb)
            {
                flag = Constants.PAYMENT_ADVANCE
            }
            else if(checkedId == R.id.radvance_rb)
            {
                flag = Constants.RETURN_ADVANCE
            }
            else if(checkedId == R.id.payment_rb)
            {
                flag = Constants.PAYMENT_PAYMENT
            }
        })

        submit_btn.setOnClickListener {
            validateDetails()
        }
    }
    private fun validateDetails() {
        //first we need to check validations
        if(!T.validateEmptyField(labor_name_edt,"Enter name of labor"))
        {
            return
        }
        if(!validateEmptyRadio("Select payment type"))
        {
            return
        }
        if(!T.validateEmptyField(amtpaid_edt,"Enter amount to be paid"))
        {
            return
        }
        TABLE_LABOR_PAYMENT.addNewPayment(ID,amtpaid_edt.text.toString(),flag)
        amtpaid_edt.setText("")
        T.t("Payment added")

        /*//check update or insert
        if(submit_btn.text.toString().equals("Update"))
        {
            //update labor
            TABLE_LABOR_MASTER.updateLabor(
                ID,
                name_edt.text.toString(),
                mobile_edt.text.toString(),
                address_edt.text.toString())
            //update labor charges
            TABLE_LABOR_CHARGES.updateLaborCharges(ID,charges_edt.text.toString())
            T.t(getString(R.string.labor_updated))
        }
        else
        {
            //save labor details
            TABLE_LABOR_MASTER.addLabor(
                name_edt.text.toString(),
                mobile_edt.text.toString(),
                address_edt.text.toString())
            var labor_id = TABLE_LABOR_CHARGES.selectLaborChargesMaxId()
            TABLE_LABOR_CHARGES.addNewLaborCharges(""+(labor_id + 1),charges_edt.text.toString())
            //finally empty editext
            name_edt.setText("")
            mobile_edt.setText("")
            address_edt.setText("")
            charges_edt.setText("")
            T.t(getString(R.string.labor_added))
        }*/

    }
    fun validateEmptyRadio(message: String) : Boolean
    {
        if(advance_rb.isChecked == true || radvance_rb.isChecked || payment_rb.isChecked)
        {
            return true

        }
        else
        {
            T.t(message)
            return false
        }
    }
}
