package com.example.afinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        val products = arrayListOf<Product>()


        products.add(Product("CSUSM Hat", "https://bkstr.scene7.com/is/image/Bkstr/1259-CLEANUP-MSCT-Black?", 24.99))
        products.add(Product("CSUSM Socks", "https://bkstr.scene7.com/is/image/Bkstr/1259-FLC2212-WDMK-White?", 11.99))
        products.add(Product("CSUSM Jacket", "https://bkstr.scene7.com/is/image/Bkstr/1259-CS2071-P1118153-Granite-Heather", 43.99))

        val goCheckout = findViewById<Button>(R.id.btnCheckActivity)

        goCheckout.setOnClickListener {
            val intentConstraint = Intent(this, CheckoutActivity::class.java);
            startActivity(intentConstraint);
        }
//https://imgur.com/zz7y9Eh
        val recycler_view = findViewById<RecyclerView>(R.id.recycler_viewCart);
        recycler_view.apply {
            layoutManager = GridLayoutManager(this@CartActivity, 2)
            adapter = CartAdapter(products)

        }

    }
}
