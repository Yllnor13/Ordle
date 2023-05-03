package com.example.ordle

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.util.Log



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var l1: TextView = findViewById<TextView>(R.id.r1c1)
        var l2: TextView = findViewById<TextView>(R.id.r1c2)
        var l3: TextView = findViewById<TextView>(R.id.r1c3)
        var l4: TextView = findViewById<TextView>(R.id.r1c4)
        var l5: TextView = findViewById<TextView>(R.id.r1c5)
        var row1: List<TextView> = listOf(l1,l2,l3,l4,l5)
        val keyboard: GridLayout = findViewById<GridLayout>(R.id.keyboard)
        for (i in 0 until keyboard.getChildCount()) {
            val v: View = keyboard.getChildAt(i)
            if (v is Button) {
                val button: Button = v as Button
                button.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(view: View) {
                        val letter: String = (view as Button).getText().toString()
                        Log.d("letter", letter)
                        var string1: String = ""
                        for(j in row1){
                            val s: String = j.getText().toString()
                            Log.d("rowtext", "row: " + s + " button: " + letter)
                            if (s != " "){
                                j.setText(letter)
                                Log.d("rowtext", "row: " + s + " button: " + letter)
                                break
                            }
                        }
                    }
                })
            }
        }
    }
}