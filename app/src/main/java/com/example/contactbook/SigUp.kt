package com.example.contactbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SigUp : AppCompatActivity() {

    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sig_up)

        supportActionBar?.hide()

        val btnSignUp = findViewById<Button>(R.id.btnSignUp)
        val tvSignIn = findViewById<TextView>(R.id.tvSignIn)

        btnSignUp.setOnClickListener {
            val etName = findViewById<TextInputEditText>(R.id.etName)
            val etMail = findViewById<TextInputEditText>(R.id.etMail)
            val etPhone = findViewById<TextInputEditText>(R.id.etPhone)
            val etPassword = findViewById<TextInputEditText>(R.id.etPassword)

            val name = etName.text.toString()
            val mail = etMail.text.toString()
            val phone = etPhone.text.toString()
            val psswd = etPassword.text.toString()

            val user = User(name, mail, phone, psswd)
            val id = user.phone
            if(name.isNotEmpty() and mail.isNotEmpty() and phone.isNotEmpty() and psswd.isNotEmpty()){
                database = FirebaseDatabase.getInstance().getReference("Users")
                database.child(id).setValue(user).addOnSuccessListener {
                    etName.text?.clear()
                    etMail.text?.clear()
                    etPhone.text?.clear()
                    etPassword.text?.clear()

                    Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, SignIn::class.java)
                    startActivity(intent)

                }.addOnFailureListener{
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Enter all the details", Toast.LENGTH_SHORT).show()
            }

        }

        tvSignIn.setOnClickListener{
            val i = Intent(this, SignIn::class.java)
            startActivity(i)
        }

    }
}