package com.example.home_pc.mytestapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import com.example.home_pc.mytestapplication.R.id.tab_layout
import com.example.home_pc.mytestapplication.R.id.view_pager
import com.example.home_pc.mytestapplication.eventbus.GetViewPagerPosition
import org.greenrobot.eventbus.EventBus

class MainActivity : AppCompatActivity() {

    lateinit var tabLayout:TabLayout
    lateinit var viewPager:ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.view_pager)

        setupViewPager()
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(MyFragment.newInstance(), "MyFragment1")
        adapter.addFragment(MyFragment.newInstance(), "MyFragment2")
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {}

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

            override fun onPageSelected(position: Int) {
                val currentFrag = supportFragmentManager.findFragmentById(R.id.view_pager)
                Log.v("tag_my", "currentFrag = " + currentFrag)
                EventBus.getDefault().post(GetViewPagerPosition(position))
            }
        })
    }
}
