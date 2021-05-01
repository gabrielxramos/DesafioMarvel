package br.com.digitalhouse.desafiomarvel.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.digitalhouse.desafiomarvel.R
import br.com.digitalhouse.desafiomarvel.remote.model.ComicsResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.comic_item.view.*
import java.text.FieldPosition

class ListComicAdapter (
    var comicResponse: ComicsResponse?,
    private var clickListener: OnItemClickListener
): RecyclerView.Adapter<ListComicAdapter.ComicViewHolder>(){

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    class ComicViewHolder(itemView: View, listener: OnItemClickListener): RecyclerView.ViewHolder(itemView){
        val comicZoom: ImageView = itemView.comic_item
        val comicNumber: TextView = itemView.comic_number

        init {
            itemView.setOnClickListener{
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    listener.onItemClick(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.comic_item, parent, false)
        return ComicViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val currentItem = comicResponse?.data?.results?.get(position)
        val imgUrl ="${currentItem?.thumbnail?.path}.${currentItem?.thumbnail?.extension}"
        val numberComic = "#${currentItem?.issueNumber?.toInt()}"

        holder.comicNumber.text = numberComic
        Picasso.get().load(imgUrl).fit().centerInside().into(holder.comicZoom)
    }

    override fun getItemCount() = comicResponse?.data?.results?.size?: 0

}

