package com.asascompany.apitest.ui
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import coil.load
import com.asascompany.apitest.databinding.ActivityDetailsBinding

class DetailsActivity() : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    var viewModel =  PostViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var id = intent?.getStringExtra("name")?:"Rotten Staff"
        viewModel.getByName(id)
        viewModel.item.observe(this) {
            binding.apply {
                if (it.weight == 0.0) {
                    //show a progress bar if the list is empty
                    pbPosts2.visibility = View.VISIBLE
                    ivName.visibility = View.GONE
                    ivCata.visibility = View.GONE
                    tvAtk.visibility = View.GONE
                    tvDef.visibility = View.GONE
                } else {
                    //otherwise hide the progress bar
                    pbPosts2.visibility = View.GONE
                    ivName.visibility = View.VISIBLE
                    ivCata.visibility = View.VISIBLE
                    tvAtk.visibility = View.VISIBLE
                    tvDef.visibility = View.VISIBLE
                }
                ivName.text = "Name:${it.name}"
                ivCata.text = "type:${ it.category }"
                ivPic.load(it.image)
                tvWeight.text = it.weight.toString()
                tvAtk.text = it.attack.toString()
                tvDef.text = it.defence.toString()
                tvRequire.text = it.requiredAttributes.toString()
                tvScaling.text = it.scalesWith.toString()
                tvDecs.text = it.description
            }
        }

    }

}