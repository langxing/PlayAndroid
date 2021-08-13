package com.example.main

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.main.databinding.ActivityMainBinding
import com.zxf.basic.base.BindingActivity

class MainActivity : BindingActivity<ActivityMainBinding>() {
    private var mMenuOpen = false

    override fun initView() {
        super.initView()
        val drawerToggle = ActionBarDrawerToggle(this,
            mBinding.drawerLayout,
            mBinding.titlebar,
            R.string.menu_open,
            R.string.menu_close)
        drawerToggle.syncState()
        drawerToggle.isDrawerIndicatorEnabled = false
        mBinding.titlebar.onBackClick = {
            if (mMenuOpen) {
                mBinding.drawerLayout.close()
            } else {
                mBinding.drawerLayout.open()
            }
        }
        mBinding.navBottom.setupWithNavController(findNavController(R.id.fragment_main))
    }
}