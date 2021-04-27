package ru.navodnikov.denis.domain.entity

import ru.navodnikov.denis.karagatantour.entity.Excursion

fun Excursion.mapToDomain(): ExcursionDomain {
    return ExcursionDomain(
        title = title,
        body = body,
        imageUri = imageUri,
        priceAdult = priceAdult,
        priceChild = priceChild
    )
}

fun ExcursionDomain.mapToDomain(): Excursion {
    return Excursion(
        title = title,
        body = body,
        imageUri = imageUri,
        priceAdult = priceAdult,
        priceChild = priceChild
    )
}