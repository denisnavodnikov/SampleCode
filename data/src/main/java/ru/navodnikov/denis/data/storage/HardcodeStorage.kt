package ru.navodnikov.denis.data.storage

import ru.navodnikov.denis.data.R
import ru.navodnikov.denis.data.entity.*
import ru.navodnikov.denis.domain.entity.IslandEnum

class HardcodeStorage : Storage {
    private val listOfContacts = arrayOf(
        "WhatsApp",
        "Телефон",
        "E-mail",
        "Telegram",
        "Viber"
    )
    private val listOfUrlsCebu = arrayOf(
        "/android_asset/cebu_excursions/whalesharks_cebu.jpg",
        "/android_asset/cebu_excursions/kawasan.jpg",
        "/android_asset/cebu_excursions/city_tour_cebu.jpg",
        "/android_asset/cebu_excursions/sia_trip_cebu.jpg",
        "/android_asset/cebu_excursions/safari.jpg",
        "/android_asset/cebu_excursions/camotes.jpg",
        "/android_asset/cebu_excursions/bohol.jpg",
        "/android_asset/cebu_excursions/moalboal.jpg"
    )
    private val listOfUrlsLuzon = arrayOf(
        "/android_asset/luzon_excursions/banaua.jpg",
        "/android_asset/luzon_excursions/city_tour_manila.jpg",
        "/android_asset/luzon_excursions/corregiador.jpg",
        "/android_asset/luzon_excursions/hidden_valey.jpg",
        "/android_asset/luzon_excursions/pangsahan.jpg"
    )
    private val listOfUrlsBoracay = arrayOf(
        "/android_asset/boracay_excursions/panay.jpg",
        "/android_asset/boracay_excursions/sia_trip_boracay.jpg",
        "/android_asset/boracay_excursions/vertolet.jpg"
    )
    private val listOfUrlsBohol = arrayOf(
        "/android_asset/bohol_excursions/balikasak.jpg",
        "/android_asset/bohol_excursions/city_tour_bohol.jpg",
        "/android_asset/bohol_excursions/fireflys_bohol.jpg",
        "/android_asset/bohol_excursions/whalesharcks_bohol.jpg"
    )
    private val listOfUrlsPalavan = arrayOf(
        "/android_asset/palawan_excursions/el_nido.jpg",
        "/android_asset/palawan_excursions/fireflys_palavan.jpg",
        "/android_asset/palawan_excursions/plyazh_nakpan.jpg",
        "/android_asset/palawan_excursions/puerto_princessa.jpg"
    )
    private val listOfUrlsNegros = arrayOf(
        "/android_asset/negros_excursions/apo.jpg",
        "/android_asset/negros_excursions/casororo.jpg",
        "/android_asset/negros_excursions/city_tour_negros.jpg",
        "/android_asset/negros_excursions/twin_lakes.jpg",
        "/android_asset/negros_excursions/whalesharcks_negros.jpg"
    )

    private val arrayOfIslandsTitles = arrayOf(
        R.string.luzon,
        R.string.cebu,
        R.string.boraсay,
        R.string.bohol,
        R.string.palavan,
        R.string.negros
    )

    override fun getListOfExcursionCebu(): List<ExcursionData> {
        val listOfTitles = arrayOf(
            R.string.whalesharks_cebu,
            R.string.kawasan,
            R.string.city_tour_cebu,
            R.string.sia_trip_cebu,
            R.string.safari,
            R.string.camotes,
            R.string.bohol_cebu,
            R.string.moalboal
        )
        val listOfBodies = arrayOf(
            R.string.whalesharks_cebu_body,
            R.string.kawasan_body,
            R.string.city_tour_cebu_body,
            R.string.sia_trip_cebu_body,
            R.string.safari_body,
            R.string.camotes_body,
            R.string.bohol_cebu_body,
            R.string.moalboal_body
        )

        val listOfPriceAdultCebu = arrayOf(
            WHALESHARKS_CEBU_ADULTS,
            KAWASAN_CEBU_ADULTS,
            CITY_TOUR_CEBU_ADULTS,
            SAFARI_CEBU_ADULTS,
            SIA_TRIP_CEBU_ADULTS,
            CAMOTES_CEBU_ADULTS,
            BOHOL_CEBU_ADULTS,
            MOALBOAL_CEBU_ADULTS
        )

        val listOfPriceChildCebu = arrayOf(
            WHALESHARKS_CEBU_CHILD,
            KAWASAN_CEBU_CHILD,
            CITY_TOUR_CEBU_CHILD,
            SAFARI_CEBU_CHILD,
            SIA_TRIP_CEBU_CHILD,
            CAMOTES_CEBU_CHILD,
            BOHOL_CEBU_CHILD,
            MOALBOAL_CEBU_CHILD
        )

        val listOfExcursions = ArrayList<ExcursionData>()
        for (index in listOfTitles.indices) {
            listOfExcursions.add(
                ExcursionData(
                    title = listOfTitles[index],
                    imageUrl = listOfUrlsCebu[index],
                    body = listOfBodies[index],
                    priceAdult = listOfPriceAdultCebu[index],
                    priceChild = listOfPriceChildCebu[index]
                )
            )
        }
        return listOfExcursions
    }

