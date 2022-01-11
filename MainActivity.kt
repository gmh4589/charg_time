package com.example.c_application

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val monList = resources.getStringArray(R.array.months)
        val dayList = resources.getStringArray(R.array.days)

        val button = button
        button.setOnClickListener {
            val tok = cur_mah.text.toString().toFloat()
            val vol = bat_mah.text.toString().toFloat()
            val result = vol * 1.2 / tok
            val time_now = LocalDateTime.now()

            val elsps = time_now.plusHours(result.toLong())
            val date = elsps.toString().substringBefore('T')
            val now = time_now.toString().substringBefore('T')
            val time = elsps.toString().substringAfter('T')
            val time2 = time.substringBeforeLast(':')
            val dt = date.substringAfter('-')
            val time3 = date.substringAfterLast('-').toInt() - now.substringAfterLast('-').toInt()
            val mon = dt.substringBefore('-').toInt() - 1

            val elp = if (time3 < 3) {
                dayList[time3]
                } else { "${dt.substringAfter('-')} ${monList[mon]}"
            }

            up_field.text = resources.getString(R.string.end_txt)
            result_field.text = "${elp} в ${time2}"
        }
    }
}





