package com.stylingandroid.muselee.topartists.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stylingandroid.muselee.topartists.R
import com.stylingandroid.muselee.topartists.model.Artist
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.stylingandroid.muselee.topartists.model.ImageSize

class TopArtistsViewHolder(
    item: View,
    private val rankView: TextView = item.findViewById(R.id.rank),
    private val imageView: ImageView = item.findViewById(R.id.image),
    private val nameView: TextView = item.findViewById(R.id.name)
) : RecyclerView.ViewHolder(item) {

    fun bind(rank: Int, item: Artist, imageSize: ImageSize) {
        rankView.text = rank.toString()
        nameView.text = item.name
        Glide.with(imageView)
            .load(item.images[imageSize])
            .transition(withCrossFade())
            .into(imageView)
    }
}
