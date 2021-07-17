package com.gorkymunoz.customviews.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gorkymunoz.customviews.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {
        const val EXTRA_ID = "DetailActivity:id"
    }
}