package com.example.letrack.modules.payment.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import com.example.letrack.R
import com.example.letrack.modules.payment.fragments.AdvancePaymentFragment
import com.example.letrack.modules.payment.fragments.PaymentPaymentFragment
import kotlinx.android.synthetic.main.activity_payment_history.*

class PaymentHistoryActivity : AppCompatActivity() {




    var mFragmentList = ArrayList<Fragment>()
    var mFragmentTitleList  = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_history)


        supportActionBar!!.hide()
        setupViewPager(viewpager)
        tabs.setupWithViewPager(viewpager)


        back_img.setOnClickListener {

            finish()
        }


    }

    private fun setupViewPager(viewpager: ViewPager?) {

        var adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(AdvancePaymentFragment(), "Advance")
        adapter.addFragment(PaymentPaymentFragment(), "Payment")
        viewpager!!.setAdapter(adapter);

    }


    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            // Log.e("shdjfdsjkjl",""+mFragmentList.size());
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }
}
