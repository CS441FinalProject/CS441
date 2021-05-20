package com.example.afinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.concurrent.schedule

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        val products = arrayListOf<Product>()


            products.add(Product("Campus T-Shirt", "https://i.imgur.com/zz7y9Eh.jpg"/*"https://placeimg.com/200/200/people"*/, 19.95))
            products.add(Product("Campus Shorts", "https://bkstr.scene7.com/is/image/Bkstr/1259-C81255-P2201508-Royal-Blue?", 31.99))
            products.add(Product("Asus Laptop", "https://m.media-amazon.com/images/I/81aIviy7YZL._AC_SS450_.jpg", 2291.99))
            products.add(Product("CSUSM Jacket", "https://bkstr.scene7.com/is/image/Bkstr/1259-CS2071-P1118153-Granite-Heather", 43.99))
        products.add(Product("CSUSM Sweats", "https://bkstr.scene7.com/is/image/Bkstr/1259-CP2071-P2110232-Granite-Heather?", 35.99))
        products.add(Product("CSUSM Hat", "https://bkstr.scene7.com/is/image/Bkstr/1259-CLEANUP-MSCT-Black?", 24.99))
        products.add(Product("CSUSM Socks", "https://bkstr.scene7.com/is/image/Bkstr/1259-FLC2212-WDMK-White?", 11.99))
        products.add(Product("CSUSM Tank", "https://bkstr.scene7.com/is/image/Bkstr/1259-C6009-P3315035-Oxford?", 25.99))
        products.add(Product("CSUSM Sherpa", "https://bkstr.scene7.com/is/image/Bkstr/1259-Q10-WM-Smoke", 59.99))
        products.add(Product("CSUSM V-Neck", "https://bkstr.scene7.com/is/image/Bkstr/1259-C6001-P1135878-Royal-Blue?", 25.99))
        products.add(Product("CSUSM Pajamas", "https://bkstr.scene7.com/is/image/Bkstr/1259-XTE-MSCT1-Royal-Black?", 33.99))
        products.add(Product("CSUSM Scrunchie", "https://bkstr.scene7.com/is/image/Bkstr/1259-PC400-1886C", 8.99))
        products.add(Product("MacBook Pro", "https://webobjects2.cdw.com/is/image/CDW/5837967?", 2799.99))
        products.add(Product("CSUSM Banner", "https://bkstr.scene7.com/is/image/Bkstr/1259-A7447V-FSNWA-Navy?", 35.99))
        products.add(Product("CSUSM Flag", "https://m.media-amazon.com/images/I/61eMHDRAh9L._AC_.jpg", 10.99))
        products.add(Product("CSUSM Keychain", "https://bkstr.scene7.com/is/image/Bkstr/1259-CG1340-INIT-Blue?", 6.99))


        val goCart = findViewById<Button>(R.id.btnCartActivity)

        goCart.setOnClickListener {
            val intentConstraint = Intent(this, CartActivity::class.java);
            Timer().schedule(500) {
                startActivity(intentConstraint);
            }
        }

//https://imgur.com/zz7y9Eh
        val recycler_view = findViewById<RecyclerView>(R.id.recycler_view);
        recycler_view.apply {
            layoutManager = GridLayoutManager(this@ProductActivity, 2)
            adapter = ProductsAdapter(products)

        }

    }
}