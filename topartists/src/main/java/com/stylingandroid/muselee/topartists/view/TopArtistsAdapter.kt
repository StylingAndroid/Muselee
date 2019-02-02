package com.stylingandroid.muselee.topartists.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.stylingandroid.muselee.topartists.R
import com.stylingandroid.muselee.topartists.entities.Artist
import com.stylingandroid.muselee.topartists.entities.Artist.ImageSize

class TopArtistsAdapter(
    private val items: MutableList<Artist> = mutableListOf()
) : RecyclerView.Adapter<TopArtistsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopArtistsViewHolder =
            TopArtistsViewHolder(
                    LayoutInflater.from(parent.context)
                            .inflate(R.layout.item_top_artist, parent, false)
            )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TopArtistsViewHolder, position: Int) {
        items[position].also { artist ->
            holder.bind(
                rank = (position + 1).toString(),
                artistName = artist.name,
                artistImageUrl = artist.images[ImageSize.MEDIUM] ?: artist.images.values.first()
            )
        }
    }

    fun replace(artists: List<Artist>) {
        val difference = DiffUtil.calculateDiff(TopArtistsDiffUtil(items, artists))
        items.clear()
        items += artists
        difference.dispatchUpdatesTo(this)
    }

    private class TopArtistsDiffUtil(
        private val oldList: List<Artist>,
        private val newList: List<Artist>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] === newList[newItemPosition]

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].name == newList[newItemPosition].name
    }
}
