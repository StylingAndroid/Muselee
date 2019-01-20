package com.stylingandroid.muselee.topartists.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stylingandroid.muselee.topartists.R
import com.stylingandroid.muselee.topartists.model.Artist
import com.stylingandroid.muselee.topartists.model.ImageSize

class TopArtistsAdapter(
        private val items: List<Artist>
) : RecyclerView.Adapter<TopArtistsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopArtistsViewHolder =
            TopArtistsViewHolder(
                    LayoutInflater.from(parent.context)
                            .inflate(R.layout.item_top_artist, parent, false)
            )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TopArtistsViewHolder, position: Int) {
        holder.bind(position + 1, items[position], ImageSize.MEDIUM)
    }

}
