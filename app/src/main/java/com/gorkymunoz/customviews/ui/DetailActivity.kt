package com.gorkymunoz.customviews.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.gorkymunoz.customviews.R
import com.gorkymunoz.customviews.data.MediaProvider
import com.gorkymunoz.customviews.databinding.ActivityDetailBinding
import com.gorkymunoz.customviews.enum.MediaType
import com.gorkymunoz.customviews.extensions.loadUrl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemId = intent.getIntExtra(EXTRA_ID, -1)
        if (itemId != -1) {
            getSelectedItem(itemId)
        }
    }

    private fun getSelectedItem(itemId: Int) {
        lifecycleScope.launch {
            val mediaItem = withContext(Dispatchers.IO) {
                MediaProvider.getItems().firstOrNull { it.id == itemId }
            }
            if (mediaItem != null) {
                title = mediaItem.title
                binding.detailThumb.loadUrl(mediaItem.url)
                binding.detailVideoIndicator.isVisible = mediaItem.type == MediaType.VIDEO
            } else {
                handleNotFoundItem()
            }
        }
    }

    private fun handleNotFoundItem() {
        title = getString(android.R.string.unknownName)
        binding.activityDetail.addView(TextView(this@DetailActivity).apply {
            text = getString(R.string.not_found)
            textSize = 24f
        })
    }

    companion object {
        const val EXTRA_ID = "DetailActivity:id"
    }
}