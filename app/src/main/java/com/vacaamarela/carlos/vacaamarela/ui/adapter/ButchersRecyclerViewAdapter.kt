package com.vacaamarela.carlos.vacaamarela.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.vacaamarela.carlos.vacaamarela.R
import com.vacaamarela.carlos.vacaamarela.model.Acougue
import com.vacaamarela.carlos.vacaamarela.utils.inflate
import kotlinx.android.synthetic.main.acougues_list_item.view.*

class ButchersRecyclerViewAdapter(private var items: MutableList<Acougue>,
                                  context: Context)
    : RecyclerView.Adapter<ButchersRecyclerViewAdapter.AcouguesViewHolder>() {

    var mContext = context
    /** Tag for the log messages  */
    val TAG = ButchersRecyclerViewAdapter::class.java.simpleName
    var viewHolderPosition = 0

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ButchersRecyclerViewAdapter.AcouguesViewHolder, position: Int) {
        val itemAcougue = items[position]
        val colorId = holder.getBackgroundColor()
        holder.changeBackground(colorId)
        holder.bindDados(itemAcougue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButchersRecyclerViewAdapter.AcouguesViewHolder {
        val inflatedView = parent.inflate(R.layout.acougues_list_item)
        inflatedView.tag = viewHolderPosition
        viewHolderPosition++
        return AcouguesViewHolder(inflatedView, mContext)
    }

    fun replaceData(listaAcougues: MutableList<Acougue>) {
        items = listaAcougues
        notifyDataSetChanged()
    }

    fun clearData() {
        items.clear()
        notifyDataSetChanged()
    }

    class AcouguesViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val TAG = ButchersRecyclerViewAdapter::class.java.simpleName

        private var view : View = itemView
        private var acougue : Acougue? = null
        private var mContext = context

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            Log.d("RecyclerView", "CLICK!")
        }

        fun bindDados(acougue: Acougue) {
            this.acougue = acougue
            view.casa_carne_nome.text = acougue.butcheryName
            view.casa_carne_telefone.text = acougue.butcheryPhoneNumber
            view.distance.text = "1.2 Km"
            view.quantidade_cupons.text = "10 Cupons"
        }

        fun getBackgroundColor() : Int {
            val viewTag = itemView.tag
            val colorId: Int?
            colorId = when (viewTag) {
                0,4 -> R.color.list_item_1_background
                1,5 -> R.color.list_item_2_background
                2,6 -> R.color.list_item_3_background
                3,7 -> R.color.list_item_4_background
                else -> R.color.list_item_1_background
            }
            return colorId
        }

        fun changeBackground(colorBackground: Int) {
            val color = ContextCompat.getColor(mContext, colorBackground)
            itemView.card_view.setBackgroundColor(color)
        }
    }
}
