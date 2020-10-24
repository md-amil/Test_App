package com.example.testapp

import com.example.testapp.Product

val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

val products:ArrayList<Product>  = arrayListOf(
        Product(1, "shoes", "this is best shoes from branded", "$10", "@drawable/shoe"),
        Product(2, "Tshirt", "this is best tshirt from branded", "$05", "@drawable/tshirt"),
        Product(3, "jeans", "this is best shoes from branded", "$20", "@drawable/jeans"),
        Product(4, "Tshirt", "this is best shoes from branded", "$40", "@drawable/tshirt"),
        Product(5, "eye glass", "this is best shoes from branded", "$06", "@drawable/eye"),
        Product(6, "jeans", "this is best shoes from branded", "$80", "@drawable/jeans"),
        Product(7, "pant", "this is best shoes from branded", "$09", "@drawable/pant"),
        Product(8, "shoes", "this is best shoes from branded", "$20", "@drawable/shoe"),
        Product(9, "eye glass", "this is best shoes from branded", "$05", "@drawable/eye"),
        Product(10, "shoes", "this is best shoes from branded", "$10", "@drawable/shoe"),
        Product(11, "eye glass", "this is best shoes from branded", "$100", "@drawable/eye")
)
