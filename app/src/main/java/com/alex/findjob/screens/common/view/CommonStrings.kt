package com.alex.findjob.screens.common.view

import com.alex.findjob.localization.NonTranslatable
import com.alex.findjob.localization.Translatable
import java.util.Locale

// TODO:
val ru = NonTranslatable("RU")
val en = NonTranslatable("EN")

val yes = Translatable(
    "Да",
    hashMapOf(
        Locale.ENGLISH to "Yes"
    )
)

val no = Translatable(
    "Нет",
    hashMapOf(
        Locale.ENGLISH to "No"
    )
)

val ok = Translatable(
    "Ок",
    hashMapOf(
        Locale.ENGLISH to "Ok"
    )
)

val snackbarErrorDefault = Translatable(
    "Произошла ошибка, попробуйте еще раз",
    hashMapOf(
        Locale.ENGLISH to "Произошла ошибка, попробуйте еще раз"
    )
)

val errorUnknownHost = Translatable(
    "Отсутствует соединение с интернетом",
    hashMapOf(
        Locale.ENGLISH to "Отсутствует соединение с интернетом"
    )
)

val cancel = Translatable(
    "Отмена",
    hashMapOf(
        Locale.ENGLISH to "Cancel"
    )
)

