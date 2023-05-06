package com.example.kdpz_overtry.presentation.adapter

import com.example.kdpz_overtry.R

object ListOfCities {
    val listCity = listOf(
        City("ternopil","https://tobm.org.ua/wp-content/uploads/2021/01/3-63.jpg" ),
        City("dnipro", "https://np.pl.ua/wp-content/uploads/2020/04/dnipro.jpg"),
        City("kyiv","https://bigkyiv.com.ua/wp-content/uploads/2021/09/242306343_4582798148467241_736717668887307731_n-1-800x600.jpg"),
        City("zhmerynka","https://ukrainaincognita.com/wp-content/uploads/files/zhmerynka16_vokzal4_0.jpg"),
        City("yalta","data:image/jpeg;base64,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"),
        City("sumy","https://travels.in.ua/api/Photo/PhotoStreamCIL/830"),
        City("chyhyryn","https://www.navigator-ukraina.com.ua/media/k2/items/cache/88b3960361835836e444d41321daf95e_XL.jpg"),
        City("izmail","https://anga.ua/ifiles/images/Region/Pruchornomorya/Odeska/Izmail/sobor-Izmail.jpg"),

    )
}