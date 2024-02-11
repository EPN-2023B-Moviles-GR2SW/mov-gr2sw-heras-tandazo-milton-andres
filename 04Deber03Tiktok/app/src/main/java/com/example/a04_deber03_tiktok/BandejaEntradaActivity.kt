package com.example.a04_deber03_tiktok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a04_deber03_tiktok.adapter.FriendAdapter
import com.example.a04_deber03_tiktok.databinding.ActivityBandejaEntradaBinding


class BandejaEntradaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBandejaEntradaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBandejaEntradaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        binding.bottomNavBar.setOnItemSelectedListener {menuItem->
            when(menuItem.itemId){
                R.id.bottom_menu_home->{
                    startActivity(Intent(this,MainActivity::class.java))
                }
                R.id.bottom_menu_add_video->{
                    Toast.makeText(this, "Agregar videos", Toast.LENGTH_SHORT).show()
                }
                R.id.bottom_menu_profile->{
                    Toast.makeText(this, "Bandeja", Toast.LENGTH_SHORT).show()

                    startActivity(Intent(this,BandejaEntradaActivity::class.java))
                }
            }
            false
        }
    }
    fun initRecyclerView(){
        val manager =  LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this,manager.orientation)
        binding.recyclerFriend.layoutManager = manager
        binding.recyclerFriend.adapter = FriendAdapter(FriendProvider.friendList)
        binding.recyclerFriend.addItemDecoration(decoration)
    }
}