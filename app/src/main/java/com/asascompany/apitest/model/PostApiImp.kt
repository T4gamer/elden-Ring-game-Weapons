package com.asascompany.apitest.model
import android.graphics.pdf.PdfDocument.Page
import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*

/**
 * Created by Taha Ben Ashur (https://github.com/tahaak67) on 04,Feb,2023
 */
class PostsApiImpl(
    private val client: HttpClient
): PostsApi {
    override var page: Int = 0

    override fun emptyDatum() :Datum{
        return Datum("","","","", emptyList(), emptyList()
            , emptyList(), emptyList()
            ,"",0.0)
    }

    override fun emptyDatum(id: String): Datum {
        return Datum(id,"","","", emptyList(), emptyList()
            , emptyList(), emptyList()
            ,"",0.0)
    }
    override suspend fun getPage(): List<Datum> {
        return try {
            var welcome:Welcome = client.get {
                url("${Routes.PAGE}${page}")
            }.body()
            Log.d("weapon",welcome.data.toString())
            welcome.data
        } catch (e: RedirectResponseException) {
            Log.e("PostsApi", "3XX Error: ${e.message}")
            emptyList()
        } catch (e: ClientRequestException) {
            Log.e("PostsApi", "4XX Error: ${e.message}")
            emptyList()
        } catch (e: ServerResponseException) {
            Log.e("PostsApi", "5XX Error: ${e.message}")
            emptyList()
        } catch (e: Exception) {
            Log.e("PostsApi", "Error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getName(name :String): Datum {
        return try {
            var list : Welcome = client.get {
                url("${Routes.NAME}${name}")
            }.body()
            list.data.get(list.data.indexOfFirst { it.name == name })
        } catch (e: RedirectResponseException) {
            Log.e("PostsApi", "3XX Error: ${e.message}")
            emptyDatum()
        } catch (e: ClientRequestException) {
            Log.e("PostsApi", "4XX Error: ${e.message}")
            emptyDatum()
        } catch (e: ServerResponseException) {
            Log.e("PostsApi", "5XX Error: ${e.message}")
            emptyDatum()
        } catch (e: Exception) {
            Log.e("PostsApi", "Error: ${e.message}")
            emptyDatum()
        }
    }

    override suspend fun getId(id :String): Datum {
        return try {
           var list : Welcome= client.get {
                url("${Routes.ID}${id}")
            }.body()
            Log.d("INSIDETHEFUN",list.data.get(list.data.indexOfFirst { it.id == id }).toString())
            list.data.get(list.data.indexOfFirst { it.id == id })
        } catch (e: RedirectResponseException) {
            Log.e("PostsApi", "3XX Error: ${e.message}")
            emptyDatum("-3")
        } catch (e: ClientRequestException) {
            Log.e("PostsApi", "4XX Error: ${e.message}")
            emptyDatum("-4")
        } catch (e: ServerResponseException) {
            Log.e("PostsApi", "5XX Error: ${e.message}")
            emptyDatum("-5")
        } catch (e: Exception) {
            Log.e("PostsApi", "Error: ${e.message}")
            emptyDatum("-1")
        }
    }
}