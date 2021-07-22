package com.zxf.basic.utils

import android.app.Activity
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.zxf.basic.AppManager
import com.zxf.basic.R

class ImageLoader {

    companion object {
        fun loadUrl(url: String, imageView: ImageView) {
            Glide.with(AppManager.get().getApp().applicationContext)
                .load(url)
                .placeholder(R.mipmap.icon_default)
                .centerCrop()
                .dontAnimate()
                .into(imageView)
        }

        fun loadUrl(url: String, imageView: ImageView, width: Int, height: Int) {
            Glide.with(AppManager.get().getApp().applicationContext)
                .load(url)
                .placeholder(R.mipmap.icon_default)
                .centerCrop()
                .override(width, height)
                .dontAnimate()
                .into(imageView)
        }

        fun loadUrl(fragment: Fragment, url: String, imageView: ImageView) {
            Glide.with(fragment)
                .load(url)
                .placeholder(R.mipmap.icon_default)
                .centerCrop()
                .dontAnimate()
                .into(imageView)
        }

        fun loadUrl(activity: Activity, url: String, imageView: ImageView) {
            Glide.with(activity)
                .load(url)
                .placeholder(R.mipmap.icon_default)
                .centerCrop()
                .dontAnimate()
                .into(imageView)
        }

        fun loadGif(url: String, imageView: ImageView) {
            Glide.with(AppManager.get().getApp().applicationContext)
                .load(url)
                .placeholder(R.mipmap.icon_default)
                .centerCrop()
                .dontAnimate()
                .into(imageView)
        }
    }
}
