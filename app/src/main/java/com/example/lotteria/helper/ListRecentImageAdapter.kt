package com.example.lotteria.helper

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lotteria.data.Image
import com.example.lotteria.databinding.ItemRowImageBinding

class ListRecentImageAdapter(private val listImages: List<Image>, val count: Int) : RecyclerView.Adapter<ListRecentImageAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    class ListViewHolder(var binding: ItemRowImageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(ItemRowImageBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false))

    override fun onBindViewHolder(viewHolder: ListViewHolder, position: Int) {
        val (title, picture, dateFormat) = listImages[position]
        val bitmap = BitmapFactory.decodeFile(picture)
        viewHolder.binding.judulGambar.text = title
        viewHolder.binding.gambarBakteri.setImageBitmap(bitmap)
        viewHolder.binding.waktuAkses.text = dateFormat
        viewHolder.itemView.setOnClickListener {onItemClickCallback.onItemClicked(listImages[viewHolder.adapterPosition])}
    }

    override fun getItemCount(): Int = count

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Image)
    }
}