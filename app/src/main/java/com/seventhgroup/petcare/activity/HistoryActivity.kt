package com.seventhgroup.petcare.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.seventhgroup.petcare.R
import com.seventhgroup.petcare.adapter.HistoryAdapter

class HistoryActivity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    //private lateinit var collectionRecyclerView: CollectionReference
    private lateinit var rvHistory : RecyclerView
    private lateinit var btnRefresh : Button
    private var historyArrayList : ArrayList<String> = arrayListOf()

    var dateAdapter: DateAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        supportActionBar?.hide()

//        btnRefresh = findViewById(R.id.button_refresh)
        rvHistory = findViewById(R.id.rv_historyList)
        rvHistory.setHasFixedSize(true)

        getDateData()
/*
        btnRefresh.setOnClickListener {
            getDateData()
            showRecyclerList()
            historyArrayList.add("Shit")
            Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT).show()
            Toast.makeText(this, historyArrayList.toString(), Toast.LENGTH_SHORT).show()
        }*/

        historyArrayList.add("Monday, January 03 2022 17:51:50")
        historyArrayList.add("Monday, January 03 2022 23:27:17")
        showRecyclerList()


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
    }

    private fun getDateData() {
        val myRef = FirebaseDatabase.getInstance().getReference("DEPRO006")
        myRef.setValue("Hello, World!")

        myRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                lateinit var date: String
                for (i in snapshot.children){
                    date = i.key.toString()
                    historyArrayList.add(i.key!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                historyArrayList.add("error")
            }
        })

    }

    private fun showRecyclerList() {
        rvHistory.layoutManager = LinearLayoutManager(this)
        val listHistoryAdapter = HistoryAdapter(historyArrayList)
        rvHistory.adapter = listHistoryAdapter
    }
}