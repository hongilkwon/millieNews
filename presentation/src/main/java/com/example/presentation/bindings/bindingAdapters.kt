package com.example.presentation.bindings

import android.graphics.Bitmap
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.presentation.R


@BindingAdapter("dateFormat")
fun TextView.dateFormat(date: String) {
    this.text = date.split("T").first()
}


@BindingAdapter("bitmap", "imgWidth", "imgHeight")
fun ImageView.loadImage(img: Bitmap?, imgWidth: Int = 500, imgHeight: Int = 500) {
    Glide.with(this)
        .load(img)
        .placeholder(R.drawable.placeholer_image)
        .override(imgWidth, imgHeight)
        .into(this)
}