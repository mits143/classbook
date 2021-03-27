package com.app.classbook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.classbook.R
import com.app.classbook.model.response.SubscriptionResponseItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.app_education_home_1_cover_flow_pager_item.view.*
import kotlinx.android.synthetic.main.cell_subscription.view.*

class SubscriptionAdapter(private val dataList: List<SubscriptionResponseItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(view: View, obj: SubscriptionResponseItem, position: Int)
    }

    fun setOnItemClickListener(mItemClickListener: OnItemClickListener) {
        this.itemClickListener = mItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_subscription, parent, false)

        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        if (viewHolder is ItemViewHolder) {

            val data = dataList[position]

            viewHolder.transNoTextView1.text = data.providerName
            viewHolder.txtStandard.text = data.standardsName
            viewHolder.txtSubject.text = data.enityName
            viewHolder.txtMedium.text = data.mediumName
            viewHolder.txtSubDate.text = data.subscriptionDate
//            viewHolder.txtStatus.text = data.name
            viewHolder.txtAmount.text = data.paidAmount.toString()
            viewHolder.txtExpDate.text = data.expireDate

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

    inner class ItemViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {

        internal var transNoTextView1: TextView = view.transNoTextView1
        internal var txtStandard: TextView = view.txtStandard
        internal var txtSubject: TextView = view.txtSubject
        internal var txtMedium: TextView = view.txtMedium
        internal var txtSubDate: TextView = view.txtSubDate
        internal var txtStatus: TextView = view.txtStatus
        internal var txtAmount: TextView = view.txtAmount
        internal var txtExpDate: TextView = view.txtExpDate

    }
}
