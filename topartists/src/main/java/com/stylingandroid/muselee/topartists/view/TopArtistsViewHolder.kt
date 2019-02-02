package com.stylingandroid.muselee.topartists.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.stylingandroid.muselee.topartists.R

class TopArtistsViewHolder(
    item: View,
    private val rankView: TextView = item.findViewById(R.id.rank),
    private val imageView: ImageView = item.findViewById(R.id.image),
    private val nameView: TextView = item.findViewById(R.id.name)
) : RecyclerView.ViewHolder(item) {

    fun bind(rank: String, artistName: String, artistImageUrl: String) {
        rankView.text = rank
        nameView.text = artistName
        Glide.with(imageView)
            .load(artistImageUrl)
            .transition(withCrossFade())
            .into(imageView)
    }
}
