package com.gorkymunoz.customviews

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.gorkymunoz.customviews.adapters.MediaAdapter
import com.gorkymunoz.customviews.data.getItems
import com.gorkymunoz.customviews.interfaces.Logger
import com.gorkymunoz.customviews.interfaces.PinFilled
import com.gorkymunoz.customviews.utils.SignatureUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PinFilled, Logger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SignatureUtils.getKeyHash(this, "SHA-256")

        tv_add.setOnClickListener {
            dotlayout.addPinEntry("1")
        }

        tv_remove.setOnClickListener {
            dotlayout.deletePinEntry()
        }

        val recycler: RecyclerView = findViewById(R.id.rv)
        logD("Initializing recycler")
        recycler.apply {
            adapter = MediaAdapter(getItems())
        }

        dotlayout.setListener(this)
    }

    override fun pinCompleted(pin: String) {
        Toast.makeText(this, "Pin is $pin", Toast.LENGTH_LONG).show()
        dotlayout.showError()
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}