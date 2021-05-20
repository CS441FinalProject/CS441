package com.example.afinal
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.afinal.Product
import com.squareup.picasso.Picasso
class CartAdapter(private val products: ArrayList<Product>) : RecyclerView.Adapter<CartAdapter.ViewHolder>(){
    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        holder.title.text = products[position].title
        holder.price.text = ("$"+ products[position].price.toString())
        Picasso.get().load(products[position].photoUrl).into(holder.image)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_row, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount() = products.size



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.photo)
        val title: TextView = itemView.findViewById(R.id.title)
        val price: TextView = itemView.findViewById(R.id.price)
    }
}