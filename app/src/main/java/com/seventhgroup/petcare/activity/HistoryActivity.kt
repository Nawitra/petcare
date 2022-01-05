package com.seventhgroup.petcare.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.*
import com.seventhgroup.petcare.R
import com.seventhgroup.petcare.adapter.HistoryAdapter
import com.seventhgroup.petcare.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val historyNav: BottomNavigationView = findViewById(R.id.bottom_navigation_history)
        historyNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.btn_profile -> {
                    val mIntent = Intent(this@HistoryActivity, ProfileActivity::class.java)
                    startActivity(mIntent)
                    finish()
                }
                R.id.btn_history -> {
                    Toast.makeText(this, "You are already in the History SCREEN", Toast.LENGTH_SHORT).show()
                }
                R.id.btn_setting -> {
                    val mIntent = Intent(this@HistoryActivity, SettingActivity::class.java)
                    startActivity(mIntent)
                    finish()
                }
            }
            true}

        binding.rvHistoryList.setHasFixedSize(true)
        getDateData()
    }

    override fun onStop() {
        super.onStop()
        startService(Intent(this, NotificationService::class.java))
    }

    private fun getDateData() {
        var temp = ArrayList<String>()
        val rootRef = FirebaseDatabase.getInstance().getReference("DESPRO007")
        rootRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (dateSnapshot in snapshot.children){
                        temp.add(dateSnapshot.key!!)
                        Log.d("data", temp[temp.size-1])
                        Log.d("data now", temp.size.toString())
                    }
                }
                val listHistoryAdapter = HistoryAdapter()
                with(binding.rvHistoryList) {
                    adapter = listHistoryAdapter
                    layoutManager =LinearLayoutManager(this@HistoryActivity)
                    listHistoryAdapter.setData(temp)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}