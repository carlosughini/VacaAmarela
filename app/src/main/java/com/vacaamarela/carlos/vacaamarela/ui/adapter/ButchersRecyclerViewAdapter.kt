package com.vacaamarela.carlos.vacaamarela.ui.adapter

import android.app.FragmentTransaction
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.vacaamarela.carlos.vacaamarela.R
import com.vacaamarela.carlos.vacaamarela.model.Butchery
import com.vacaamarela.carlos.vacaamarela.ui.detail.ButcheryDetailActivity
import com.vacaamarela.carlos.vacaamarela.ui.detail.DetailFragment
import com.vacaamarela.carlos.vacaamarela.ui.home.ButchersViewModel
import com.vacaamarela.carlos.vacaamarela.ui.home.HomeActivity
import com.vacaamarela.carlos.vacaamarela.utils.inflate
import kotlinx.android.synthetic.main.acougues_list_item.view.*

class ButchersRecyclerViewAdapter constructor(private var items: MutableList<Butchery>,
                                  private var listener: contentListener,
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
        holder.bindDados(itemAcougue, listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButchersRecyclerViewAdapter.AcouguesViewHolder {
        val inflatedView = parent.inflate(R.layout.acougues_list_item)
        inflatedView.tag = viewHolderPosition
        viewHolderPosition++
        return AcouguesViewHolder(inflatedView)
    }

    fun replaceData(listaButcheries: MutableList<Butchery>) {
        items = listaButcheries
        notifyDataSetChanged()
    }

    fun clearData() {
        items.clear()
        notifyDataSetChanged()
    }

    class AcouguesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val TAG = ButchersRecyclerViewAdapter::class.java.simpleName

        private var view : View = itemView
        private var butchery : Butchery? = null
        private var mContext = itemView.context
        private val butchersViewModel = ButchersViewModel()

//        init {
//            itemView.setOnClickListener(this)
//        }
//
//        override fun onClick(view: View?) {
//            butchery?.let { butchersViewModel.updateButchery(it) }
//            Log.d(TAG,"BUTCHERY NAME: ${butchersViewModel.butchery?.butcheryName}")
//
//        }

        fun bindDados(butchery: Butchery, listener: contentListener) {
            this.butchery = butchery
            view.casa_carne_nome.text = butchery.butcheryName
            view.casa_carne_telefone.text = butchery.butcheryPhoneNumber
            view.distance.text = "1.2 Km"
            view.quantidade_cupons.text = "10 Cupons"

            itemView.setOnClickListener {
                listener.onRecyclerItemCliked(butchery)
            }
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

    interface contentListener {
        fun onRecyclerItemCliked(item : Butchery)
    }
}
