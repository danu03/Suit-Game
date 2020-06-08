package com.danusuhendra.suitgamev3.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.danusuhendra.suitgamev3.ui.landingpage.Landing1Fragment
import com.danusuhendra.suitgamev3.ui.landingpage.Landing2Fragment
import com.danusuhendra.suitgamev3.ui.landingpage.Landing3Fragment

class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager){
    private val fragment = listOf(
        Landing1Fragment(),
        Landing2Fragment(),
        Landing3Fragment()
    )
    override fun getItem(position: Int): Fragment {
        return fragment[position]
    }

    override fun getCount(): Int {
        return fragment.size
    }

}