package com.gorkymunoz.customviews

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gorkymunoz.customviews.interfaces.PinFilled
import com.gorkymunoz.customviews.utils.SignatureUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PinFilled {

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

        dotlayout.setListener(this)
    }

    override fun pinCompleted(pin: String) {
        Toast.makeText(this, "Pin is $pin", Toast.LENGTH_LONG).show()
        dotlayout.showError()
    }


}