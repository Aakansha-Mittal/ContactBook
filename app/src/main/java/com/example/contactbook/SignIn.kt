package com.example.contactbook

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val btnSignIn = findViewById<Button>(R.id.btnSignIn)

        btnSignIn.setOnClickListener{
            val etPhone = findViewById<TextInputEditText>(R.id.etPhoneNo)
            val etPsswd = findViewById<TextInputEditText>(R.id.etPassword)

            val phone = etPhone.text.toString()
            val lPsswd = etPsswd.text.toString()

            if(phone.isNotEmpty() and lPsswd.isNotEmpty()) {
                database=FirebaseDatabase.getInstance().getReference("Users")
                database.child(phone).get().addOnSuccessListener {
                    if(it.exists()){
                        val psswd = it.child("password").value.toString()
                        if(lPsswd==psswd){
                            val name = it.child("name").value
                            val i = Intent(this, Feature::class.java)
                            i.putExtra("phone", phone)
                            i.putExtra("nme", name.toString())
                            startActivity(i)
                        }
                        else {
                            Toast.makeText(this, "Password is not correct", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "New user, please sign up", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener{
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }
            }else {
                Toast.makeText(this, "Enter the details", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
