package com.app.classbook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.classbook.R
import com.app.classbook.model.response.SMBData
import kotlinx.android.synthetic.main.app_directory_place_list_2_item.view.*
import kotlinx.android.synthetic.main.cell_classdetail.view.*

class ClassesDetailAdapter(private val dataList: List<SMBData>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(view: View, obj: SMBData, position: Int)
    }

    fun setOnItemClickListener(mItemClickListener: OnItemClickListener) {
        this.itemClickListener = mItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_classdetail, parent, false)

        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        if (viewHolder is ItemViewHolder) {

            val data = dataList!![position]

            viewHolder.txtBoard.text = data.boardName
            viewHolder.txtMedium.text = data.mediumName
            viewHolder.txtStandard.text = data.standardName

            viewHolder.txtSubject.setOnClickListener { v: View ->
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

        internal var txtBoard = view.txtBoard
        internal var txtMedium = view.txtMedium
        internal var txtStandard = view.txtStandard
        internal var txtSubject = view.txtSubject

    }
}
