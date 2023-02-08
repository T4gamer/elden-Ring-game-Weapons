package com.asascompany.apitest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.asascompany.apitest.databinding.PostItemBinding
import com.asascompany.apitest.model.Datum

/**
 * Created by Taha Ben Ashur (https://github.com/tahaak67) on 07,Feb,2023
 */
class PostAdapter : ListAdapter<Datum, RecyclerView.ViewHolder>(DIFF_CALLBACK){
    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Datum>() {

            override fun areItemsTheSame(oldItem: Datum, newItem: Datum): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Datum, newItem: Datum): Boolean {
                return oldItem.name == newItem.name || oldItem.id == newItem.id
//                       || oldItem.img == newItem.img
            }

        }
    }
    var onItemClick: ((Datum) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {
        val  binding: PostItemBinding =
            PostItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder){
            val item = getItem(position)
            holder.bind(item)
        }
    }


    inner class ViewHolder(val itemBinding: PostItemBinding):
        RecyclerView.ViewHolder(itemBinding.root) {
        init {
            itemBinding.card.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition))
            }
        }
        fun bind(item: Datum){
            itemBinding.apply {
                ivPicture.load(item.image)
                tvUsername.text = item.name
                tvTitle.text = item.category
                tvContent.text = item.description
            }
        }
    }
}