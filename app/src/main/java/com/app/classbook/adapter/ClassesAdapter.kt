package com.app.classbook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.classbook.R
import com.app.classbook.model.response.AllClassesResponseItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.app_directory_place_list_2_item.view.*

class ClassesAdapter(private val dataList: List<AllClassesResponseItem>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(view: View, obj: AllClassesResponseItem, position: Int)
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

            viewHolder.distanceTextView.text = "Boards " + data.numberOfBoards
            viewHolder.cityTextView.text = "Medium " + data.numberOfMediums
            viewHolder.subTextView.text = "Standard " + data.numberOfStandards
            viewHolder.placeRatingBar.rating = data.averageRating.toFloat()
            viewHolder.totalRatingTextView.text = data.averageRating

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
