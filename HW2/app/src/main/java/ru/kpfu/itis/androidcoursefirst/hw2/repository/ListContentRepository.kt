package ru.kpfu.itis.androidcoursefirst.hw2.repository

import ru.kpfu.itis.androidcoursefirst.hw2.model.ButtonsItemHolderModel
import ru.kpfu.itis.androidcoursefirst.hw2.model.MultipleHoldersModel
import ru.kpfu.itis.androidcoursefirst.hw2.model.PictureItemHolderModel
import kotlin.random.Random

object ListContentRepository {

    private var itemCount = 7

    private val itemList = mutableListOf(
        ButtonsItemHolderModel(id = 0),
        PictureItemHolderModel(
            id = 1,
            imageURL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR9sfiPQRxY3k9AQFocBXsZjhZJC3DvjRAfcg&s",
            header = "Kazan",
            description = "A very beautiful city, the capital of the Republic of Tatarstan"
        ),
        PictureItemHolderModel(
            id = 2,
            imageURL = "https://invest.tatarstan.ru/upload/resize_cache/iblock/b07/1030_600_2/naberezhnye-chelny.jpg",
            header = "Naberezhnye Chelny",
            description = "Naberezhnye Chelny, or Yar Challi, is the second biggest city in Tatarstan"
        ),
        PictureItemHolderModel(
            id = 3,
            imageURL = "https://tatarstan.top/upload/resize_cache/iblock/c3a/1200_802_1/ud1m1gfuoh5vhd07hl5metz6dht74wqb.jpg",
            header = "Almetyevsk",
            description = "Almetyevsk is the oil capital of Tatarstan"
        ),
        PictureItemHolderModel(
            id = 4,
            imageURL = "https://travelpoisk.ru/data/wikitravel/44113/menzelinsk.jpg",
            header = "Menzelinsk",
            description = "Minzela is the town I am in love with"
        ),
        PictureItemHolderModel(
            id = 5,
            imageURL = "https://cdn.tripster.ru/thumbs2/6cf3d8b0-d61a-11ec-91cd-2a26f73d5abd.800x600.jpeg",
            header = "Yelabuga",
            description = "Yelabuga, or Alabuga, which is translated as perch from Tatar language, is a city with fascinating history"
        ),
        PictureItemHolderModel(
            id = 6,
            imageURL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR9sfiPQRxY3k9AQFocBXsZjhZJC3DvjRAfcg&s",
            header = "Kazan",
            description = "A very beautiful city, the capital of the Republic of Tatarstan"
        )
    )

    private val randomUrls = listOf(
        "https://img.freepik.com/premium-photo/fantasy-town-water-sunset_921026-10796.jpg",
        "https://t3.ftcdn.net/jpg/05/85/83/64/360_F_585836415_qbRaID071NE9jdzTK2mLzzhQ35LIO7Dm.jpg",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTM_yd-fDPo7sd-J4GEZCXxWjgOMCHBkmS5lw&s",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT6ZZg5GZuzmUaNFVtK0bwST036k5VfAU_ETA&s",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRil7anR4o27AU4i9goJtT94Qfx70MBbmXbaw&s",
        "https://images.squarespace-cdn.com/content/v1/608164c98d4fc466c981a18c/cbe51ad5-2b04-4ac9-a3f1-41d4c86c5ab1/Fantasy-Dragon-City.jpg",
    )

    private val randomHeaders = listOf(
        "Aetheria",
        "Luminalis",
        "Obsidia",
        "Hydropolis",
        "Chronovale",
        "Celestara",
        "Qamillandia"
    )

    private val randomDescriptions = listOf(
        "Шәһәр күктә асылынып тора, аны кристалл энерциясе күтәреп тора. Һава күперләре белән тоташкан алтын манаралар кояш нурларында ялтырап балкый. Биредә җил органнар уйный, ә кешеләр кошлардай оча торган канатлы җайланмаларда хәрәкәт итә.",
        "Яктылык шәһәре. Урамнарда караңгы төшми, чөнки һәр йорт үз энергиясен юкка чыккан йолдызлардан ала. Кешеләр гравитацияне үзгәртә ала, һәм урам буйлап басып түгел, ә йөзеп хәрәкәт итәләр.",
        "Тулысынча кара таштан төзелгән шәһәр. Өстән караганда ул мәгарә кебек күренә, әмма эчендә гел яктыртылган неон бизәкләр бар. Монда яшәүчеләр чынбарлык белән виртуаль дөнья арасында теләгәнчә күчә ала.",
        "Океан астындагы шәһәр. Гөмбәзсыман биналар коралсыман аквариумнар белән уратып алынган. Барысы да су асты җанварлары һәм үсемлекләр белән гармониядә яши. Монда су астында сулый алу өчен махсус аппаратлар кулланалар.",
        "Вакыт кырына төзелгән шәһәр. Урамнары гасырлар арасында күчеп йөри, һәм һәр йорт үз заманын күрсәтә. Кешеләр вакыт арасында сәяхәт итеп, тарихны өйрәнә яки киләчәкне күрә ала.",
        "Космосның чиксез бушлыгында асылынып торучы шәһәр. Җирсез күперләр йолдызлар кыры буйлап сузыла. Монда яшәүчеләр өчен тартылу көче көйләнә, һәм алар йолдызлардан энергия җыя.",
    )

    fun getListOfCities(): List<MultipleHoldersModel> {
        return itemList
    }

    fun getRandomCity(): PictureItemHolderModel {
        val newCity = PictureItemHolderModel(
            id = itemList.size,
            imageURL = getRandomURL(),
            header = getRandomHeader(),
            description = getRandomDescription(),
        )
        itemList.add(newCity)
        return newCity
    }

    private fun getRandomURL(): String {
        return randomUrls[Random.nextInt(0, randomUrls.size)]
    }

    private fun getRandomHeader(): String {
        return randomHeaders[Random.nextInt(0, randomHeaders.size)]
    }

    private fun getRandomDescription(): String {
        return randomDescriptions[Random.nextInt(0, randomDescriptions.size)]
    }

}
