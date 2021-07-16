package com.gorkymunoz.customviews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gorkymunoz.customviews.adapters.MediaAdapter
import com.gorkymunoz.customviews.data.getItems
import com.gorkymunoz.customviews.databinding.ActivityMainBinding
import com.gorkymunoz.customviews.extensions.toast
import com.gorkymunoz.customviews.interfaces.Logger
import com.gorkymunoz.customviews.utils.SignatureUtils

class MainActivity : AppCompatActivity(), Logger {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            tvAdd.setOnClickListener {
                dotlayout.addPinEntry("1")
            }

            tvRemove.setOnClickListener {
                dotlayout.deletePinEntry()
            }

            rv.apply {
                adapter = MediaAdapter(getItems()) {
                    toast(it.title)
                }
            }

            dotlayout.setListener {
                toast("Pin is $it")
                binding.dotlayout.showError()
            }

            setContentView(root)
        }


        SignatureUtils.getKeyHash(this, "SHA-256")
    }

}