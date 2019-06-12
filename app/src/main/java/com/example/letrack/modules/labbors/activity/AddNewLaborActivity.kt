package com.example.letrack.modules.labbors.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.letrack.R
import com.example.letrack.database.TABLE_LABOR_CHARGES
import com.example.letrack.database.TABLE_LABOR_MASTER
import com.example.letrack.support.Constants
import com.example.letrack.support.T
import kotlinx.android.synthetic.main.activity_add_new_labor.*

class AddNewLaborActivity : AppCompatActivity() {

    lateinit var ID : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_labor)


        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        getIntentDetails()
        setClickListner();
    }
    private fun getIntentDetails() {

        //set mac address
        var bundle = intent.extras
        var flag = bundle.getString(Constants.FLAG)

        if(flag.equals(Constants.FLAG_UPDATE))
        {

            submit_btn.setText("Update")
            ID = bundle.getString(TABLE_LABOR_MASTER.ID)
            var NAME = bundle.getString(TABLE_LABOR_MASTER.FULL_NAME)
            var MOBILE_NUMBER = bundle.getString(TABLE_LABOR_MASTER.MOBILE)
            var ADDRESS = bundle.getString(TABLE_LABOR_MASTER.ADDRESS)
            var CHARGES = bundle.getString(TABLE_LABOR_MASTER.FULL_NAME)

            name_edt.setText(NAME)
            mobile_edt.setText(MOBILE_NUMBER)
            address_edt.setText(ADDRESS)
            charges_edt.setText(CHARGES)
        }
    }

    private fun setClickListner() {

        submit_btn.setOnClickListener {
            validateDetails()
        }
    }

    private fun validateDetails() {
        //first we need to check validations
        if(!T.validateEmptyField(name_edt,getString(R.string.labor_name)))
        {
            return
        }
        if(!T.validateEmptyField(mobile_edt,getString(R.string.labor_mobile)))
        {
            return
        }
        if(!T.validateEmptyField(address_edt,getString(R.string.labor_address)))
        {
            return
        }
        if(!T.validateEmptyField(charges_edt,getString(R.string.labor_charges)))
        {
            return
        }
        //check update or insert
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
            var labor_id = TABLE_LABOR_MASTER.selectLaborMaxId()
            TABLE_LABOR_CHARGES.addNewLaborCharges(labor_id ,charges_edt.text.toString())
            //finally empty editext
            name_edt.setText("")
            mobile_edt.setText("")
            address_edt.setText("")
            charges_edt.setText("")
            T.t(getString(R.string.labor_added))
        }

    }
}
