package com.app.classbook.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.classbook.R
import com.app.classbook.model.response.CartDetailModel
import kotlinx.android.synthetic.main.app_education_home_1_cover_flow_pager_item.view.*
import kotlinx.android.synthetic.main.cell_cart.view.*

class CartListAdapter(private val dataList: List<CartDetailModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(view: View, obj: CartDetailModel, position: Int)
    }

    fun setOnItemClickListener(mItemClickListener: OnItemClickListener) {
        this.itemClickListener = mItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_cart, parent, false)

        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        if (viewHolder is ItemViewHolder) {

            val data = dataList[position]

            viewHolder.txtName.text = data.providerName
            viewHolder.txtBoard.text = data.boardName
            viewHolder.txtMedium.text = data.mediumName
            viewHolder.txtStandard.text = data.standardsName
            viewHolder.txtSubject.text = data.enityName
            viewHolder.txtAmount.text = data.actualFees.toString()

            if (TextUtils.equals(data.providerType, "CareerExpert") || TextUtils.equals(data.providerType, "Courses") ) {
                viewHolder.txtBoard.visibility = View.GONE
                viewHolder.txtMedium.visibility = View.GONE
                viewHolder.txtStandard.visibility = View.GONE
            }

//            Glide.with(viewHolder.imageView.context)
//                .load(data.imageUrl)
//                .placeholder(R.drawable.placeholder)
//                .transition(DrawableTransitionOptions.withCrossFade())
//                .into(viewHolder.imageView)

            viewHolder.imgDelete.setOnClickListener { v: View ->
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

        internal var imageView: ImageView = view.imageView
        internal var txtName: TextView = view.txtName
        internal var txtBoard: TextView = view.txtBoard
        internal var txtMedium: TextView = view.txtMedium
        internal var txtStandard: TextView = view.txtStandard
        internal var txtSubject: TextView = view.txtSubject
        internal var txtAmount: TextView = view.txtAmount
        internal var imgDelete: ImageView = view.imgDelete

    }
}
