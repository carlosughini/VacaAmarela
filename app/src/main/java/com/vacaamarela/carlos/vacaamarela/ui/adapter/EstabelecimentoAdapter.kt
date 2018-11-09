package com.vacaamarela.carlos.vacaamarela.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.vacaamarela.carlos.vacaamarela.Butcheryyy
import com.vacaamarela.carlos.vacaamarela.R

class EstabelecimentoAdapter(private val context: Context, private val dataSource: ArrayList<Butcheryyy>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        // Check if an existing view is being reused, otherwise inflate the view
        var listItemView = convertView
        if (listItemView == null) {
            // Get view for row item
            //listItemView = inflater.inflate(R.layout.butchery_list_item, parent, false)
        }

        val currentButchery = dataSource[position]

        // Find the TextView in the butchery_list_itemlist_item.xml layout with the ID butchery_name
        //val name = listItemView?.findViewById<TextView>(R.id.butchery_name)

        // Find the TextView in the butchery_list_item.xml_item.xml layout with the ID butchery_name
        val distanceFromTheUser = listItemView?.findViewById<TextView>(R.id.distance)

        //name!!.text = currentButchery.mButcheryName

        distanceFromTheUser!!.text = currentButchery.mDistanceFromTheUser

        return listItemView
    }

    override fun getItem(position: Int): Any {
        return position.toLong()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }


}