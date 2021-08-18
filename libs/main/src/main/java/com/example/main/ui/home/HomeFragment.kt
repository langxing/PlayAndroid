package com.example.main.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.GlobalContext
import com.example.common.data.local.AppDatabase
import com.example.common.data.local.Schedule
import com.example.common.service.ServiceManager
import com.example.main.R
import com.example.main.databinding.FragmentHomeBinding
import com.zxf.basic.adapter.DiffAdapter
import com.zxf.basic.base.BaseFragment
import com.zxf.basic.base.BindingFragment
import com.zxf.basic.expand.signClick
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : BaseFragment() {
    private lateinit var mHomeViewModel: HomeViewModel
    private lateinit var mBinding: FragmentHomeBinding

    private var mData = mutableListOf<Schedule>()

    override fun layout(): Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun initView() {
        tvLogin.signClick {
            ServiceManager.startLogin(requireActivity() as AppCompatActivity) {

            }
        }
    }

    override fun initData() {
        mHomeViewModel = ViewModelProvider(
            this,
            HomeViewModelFactory(GlobalContext.database!!.scheduleDao())
        ).get(HomeViewModel::class.java)

        lifecycleScope.launch {
            mHomeViewModel.getAllStop()
                .collect {
                    mData.addAll(it)
                }
        }

        mBinding.recyclerView.adapter = object: DiffAdapter<Schedule>() {

            override fun layout(): Int {
                return R.layout.item_bus_stop
            }

            override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("SimpleDateFormat")
            override fun onBindViewHolder(holder: Holder, position: Int) {
                val item = mData[position]
                holder.setText(R.id.stop_name_text_view, item.stopName)
                    .setText(
                        R.id.arrival_time_text_view,
                        SimpleDateFormat("h:mm a").format(Date(item.arrivalTime.toLong() * 1000))
                    )
            }

        }
        mBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}