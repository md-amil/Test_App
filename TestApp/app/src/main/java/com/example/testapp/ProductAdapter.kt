package com.example.testapp

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ProductAdapter (private val context: Context, private val products:ArrayList<Product>, val listener: IProduct): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.product_item, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position],position)
    }

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.product_image)
        val productTxtName:TextView = itemView.findViewById(R.id.product_txt_name)
        val productTxtPrice:TextView = itemView.findViewById(R.id.product_txt_price)
        val productTxtShortDescription:TextView = itemView.findViewById(R.id.product_txt_short_description)
        fun bind(p: Product, position: Int) {
            productTxtName.text = p.name
            productTxtPrice.text = p.prize
            productTxtShortDescription.text = p.des
            val imageResource: Int = context.resources.getIdentifier(p.uri, null, context.packageName)
            val res: Drawable = context.resources.getDrawable(imageResource)
            productImage.setImageDrawable(res)
            itemView.setOnClickListener {
                App.currentProduct = p
                listener.onclick(position)
            }
        }
    }
}