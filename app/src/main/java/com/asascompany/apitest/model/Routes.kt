package com.asascompany.apitest.model

object Routes {
    private const val BASE_URL = "https://eldenring.fanapis.com/api/weapons"
    const val ID = "https://eldenring.fanapis.com/api/weapons?id="
    const val NAME = "$BASE_URL?name="
    const val PAGE = "$BASE_URL?limit=5&page="
}