    override fun getListOfExcursionLuzon(): List<ExcursionData> {
        val listOfTitles = arrayOf(
            R.string.banaua,
            R.string.city_tour_manila,
            R.string.corregiador,
            R.string.hidden_valey,
            R.string.pangsahan

        )
        val listOfBodies = arrayOf(
            R.string.banaua_body,
            R.string.city_tour_manila_body,
            R.string.corregiador_body,
            R.string.hidden_valey_body,
            R.string.pangsahan_body
        )

        val listOfPriceAdultLuzon = arrayOf(
            BANAUA_ADULT,
            CITY_TOUR_MANILA_ADULT,
            CORREGIADOR_ADULT,
            HIDDEN_VALEY_ADULT,
            PANGSAHAN_ADULT
        )

        val listOfPriceChildLuzon = arrayOf(
            BANAUA_CHILD,
            CITY_TOUR_MANILA_CHILD,
            CORREGIADOR_CHILD,
            HIDDEN_VALEY_CHILD,
            PANGSAHAN_CHILD
        )

        val listOfExcursions = ArrayList<ExcursionData>()
        for (index in listOfTitles.indices) {
            listOfExcursions.add(
                ExcursionData(
                    title = listOfTitles[index],
                    imageUrl = listOfUrlsLuzon[index],
                    body = listOfBodies[index],
                    priceAdult = listOfPriceAdultLuzon[index],
                    priceChild =listOfPriceChildLuzon[index]
                )
            )
        }
        return listOfExcursions
    }

    override fun getListOfExcursionPalavan(): List<ExcursionData> {
        val listOfTitles = arrayOf(
            R.string.el_nido,
            R.string.fireflys_palavan,
            R.string.plyazh_nakpan,
            R.string.puerto_princessa
        )
        val listOfBodies = arrayOf(
            R.string.el_nido_body,
            R.string.fireflys_palavan_body,
            R.string.plyazh_nakpan_body,
            R.string.puerto_princessa_body
        )

        val listOfPriceAdultPalavan = arrayOf(
            EL_NIDO_ADULT,
            FIREFLYS_PALAVAN_ADULT,
            PLYAZH_NAKPAN_ADULT,
            PUERTO_PRINCESSA_ADULT
        )

        val listOfPriceChildPalavan = arrayOf(
            EL_NIDO_CHILD,
            FIREFLYS_PALAVAN_CHILD,
            PLYAZH_NAKPAN_CHILD,
            PUERTO_PRINCESSA_CHILD
        )

        val listOfExcursions = ArrayList<ExcursionData>()
        for (index in listOfTitles.indices) {
            listOfExcursions.add(
                ExcursionData(
                    title = listOfTitles[index],
                    imageUrl = listOfUrlsPalavan[index],
                    body = listOfBodies[index],
                    priceAdult = listOfPriceAdultPalavan[index],
                    priceChild = listOfPriceChildPalavan[index]
                )
            )
        }
        return listOfExcursions
    }

    override fun getListOfExcursionBoracay(): List<ExcursionData> {
        val listOfTitles = arrayOf(
            R.string.panay,
            R.string.sia_trip_boracay,
            R.string.vertolet
        )
        val listOfBodies = arrayOf(
            R.string.panay_body,
            R.string.sia_trip_boracay_body,
            R.string.vertolet_body
        )

        val listOfPriceAdultBoracay = arrayOf(
            PANAY_ADULT,
            SIA_TRIP_BORACAY_ADULT,
            VERTOLET_ADULT
        )

        val listOfPriceChildBoracay = arrayOf(
            PANAY_CHILD,
            SIA_TRIP_BORACAY_CHILD,
            VERTOLET_CHILD
        )

        val listOfExcursions = ArrayList<ExcursionData>()
        for (index in listOfTitles.indices) {
            listOfExcursions.add(
                ExcursionData(
                    title = listOfTitles[index],
                    imageUrl = listOfUrlsBoracay[index],
                    body = listOfBodies[index],
                    priceAdult = listOfPriceAdultBoracay[index],
                    priceChild = listOfPriceChildBoracay[index]
                )
            )
        }
        return listOfExcursions
    }

