package com.example.bellaonojie.data

import com.example.bellaonojie.models.Cart
import com.example.bellaonojie.models.Item
import com.example.bellaonojie.models.User
import kotlin.random.Random

fun menuItemsList(): List<Item> {
    return listOf(
            Item(
                    id = Random.nextLong(),
                    name = "Veggie tomato mix",
                    price = 12,
                    image = "https://cdn.pixabay.com/photo/2017/01/22/19/20/pizza-2000614_1280.jpg",
                    category = "Foods",
            ),
            Item(
                    id = Random.nextLong(),
                    name = "Veggie tomato mix",
                    price = 14,
                    image = "https://cdn.pixabay.com/photo/2019/01/29/18/05/burger-3962997_1280.jpg",
                    category = "Foods",
            ),
            Item(
                    id = Random.nextLong(),
                    name = "Veggie tomato mix",
                    price = 20,
                    image = "https://cdn.pixabay.com/photo/2015/03/26/09/39/fried-chicken-690039_1280.jpg",
                    category = "Foods",
            ),
            Item(
                    id = Random.nextLong(),
                    name = "Veggie tomato mix",
                    price = 11,
                    image = "https://cdn.pixabay.com/photo/2016/03/05/19/02/abstract-1238247_1280.jpg",
                    category = "Sauce"
            )
    )
}

fun cartItemsList(): List<Cart> {
    return listOf(
            Cart(
                    id = Random.nextLong(),
                    name = "Veggie tomato mix",
                    price = 12,
                    image = "https://cdn.pixabay.com/photo/2017/01/22/19/20/pizza-2000614_1280.jpg",
                    category = "Foods",
                    quantity = 2,
            ),
            Cart(
                    id = Random.nextLong(),
                    name = "Veggie tomato mix",
                    price = 14,
                    image = "https://cdn.pixabay.com/photo/2019/01/29/18/05/burger-3962997_1280.jpg",
                    category = "Foods",
                    quantity = 3,
            ),
            Cart(
                    id = Random.nextLong(),
                    name = "Veggie tomato mix",
                    price = 20,
                    image = "https://cdn.pixabay.com/photo/2015/03/26/09/39/fried-chicken-690039_1280.jpg",
                    category = "Foods",
                    quantity = 2,
            ),
    )
}

fun userDetails(): User {
    return User(
            id = 101,
            username = "Alex Watts",
            email = "placeholder@gmail.com",
            image = "https://i.pinimg.com/originals/ae/ec/c2/aeecc22a67dac7987a80ac0724658493.jpg",
            address = "Al Barsha-Dubai-United Arab Emirates",
            phone = "+971555666655",
            password = "123",
            created = System.currentTimeMillis(),
            cart_id = 101,
            cart_total = 200
    )
}
