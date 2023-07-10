package com.example.contactbook

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Add_Contact : AppCompatActivity() {

    lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        val phoneDb = intent.getStringExtra("phone").toString()

        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etMail = findViewById<TextInputEditText>(R.id.etMail)
        val etPhone = findViewById<TextInputEditText>(R.id.etPhoneNo)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        btnAdd.setOnClickListener{
            val name = etName.text.toString()
            val phone = etPhone.text.toString()
            val mail = etMail.text.toString()

            if(name.isNotEmpty() and phone.isNotEmpty() ){

                val contactDetails = Contact(name, phone, mail)
                database=FirebaseDatabase.getInstance().getReference(phoneDb)
                database.child(phone).get().addOnSuccessListener {
                    if(it.exists()){
                        Toast.makeText(this, "Contact added already", Toast.LENGTH_SHORT).show()
                    } else {
                        database.child(phone).setValue(contactDetails).addOnSuccessListener {
                            val dialog = Dialog(this)
                            dialog.setContentView(R.layout.alert_box)
                            val btnOk = dialog.findViewById<Button>(R.id.btnOk)
                            dialog.show()
                            btnOk.setOnClickListener{
                                dialog.dismiss()
                                finish()
                            }
                        }. addOnFailureListener {
                            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            }
            else {
                Toast.makeText(this, "Enter the details", Toast.LENGTH_SHORT).show()
            }
        }

    }
}