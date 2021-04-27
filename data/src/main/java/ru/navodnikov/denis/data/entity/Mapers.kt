package ru.navodnikov.denis.data.entity

import ru.navodnikov.denis.domain.entity.ExcursionDomain
import ru.navodnikov.denis.domain.entity.Island
import ru.navodnikov.denis.domain.entity.Order

fun IslandData.mapToDomain(): Island {
    return Island(
        title = title,
        island = island,
        body = body,
        image = image
    )
}

fun ExcursionData.mapToDomain(): ExcursionDomain {
    return ExcursionDomain(
        title = title,
        body = body,
        imageUri = imageUri,
        priceAdult = priceAdult,
        priceChild = priceChild
    )

}

fun OrderData.mapToDomain(): Order {
    return Order(
        id = id,
        typeOfOrder = typeOfOrder,
        imageUri = imageUri,
        title = title,
        numberOfAdult = numberOfAdult,
        numberOfChildren = numberOfChildren,
        date = date,
        price = price,
        comments = comments
    )
}