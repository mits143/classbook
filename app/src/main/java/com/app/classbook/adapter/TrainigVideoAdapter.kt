package com.app.classbook.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.classbook.R
import com.app.classbook.model.response.TrainingVideo
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.app_education_home_1_cover_flow_pager_item.view.*
import kotlinx.android.synthetic.main.app_education_home_1_cover_flow_pager_item.view.cardImageView
import kotlinx.android.synthetic.main.app_education_home_1_cover_flow_pager_item.view.descTextView
import kotlinx.android.synthetic.main.app_education_home_1_cover_flow_pager_item.view.titleTextView
import kotlinx.android.synthetic.main.cell_cart.view.*
import kotlinx.android.synthetic.main.cell_topic.view.*

class TrainigVideoAdapter(private val dataList: ArrayList<TrainingVideo>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(view: View, obj: TrainingVideo, position: Int)
    }

    fun setOnItemClickListener(mItemClickListener: OnItemClickListener) {
        this.itemClickListener = mItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_topic, parent, false)

        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        if (viewHolder is ItemViewHolder) {

            val data = dataList[position]

            Glide.with(viewHolder.placeImageView.context).load(data.thumbnailUrl)
                .into(viewHolder.placeImageView)
            viewHolder.placeNameTextView.text = data.title
            viewHolder.distanceTextView.text = data.description

            viewHolder.cityTextView.text = if (data.active) "Published" else "Unpublished"

            viewHolder.itemView.setOnClickListener { v: View ->
                itemClickListener.onItemClick(
                    v,
                    dataList[position],
                    position
                )
            }

        }
    }

    fun modifyItemRemoved(position: Int) {
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount);
    }

    override fun getItemCount(): Int {

        return dataList?.size ?: 0
    }

    inner class ItemViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {

        internal var placeImageView: ImageView = view.placeImageView
        internal var placeNameTextView: TextView = view.placeNameTextView
        internal var distanceTextView: TextView = view.distanceTextView
        internal var cityTextView: TextView = view.cityTextView
        internal var subTextView: TextView = view.subTextView

    }
}
