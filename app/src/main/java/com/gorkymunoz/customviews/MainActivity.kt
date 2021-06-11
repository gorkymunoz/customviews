package com.gorkymunoz.customviews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gorkymunoz.customviews.enum.DotState
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    var state: DotState = DotState.EMPTY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_hello.setOnClickListener {
            state = state.next()
            dotview.setDotState(state)
        }
    }


}