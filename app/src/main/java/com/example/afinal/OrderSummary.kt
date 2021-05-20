package com.example.afinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import java.util.*
import kotlin.concurrent.schedule

class OrderSummary : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_summary)
        val goConfirm = findViewById<Button>(R.id.btnReceipt)
        goConfirm.setOnClickListener {
            Toast.makeText(this, "Order Confirmed", Toast.LENGTH_SHORT).show()

        }
    }
}