package com.app.classbook.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.app.classbook.model.response.StateResponseItem

class StateAdapter(
    context: Context,
    @LayoutRes private val layoutResource: Int,
    private val dataList: List<StateResponseItem>
) :
    ArrayAdapter<StateResponseItem>(context, layoutResource, dataList),
    Filterable {
    private var filterList: List<StateResponseItem> = dataList

    override fun getCount(): Int {
        return filterList.size
    }

    override fun getItem(p0: Int): StateResponseItem? {
        return filterList.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        // Or just return p0
        return filterList.get(p0).id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: TextView = convertView as TextView? ?: LayoutInflater.from(context)
            .inflate(layoutResource, parent, false) as TextView
        view.text = filterList[position].name
        return view
    }
}