package com.asascompany.apitest.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.asascompany.apitest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = PostAdapter()
        adapter.onItemClick={
            var intent = Intent(this@MainActivity , DetailsActivity::class.java)
            intent.putExtra("name",it.name)
            startActivity(intent)
        }
        binding.rvPosts.adapter = adapter
        binding.nextBtn.setOnClickListener {
            viewModel.nextPage()
        }
        binding.lastPage.setOnClickListener {
            viewModel.lastPage()
        }
        //start observing our data
        viewModel.posts.observe(this) { posts ->
            if (posts.isEmpty()) {
                //show a progress bar if the list is empty
                binding.pbPosts.visibility = View.VISIBLE
            } else {
                //otherwise hide the progress bar
                binding.pbPosts.visibility = View.GONE
                adapter.submitList(posts)
            }
        }
    }
}