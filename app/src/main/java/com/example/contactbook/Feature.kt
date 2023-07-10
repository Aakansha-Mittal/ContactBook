package com.example.contactbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Feature : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature)

        val tvNew = findViewById<TextView>(R.id.tvNew)
        val tvList = findViewById<TextView>(R.id.tvList)

        tvNew.setOnClickListener {
            val i = Intent(this, Add_Contact::class.java)
            startActivity(i)
        }

        tvList.setOnClickListener {
            val intent = Intent(this, ContactList::class.java)
            startActivity(intent)
        }

    }
}