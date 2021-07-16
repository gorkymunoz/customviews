package com.gorkymunoz.customviews

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gorkymunoz.customviews.adapters.MediaAdapter
import com.gorkymunoz.customviews.data.MediaProvider
import com.gorkymunoz.customviews.databinding.ActivityMainBinding
import com.gorkymunoz.customviews.enum.MediaType
import com.gorkymunoz.customviews.extensions.toast
import com.gorkymunoz.customviews.model.MediaItem
import com.gorkymunoz.customviews.utils.SignatureUtils
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var job: Job

    private lateinit var binding: ActivityMainBinding
    private val mediaAdapter: MediaAdapter by lazy {
        MediaAdapter {
            toast(it.title)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        job = SupervisorJob()

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            tvAdd.setOnClickListener {
                dotlayout.addPinEntry("1")
            }

            tvRemove.setOnClickListener {
                dotlayout.deletePinEntry()
            }

            rv.apply {
                adapter = mediaAdapter
            }

            loadItems()

            dotlayout.setListener {
                toast("Pin is $it")
                binding.dotlayout.showError()
            }

            setContentView(root)
        }


        SignatureUtils.getKeyHash(this, "SHA-256")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        loadItems(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }

    private fun loadItems(filter: Int = R.id.filter_all) {
        launch {
            binding.rv.visibility = View.GONE
            binding.progress.visibility = View.VISIBLE
            mediaAdapter.items = withContext(Dispatchers.IO) {
                filteredItems(filter)
            }
            binding.progress.visibility = View.GONE
            binding.rv.visibility = View.VISIBLE
        }
    }

    private fun filteredItems(filter: Int): List<MediaItem> =
        MediaProvider.getItems().let { items ->
            when (filter) {
                R.id.filter_videos -> items.filter { it.type == MediaType.VIDEO }
                R.id.filter_photos -> items.filter { it.type == MediaType.PHOTO }
                R.id.filter_all -> items
                else -> emptyList()
            }
        }

}