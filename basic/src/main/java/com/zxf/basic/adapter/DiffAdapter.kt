package com.zxf.basic.adapter

import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil

/**
 * @author Jack 2021-05-01 23:03
 */
abstract class DiffAdapter<T> : BaseAdapter() {

    private val mData by lazy {
        AsyncListDiffer(this, object: DiffUtil.ItemCallback<T>() {
            override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        })
    }

    override fun getItemCount(): Int {
        return mData.currentList.size
    }

    fun updateData(data: List<T>) {
        mData.submitList(data)
    }

    fun getData(): MutableList<T> {
        return mData.currentList.toMutableList()
    }
}