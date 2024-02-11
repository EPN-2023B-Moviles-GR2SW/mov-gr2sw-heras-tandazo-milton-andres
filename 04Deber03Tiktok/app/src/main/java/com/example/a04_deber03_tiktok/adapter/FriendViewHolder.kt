package com.example.a04_deber03_tiktok.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a04_deber03_tiktok.Friend
import com.example.a04_deber03_tiktok.R
import com.example.a04_deber03_tiktok.databinding.ItemFriendBinding

class FriendViewHolder (view: View): RecyclerView.ViewHolder(view){

    val binding = ItemFriendBinding.bind(view)


    fun render(friendModel: Friend){
        binding.tvFriendName.text = friendModel.user
        binding.tvEstado.text = friendModel.estado
        binding.tvMensaje.text = friendModel.mensaje
        Glide.with(binding.ivFriend.context).load(friendModel.photo).into(binding.ivFriend)


    }
}