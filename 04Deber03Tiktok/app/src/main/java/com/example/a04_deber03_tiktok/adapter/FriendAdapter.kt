package com.example.a04_deber03_tiktok.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a04_deber03_tiktok.Friend
import com.example.a04_deber03_tiktok.R

class FriendAdapter (private val friendList:List<Friend>): RecyclerView.Adapter<FriendViewHolder>(){
    //Coger listado y convertirlo en recycler view

    //Devolver al viewHolder , del objeto que contiene coger los atributos y pintarlos, pasar el layout que debe modificar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context) //Sacar contexto cuando no se esta en activity, sacar de cualqueira de las vistas VIEWGROUP, A recycler view no pasarle el contexto
        return FriendViewHolder(layoutInflater.inflate(R.layout.item_friend, parent,false))
    }

    override fun getItemCount(): Int {
        return friendList.size //Devolver el tamaño del listado
    }

    //Pasar por cada uno de los items y llamar al render del viewHolder
    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val item = friendList[position]
        holder.render(item)

    }
}