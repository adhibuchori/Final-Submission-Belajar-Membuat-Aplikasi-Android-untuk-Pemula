package com.adhibuchori.finalsubmission_simpleandroidapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adhibuchori.finalsubmission_simpleandroidapp.databinding.ItemRowCharacterBinding
import com.bumptech.glide.Glide

class ListCharacterAdapter(private val listCharacter: ArrayList<Character>) :
    RecyclerView.Adapter<ListCharacterAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listCharacter.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, category, image) = listCharacter[position]

        Glide.with(holder.itemView.context)
            .load(image)
            .into(holder.binding.imgItemCharacter)

        holder.binding.tvItemName.text = name
        holder.binding.tvItemCategory.text = category

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listCharacter[holder.adapterPosition])
        }
    }

    class ListViewHolder(var binding: ItemRowCharacterBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Character)
    }
}