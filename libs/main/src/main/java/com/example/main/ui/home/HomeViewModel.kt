package com.example.main.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.common.data.local.ScheduleDao
import com.zxf.basic.base.BaseViewModel

class HomeViewModel(private val scheduleDao: ScheduleDao) : BaseViewModel() {

    fun getAllStop() = scheduleDao.getAll()

}

class HomeViewModelFactory(private val scheduleDao: ScheduleDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(scheduleDao) as T
        } else throw IllegalArgumentException("Unknown ViewModel class")
    }

}