package com.example.charge_time_new

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val monList = resources.getStringArray(R.array.month)
        val dayList = resources.getStringArray(R.array.days)

        val button: Button = findViewById(R.id.button)
        val cur_mah: EditText = findViewById(R.id.enter_currency)
        val bat_mah: EditText = findViewById(R.id.enter_vollume)
        val result_field: TextView = findViewById(R.id.info_panel)

        button.setOnClickListener {
            val cur = cur_mah.text.toString().toFloat()
            val vol = bat_mah.text.toString().toFloat()
            val result = (vol * 1.2 / cur)
            val timeNow = LocalDateTime.now()
            val dateArrayNow = timeNow.toString().split("T", ":", ".", "-")

            val finish = timeNow.plusHours(result.toLong())
            val dateArray = finish.toString().split("T", ":", ".", "-")
            //[0:год, 1:месяц, 2:день, 3:час, 4:мин, 5:сек, 6:доли]

            val h = dateArray[3]
            val m = dateArray[4]
            val rDay = ((result + dateArrayNow[3].toInt()) / 24).toInt()

            val day = if (rDay < 3) {
                dayList[rDay]
            } else {
                dateArray[2] + ' ' + monList[dateArray[1].toInt()]
            }

            result_field.text = "$day в $h:$m"
        }
    }
}

