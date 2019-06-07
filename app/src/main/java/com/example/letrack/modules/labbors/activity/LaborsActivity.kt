package com.example.letrack.modules.labbors.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.example.letrack.R
import com.example.letrack.database.TABLE_LABOR_MASTER
import com.example.letrack.modules.labbors.adapter.LaborsAdapter
import com.example.letrack.support.Constants
import com.example.letrack.support.MyApplication
import com.example.letrack.support.T
import kotlinx.android.synthetic.main.activity_labors.*
import kotlinx.android.synthetic.main.hide_layout.*

class LaborsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_labors)


        addLabor_btn.setOnClickListener {

            var i = Intent(this,AddNewLaborActivity::class.java)
            i.putExtra(Constants.FLAG,"add")
            startActivity(i)
        }

        //get stored labor details
        getLabors()
    }

    private fun getLabors() {

        var LABORS = TABLE_LABOR_MASTER.getLabor();

        if(LABORS.isEmpty())
        {

            labors_list.setVisibility(View.GONE)
            hide_layout.setVisibility(View.VISIBLE)
            text_id.setText(getString(R.string.labor_not_found))
        }
        else
        {

            labors_list.setVisibility(View.VISIBLE)
            hide_layout.setVisibility(View.GONE)

            labors_list.layoutManager = LinearLayoutManager(MyApplication.context, LinearLayout.VERTICAL, false)
            val adapter = LaborsAdapter(LABORS);
            labors_list.adapter = adapter
            adapter.notifyDataSetChanged()
        }

    }

    override fun onResume() {
        super.onResume()
        getLabors()
    }
}
