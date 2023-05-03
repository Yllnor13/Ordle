package com.example.ordle

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // row 1
        var l1: TextView = findViewById<TextView>(R.id.r1c1)
        var l2: TextView = findViewById<TextView>(R.id.r1c2)
        var l3: TextView = findViewById<TextView>(R.id.r1c3)
        var l4: TextView = findViewById<TextView>(R.id.r1c4)
        var l5: TextView = findViewById<TextView>(R.id.r1c5)
        var row1: List<TextView> = listOf(l1,l2,l3,l4,l5)

        //row 2
        var k1: TextView = findViewById<TextView>(R.id.r2c1)
        var k2: TextView = findViewById<TextView>(R.id.r2c2)
        var k3: TextView = findViewById<TextView>(R.id.r2c3)
        var k4: TextView = findViewById<TextView>(R.id.r2c4)
        var k5: TextView = findViewById<TextView>(R.id.r2c5)
        var row2: List<TextView> = listOf(k1,k2,k3,k4,k5)

        var rows = listOf<List<TextView>>(row1,row2)
        //row 3

        val norskord = listOf<String>(
            "FISKE",
            "KASTE",
            "HOPPE",
            "STOPP",
            "KLEMM",
            "SKRIK",
            "PRUMP",
            "PRINS",
            "KRIGE",
            "KLAPP",
            "SLIPP",
            "KLEIN",
            "TRIKS",
            "SLIPS",
            "SLÅSS",
            "KNIPS"
        )

        val ordliste = norskord.size
        val randomNr = Random.nextInt(ordliste)

        val ord = norskord.get(randomNr)
        Log.i("Ord: ", ord)

        val keyboard: GridLayout = findViewById<GridLayout>(R.id.keyboard)
        for (i in 0 until keyboard.getChildCount()) {
            val v: View = keyboard.getChildAt(i)
            if (v is Button) {
                val button: Button = v
                button.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(view: View) {
                        val letter: String = (view as Button).getText().toString()
                        Log.i("letter", letter)
                        var string1: String = ""
                        for(k in rows) {
                            for(j in k){
                                val s: String = j.getText().toString()
                                Log.i("rowtext", "row: " + s + " button: " + letter)
                                if (s == " "){
                                    j.setText(letter)
                                    Log.i("rowtext", "row: " + s + " button: " + letter)
                                    break
                                }
                            }
                            var attempt : String = ""
                            for(j in k){
                                for(m in j.getText()){
                                    if(m in ord){
                                        j.setBackgroundColor(Color.YELLOW)
                                    }
                                }
                            }
                            for(j in k){
                                attempt += j.getText()
                            }
                            if(attempt == ord){
                                for(j in row1){
                                    j.setBackgroundColor(Color.GREEN)
                                }
                            }
                        }
                    }
                })
            }
        }
    }
}