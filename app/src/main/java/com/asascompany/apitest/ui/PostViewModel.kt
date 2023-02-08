package com.asascompany.apitest.ui
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asascompany.apitest.model.Datum
import com.asascompany.apitest.model.PostsApi
import com.asascompany.apitest.model.PostsApiImpl
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * Created by Taha Ben Ashur (https://github.com/tahaak67) on 07,Feb,2023
 */
class PostViewModel: ViewModel() {

    private var postsApi: PostsApi = PostsApiImpl(Provider.client)
    private val _posts: MutableLiveData<List<Datum>> = MutableLiveData()
    val posts: LiveData<List<Datum>> get() = _posts
    private val _item: MutableLiveData<Datum> = MutableLiveData()
    val item :MutableLiveData<Datum> get() = _item

    init {
        loadPostsFromApi()
    }

    fun loadPostsFromApi(){
        viewModelScope.launch {
            _posts.value = postsApi.getPage()
        }
    }

    fun nextPage(){
        viewModelScope.launch {
                postsApi.page +=1
                _posts.value = postsApi.getPage()
        }
    }
    fun lastPage(){
        viewModelScope.launch {
            if(postsApi.page > 1) {
                postsApi.page -= 1
            }
            _posts.value = postsApi.getPage()
        }
    }
    fun getByName(name: String){
        viewModelScope.launch {
            _item.value= postsApi.getName(name)
        }
    }
}