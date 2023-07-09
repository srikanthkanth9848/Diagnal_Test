package com.example.diagntest.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.diagntest.R
import com.example.diagntest.models.ContentItem
import javax.inject.Inject


class RecyclerViewAdapter @Inject constructor(private val clickListener: ClickListener) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private var isSelectedPos: Boolean = false
    private var data = ArrayList<ContentItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_list_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contentItem = data[position]
        holder.txtTitle.text = contentItem.name

        Log.e("RecyclerViewAdapter", "onBindViewHolder: contentItem " + contentItem.name)

        if (data[position].posterImage.equals("poster1.jpg")) {
            //holder.imageView.setImageResource(R.drawable.poster1);
            Glide.with(holder.imageView)
                .load(R.drawable.poster1)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.placeholder_for_missing_posters)
                        .error(R.drawable.placeholder_for_missing_posters))
                .into(holder.imageView)
        } else if (data[position].posterImage.equals("poster2.jpg")) {
            Glide.with(holder.imageView)
                .load(R.drawable.poster2)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.placeholder_for_missing_posters)
                        .error(R.drawable.placeholder_for_missing_posters)
                )
                .into(holder.imageView)
        } else if (data[position].posterImage.equals("poster3.jpg")) {
            Glide.with(holder.imageView)
                .load(R.drawable.poster3)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.placeholder_for_missing_posters)
                        .error(R.drawable.placeholder_for_missing_posters)
                )
                .into(holder.imageView)
        } else if (data[position].posterImage.equals("poster4.jpg")) {
            Glide.with(holder.imageView)
                .load(R.drawable.poster4)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.placeholder_for_missing_posters)
                        .error(R.drawable.placeholder_for_missing_posters)
                )
                .into(holder.imageView)
        } else if (data[position].posterImage.equals("poster5.jpg")) {
            Glide.with(holder.imageView)
                .load(R.drawable.poster5)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.placeholder_for_missing_posters)
                        .error(R.drawable.placeholder_for_missing_posters)
                )
                .into(holder.imageView)
        } else if (data[position].posterImage.equals("poster6.jpg")) {
            Glide.with(holder.imageView)
                .load(R.drawable.poster6)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.placeholder_for_missing_posters)
                        .error(R.drawable.placeholder_for_missing_posters)
                )
                .into(holder.imageView)
        } else if (data[position].posterImage.equals("poster7.jpg")) {
            Glide.with(holder.imageView)
                .load(R.drawable.poster7)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.placeholder_for_missing_posters)
                        .error(R.drawable.placeholder_for_missing_posters)
                )
                .into(holder.imageView)
        } else if (data[position].posterImage.equals("poster8.jpg")) {
            Glide.with(holder.imageView)
                .load(R.drawable.poster8)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.placeholder_for_missing_posters)
                        .error(R.drawable.placeholder_for_missing_posters)
                )
                .into(holder.imageView)
        } else if (data[position].posterImage.equals("poster9.jpg")) {
            Glide.with(holder.imageView)
                .load(R.drawable.poster9)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.placeholder_for_missing_posters)
                        .error(R.drawable.placeholder_for_missing_posters)
                )
                .into(holder.imageView)
        }

        //it is used for click for any item then it will be showing title as name
        holder.relativeLayout.setOnClickListener {
            clickListener.launchIntent(data[position].name)
        }
    }

    override fun getItemCount(): Int {
        Log.e("RecyclerViewAdapter", "getItemCount: contentItem size " + data.size)
        return data.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle: TextView = itemView.findViewById(R.id.textView_title)
        val imageView: ImageView = itemView.findViewById(R.id.imageView_picture)
        val relativeLayout: LinearLayout = itemView.findViewById(R.id.linearLayout)
    }

    interface ClickListener {
        fun launchIntent(title: String?)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: MutableList<ContentItem>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    // method for filtering our recyclerview items.
    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filteredList: ArrayList<ContentItem>) {
        // below line is to add our filtered list in our course array list.
        this.data = filteredList
        // below line is to notify our adapter as change in recycler view data.
        notifyDataSetChanged()
    }
}

