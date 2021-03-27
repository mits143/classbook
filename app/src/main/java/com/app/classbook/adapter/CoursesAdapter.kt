package com.app.classbook.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.classbook.R
import com.app.classbook.model.response.CoursesListResponseItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.app_directory_place_list_2_item.view.*

class CoursesAdapter(private val dataList: List<CoursesListResponseItem>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(view: View, obj: CoursesListResponseItem, position: Int)
    }

    fun setOnItemClickListener(mItemClickListener: OnItemClickListener) {
        this.itemClickListener = mItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.app_directory_place_list_2_item, parent, false)

        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        if (viewHolder is ItemViewHolder) {

            val data = dataList!![position]

            viewHolder.placeNameTextView.text = data.name

            Glide.with(viewHolder.placeImageView.context)
                .load(data.image)
                .placeholder(R.drawable.placeholder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(viewHolder.placeImageView)

//            viewHolder.distanceTextView.text = if (TextUtils.isEmpty(data.courseProviderName)) ""  else "Boards " + data.courseProviderName
//            viewHolder.cityTextView.text = "Standard " + data.categoryName.toString()
//            viewHolder.subTextView.visibility = View.GONE
//            viewHolder.placeRatingBar.rating = data.rating.toFloat()
//            viewHolder.totalRatingTextView.text = data.rating

            viewHolder.itemView.setOnClickListener { v: View ->
                itemClickListener.onItemClick(
                    v,
                    dataList[position],
                    position
                )
            }

        }
    }

    override fun getItemCount(): Int {

        return dataList?.size ?: 0
    }

    class ItemViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {

        internal var placeImageView = view.placeImageView
        internal var placeNameTextView = view.placeNameTextView
        internal var distanceTextView = view.distanceTextView
        internal var cityTextView = view.cityTextView
        internal var placeRatingBar = view.placeRatingBar
        internal var totalRatingTextView = view.totalRatingTextView
        internal var subTextView = view.subTextView

    }
}
