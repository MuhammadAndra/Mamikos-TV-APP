package com.example.mamikostvapp.data.model

data class Show(
    val id: Int,
    val url: String,
    val name: String,
    val type: String,
    val language: String,
    val genres: List<String>,
    val status: String,
    val runtime: Int,
    val averageRuntime: Int,
    val premiered: String,
    val ended: String,
    val officialSite: String,
    val schedule: Schedule,
    val rating: Rating,
    val weight: Int,
    val network: Network,
    val webChannel: Any?,
    val dvdCountry: Any?,
    val externals: Externals,
    val image: Image,
    val summary: String,
    val updated: Int,
    val _links: Links
)

data class Links(
    val previousepisode: PreviousEpisode,
    val self: Self
)

data class Externals(
    val imdb: String,
    val thetvdb: Int,
    val tvrage: Int
)

data class Image(
    val medium: String,
    val original: String
)

data class Network(
    val country: Country,
    val id: Int,
    val name: String,
    val officialSite: Any?
)

data class Rating(
    val average: Double?
)

data class Schedule(
    val days: List<String>,
    val time: String
)

data class PreviousEpisode(
    val href: String,
    val name: String
)

data class Self(
    val href: String
)

data class Country(
    val code: String,
    val name: String,
    val timezone: String
)