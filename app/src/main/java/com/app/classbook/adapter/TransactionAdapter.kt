package com.app.classbook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.classbook.R
import com.app.classbook.model.response.TransactionResponseItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.app_education_home_1_cover_flow_pager_item.view.*
import kotlinx.android.synthetic.main.cell_subscription.view.*
import kotlinx.android.synthetic.main.cell_transaction.view.*

class TransactionAdapter(private val dataList: List<TransactionResponseItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(view: View, obj: TransactionResponseItem, position: Int)
    }

    fun setOnItemClickListener(mItemClickListener: OnItemClickListener) {
        this.itemClickListener = mItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_transaction, parent, false)

        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        if (viewHolder is ItemViewHolder) {

            val data = dataList[position]

            viewHolder.txtTransactionNo.text = data.transcatioNo.toString()
            viewHolder.txtClassName.text = data.providerName
            viewHolder.txtActive.text = data.enityName
            viewHolder.txtAmt.text = data.paidAmount.toString()
            viewHolder.txtDate.text = data.orderDate
//            viewHolder.txtType.text = data.ty

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

        internal var txtTransactionNo: TextView = view.txtTransactionNo
        internal var txtClassName: TextView = view.txtClassName
        internal var txtActive: TextView = view.txtActive
        internal var txtAmt: TextView = view.txtAmt
        internal var txtDate: TextView = view.txtDate
        internal var txtType: TextView = view.txtType

    }
}
