package com.app.classbook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.classbook.R
import com.app.classbook.model.response.Subject
import com.app.classbook.model.response.SubjectResponseItem
import kotlinx.android.synthetic.main.cell_subject.view.*

class SubjectAdapter(private val dataList: List<Subject>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(view: View, obj: Subject, position: Int, type:String)
    }

    fun setOnItemClickListener(mItemClickListener: OnItemClickListener) {
        this.itemClickListener = mItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_subject, parent, false)

        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        if (viewHolder is ItemViewHolder) {

            val data = dataList!![position]

            viewHolder.txtSubject.text = data.subjectName

//            if (data.inCart){
//                viewHolder.txtCart.isEnabled = false
//                viewHolder.txtCart.text = "Already in cart"
//                viewHolder.txtCart.setBackgroundColor(viewHolder.txtCart.context.resources.getColor(R.color.md_grey_500))
//            }else{
//                viewHolder.txtCart.isEnabled = true
//                viewHolder.txtCart.text = "Add to cart"
//                viewHolder.txtCart.setBackgroundColor(viewHolder.txtCart.context.resources.getColor(R.color.md_grey_200))
//            }

            viewHolder.itemView.setOnClickListener { v: View ->
                itemClickListener.onItemClick(
                    v,
                    dataList[position],
                    position,
                    "item"
                )
            }

            viewHolder.txtCart.setOnClickListener { v: View ->
                itemClickListener.onItemClick(
                    v,
                    dataList[position],
                    position,
                    "cart"
                )
            }

        }
    }

    override fun getItemCount(): Int {

        return dataList?.size ?: 0
    }

    class ItemViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {

        internal var txtSubject = view.txtSubject
        internal var txtCart = view.txtCart

    }
}
