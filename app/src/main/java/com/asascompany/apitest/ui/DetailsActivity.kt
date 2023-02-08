package com.asascompany.apitest.ui
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

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
                ivName.text = it.name
                ivCata.text = it.category
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