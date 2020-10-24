package com.example.testapp

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.testapp.App
import com.example.testapp.R

class ProductDetailFragment : Fragment() {
    lateinit var productImage:ImageView
    lateinit var productName:TextView
    lateinit var productPrize:TextView
    lateinit var productDescription:TextView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

         val root = inflater.inflate(R.layout.fragment_product_detail, container, false)
        productImage = root.findViewById(R.id.product_image)
        productName = root.findViewById(R.id.product_txt_name)
        productPrize = root.findViewById(R.id.product_txt_price)
        productDescription = root.findViewById(R.id.product_txt_long_description)
        val product = App.currentProduct
        if(product!=null){
            productName.text = product.name
            productPrize.text = product.prize
            productDescription.text = product.des
            val imageResource: Int = requireContext().resources.getIdentifier(product.uri, null, requireContext().packageName)
            val res: Drawable = requireContext().resources.getDrawable(imageResource)
            productImage.setImageDrawable(res)
        }

        return root
    }

}