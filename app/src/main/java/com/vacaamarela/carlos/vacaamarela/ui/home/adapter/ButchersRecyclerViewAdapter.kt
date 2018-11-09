package com.vacaamarela.carlos.vacaamarela.ui.home.adapter

import android.location.Location
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.vacaamarela.carlos.vacaamarela.R
import com.vacaamarela.carlos.vacaamarela.model.Butchery
import com.vacaamarela.carlos.vacaamarela.utils.inflate
import kotlinx.android.synthetic.main.acougues_list_item.view.*
import com.vacaamarela.carlos.vacaamarela.ui.detail.DescriptionFragment
import com.vacaamarela.carlos.vacaamarela.ui.home.HomeActivity
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.round


class ButchersRecyclerViewAdapter (private var items: MutableList<Butchery>,
                                   private val userLatitude: Double?,
                                   private val userLongitude: Double?)
    : RecyclerView.Adapter<ButchersRecyclerViewAdapter.AcouguesViewHolder>() {

    /** Tag for the log messages  */
    val TAG = ButchersRecyclerViewAdapter::class.java.simpleName
    var viewHolderPosition = 0

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: AcouguesViewHolder, position: Int) {
        val itemAcougue = items[position]
        val colorId = holder.getBackgroundColor()
        holder.changeBackground(colorId)
        holder.bindDados(itemAcougue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcouguesViewHolder {
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

    inner class AcouguesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val TAG = ButchersRecyclerViewAdapter::class.java.simpleName

        private var butchery : Butchery? = null
        private var mContext = itemView.context
        private val BUTCHERY_TAG = "Butchery"

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            //butchery?.let { butchersViewModel.updateButchery(it) }
//            val intent = Intent(mContext, ButcheryDetailActivity::class.java)
//            intent.putExtra(BUTCHERY_TAG,butchery)
//            view?.context?.startActivity(intent)
            fragmentJump(butchery)
            Log.d("CodeAndroidLocation","URecyclerView ser latitude: $userLatitude longitude: $userLongitude butcher long: ${butchery?.longitude} lat: ${butchery?.latitude}")
        }

        fun bindDados(butchery: Butchery) {
            this.butchery = butchery
            itemView.casa_carne_nome.text = butchery.name
            itemView.casa_carne_telefone.text = butchery.phoneNumber
            itemView.quantidade_cupons.text = "10 Cupons"
            itemView.distance.text =  mContext.getString(R.string.km, butchery.distanceFromUser)
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

        private fun fragmentJump(mItemSelected: Butchery?) {
            val mFragment = DescriptionFragment()
            //mBundle = Bundle()
            //mBundle.putParcelable("item_selected_key", mItemSelected)
            //mFragment.setArguments(mBundle)
            switchContent(R.id.main_viewpager, mFragment)
        }

        private fun switchContent(id: Int, fragment: Fragment) {
            if (mContext == null)
                return
            if (mContext is HomeActivity) {
                val homeActivity = mContext as HomeActivity
                homeActivity.switchContent(id, fragment)
            }

        }
    }
}
