package com.example.mamikostvapp.data.model

data class ShowDetail(
    val id: Int,
    val name: String,
    val image: String,
    val premiered: String,
    val summary:String,
    val rating: Double? = null
)

val dummyShowDetail = ShowDetail(
    id = 1,
    name = "Breaking Bad",
    image = "https://static.tvmaze.com/uploads/images/original_untouched/501/1253519.jpg",
    premiered = "2008-01-20",
    summary = "\\u003Cp\\u003E\\u003Cb\\u003EBreaking Bad\\u003C/b\\u003E follows protagonist Walter White, a chemistry teacher who lives in New Mexico with his wife and teenage son who has cerebral palsy. White is diagnosed with Stage III cancer and given a prognosis of two years left to live. With a new sense of fearlessness based on his medical prognosis, and a desire to secure his family's financial security, White chooses to enter a dangerous world of drugs and crime and ascends to power in this world. The series explores how a fatal diagnosis such as White's releases a typical man from the daily concerns and constraints of normal society and follows his transformation from mild family man to a kingpin of the drug trade.\\u003C/p\\u003E ",
    rating =  9.2
)