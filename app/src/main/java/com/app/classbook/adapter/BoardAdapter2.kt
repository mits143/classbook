package com.app.classbook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.classbook.R
import com.app.classbook.model.response.BoardsData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.app_education_home_1_new_course_item.view.*
import kotlinx.android.synthetic.main.cell_autocomplete.view.*

class BoardAdapter2(private val dataList: List<BoardsData>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(view: View, obj: BoardsData, position: Int)
    }

    fun setOnItemClickListener(mItemClickListener: OnItemClickListener) {
        this.itemClickListener = mItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_autocomplete, parent, false)

        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        if (viewHolder is ItemViewHolder) {

            val data = dataList[position]

            viewHolder.txtSpinner.text = data.name

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

        return if (dataList.size > 10) 10 else dataList.size
    }

    inner class ItemViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {

        internal var txtSpinner: TextView = view.txtSpinner

    }
}