    override fun getListOfExcursionBohol(): List<ExcursionData> {
        val listOfTitles = arrayOf(
            R.string.balikasak,
            R.string.city_tour_bohol,
            R.string.fireflys_bohol,
            R.string.whalesharck_bohol
        )
        val listOfBodies = arrayOf(
            R.string.balikasak_body,
            R.string.city_tour_bohol_body,
            R.string.fireflys_bohol_body,
            R.string.whalesharck_bohol_body
        )

        val listOfPriceAdultBohol = arrayOf(
            BALIKASAK_ADULT,
            CITY_TOUR_BOHOL_ADULT,
            FIREFLYS_BOHOL_ADULT,
            WHALESHARCK_BOHOL_ADULT
        )

        val listOfPriceChildBohol = arrayOf(
            BALIKASAK_CHILD,
            CITY_TOUR_BOHOL_CHILD,
            FIREFLYS_BOHOL_CHILD,
            WHALESHARCK_BOHOL_CHILD
        )

        val listOfExcursions = ArrayList<ExcursionData>()
        for (index in listOfTitles.indices) {
            listOfExcursions.add(
                ExcursionData(
                    title = listOfTitles[index],
                    imageUrl = listOfUrlsBohol[index],
                    body = listOfBodies[index],
                    priceAdult = listOfPriceAdultBohol[index],
                    priceChild = listOfPriceChildBohol[index]
                )
            )
        }
        return listOfExcursions
    }

    override fun getListOfExcursionNegros(): List<ExcursionData> {
        val listOfTitles = arrayOf(
            R.string.apo,
            R.string.casororo,
            R.string.city_tour_negros,
            R.string.twin_lakes,
            R.string.whalesharck_negros
        )
        val listOfBodies = arrayOf(
            R.string.apo_body,
            R.string.casororo_body,
            R.string.city_tour_negros_body,
            R.string.twin_lakes_body,
            R.string.whalesharck_negros_body
        )

        val listOfPriceAdultNegros = arrayOf(
            APO_ADULT,
            CASORORO_ADULT,
            CITY_TOUR_NEGROS_ADULT,
            TWIN_LAKES_ADULT,
            WHALESHARCK_NEGROS_ADULT
        )

        val listOfPriceChildNegros = arrayOf(
            APO_CHILD,
            CASORORO_CHILD,
            CITY_TOUR_NEGROS_CHILD,
            TWIN_LAKES_CHILD,
            WHALESHARCK_NEGROS_CHILD
        )

        val listOfExcursions = ArrayList<ExcursionData>()
        for (index in listOfTitles.indices) {
            listOfExcursions.add(
                ExcursionData(
                    title = listOfTitles[index],
                    imageUrl = listOfUrlsNegros[index],
                    body = listOfBodies[index],
                    priceAdult = listOfPriceAdultNegros[index],
                    priceChild = listOfPriceChildNegros[index]
                )
            )
        }
        return listOfExcursions
    }

    override fun getListOfIslands(): List<IslandData> {

        val arrayOfIslands = arrayOf(
            IslandEnum.LUZON,
            IslandEnum.CEBU,
            IslandEnum.BORACAY,
            IslandEnum.BOHOL,
            IslandEnum.PALAVAN,
            IslandEnum.NEGROS
        )
        val arrayOfIslandBodies = arrayOf(
            R.string.luzon_body,
            R.string.cebu_body,
            R.string.boraсay_body,
            R.string.bohol_body,
            R.string.palavan_body,
            R.string.negros_body
        )

        val arrayOfIslandImages = arrayOf(
            R.drawable.image_luzon,
            R.drawable.image_cebu,
            R.drawable.image_baracay,
            R.drawable.image_bohol,
            R.drawable.image_palavan,
            R.drawable.image_negros
        )

        val listOfIsland = ArrayList<IslandData>()
        for (index in arrayOfIslands.indices) {
            listOfIsland.add(
                IslandData(
                    title = arrayOfIslandsTitles[index],
                    island = arrayOfIslands[index],
                    body = arrayOfIslandBodies[index],
                    image = arrayOfIslandImages[index]
                )
            )
        }
        return listOfIsland
    }

    override fun getTransferUri(): String {
        return "/android_asset/util/transfer.jpg"
    }

    override fun getHotelUri(): String {
        return "/android_asset/util/hotel.jpg"
    }

    override fun getExcursionPalavanUrl(): String {
        return listOfUrlsPalavan.random()
    }

    override fun getExcursionNegrosUrl(): String {
        return listOfUrlsNegros.random()
    }

    override fun getExcursionBoracayUrl(): String {
        return listOfUrlsBoracay.random()
    }

    override fun getExcursionBoholUrl(): String {
        return listOfUrlsBohol.random()
    }

    override fun getExcursionCebuUrl(): String {
        return listOfUrlsCebu.random()
    }

    override fun getExcursionLuzonUrl(): String {
        return listOfUrlsLuzon.random()
    }

    override fun getIslandTitle(island: Int): Int {
        return arrayOfIslandsTitles[island]
    }

    override fun getListOfCitesLuzon(): Int {
        return R.array.cites_luzon
    }

    override fun getListOfCitesCebu(): Int {
        return R.array.cites_cebu
    }

    override fun getListOfCitesBoracay(): Int {
        return R.array.cites_boracay
    }

    override fun getListOfCitesBohol(): Int {
        return R.array.cites_bohol
    }

    override fun getListOfCitesPalavan(): Int {
        return R.array.cites_palavan
    }

    override fun getListOfCitesNegros(): Int {
        return R.array.cites_negros
    }

    override fun getListOfContacts(): Int {
        return R.array.types_of_contacts
    }

}