package com.example.ordle

import android.R.attr.button
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //elements on the activity
        val enter: Button = findViewById<Button>(R.id.key_ent)
        val delete: Button = findViewById<Button>(R.id.key_del)
        val keyboard: GridLayout = findViewById<GridLayout>(R.id.keyboard)

        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_results, null)
        builder.setView(dialogView)

        builder.setPositiveButton("OK") { dialog, which ->
            // do something when the OK button is clicked
        }

        val dialog = builder.create()

        val rootView = findViewById<View>(android.R.id.content)
        val snackbar = Snackbar.make(rootView, "dette er ikke et ord i listen", Snackbar.LENGTH_SHORT)

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

        //row 3
        var m1: TextView = findViewById<TextView>(R.id.r3c1)
        var m2: TextView = findViewById<TextView>(R.id.r3c2)
        var m3: TextView = findViewById<TextView>(R.id.r3c3)
        var m4: TextView = findViewById<TextView>(R.id.r3c4)
        var m5: TextView = findViewById<TextView>(R.id.r3c5)
        var row3: List<TextView> = listOf(m1,m2,m3,m4,m5)

        //row 4
        var n1: TextView = findViewById<TextView>(R.id.r4c1)
        var n2: TextView = findViewById<TextView>(R.id.r4c2)
        var n3: TextView = findViewById<TextView>(R.id.r4c3)
        var n4: TextView = findViewById<TextView>(R.id.r4c4)
        var n5: TextView = findViewById<TextView>(R.id.r4c5)
        var row4: List<TextView> = listOf(n1,n2,n3,n4,n5)

        //row 5
        var o1: TextView = findViewById<TextView>(R.id.r5c1)
        var o2: TextView = findViewById<TextView>(R.id.r5c2)
        var o3: TextView = findViewById<TextView>(R.id.r5c3)
        var o4: TextView = findViewById<TextView>(R.id.r5c4)
        var o5: TextView = findViewById<TextView>(R.id.r5c5)
        var row5: List<TextView> = listOf(o1,o2,o3,o4,o5)

        var rows = listOf<List<TextView>>(
            row1,
            row2,
            row3,
            row4,
            row5
        )

        //TODO: add enough words until its 364
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
            "KNIPS",
            "KNASK",
            "FLIPP",
            "DRITT",
            "SVINN",
            "KIRKE",
            "HÅRET",
            "BUKSE",
            "TRIST",
            "LYKKE",
            "SMELL",
            "SVERD",
            "SVERG",
            "FLYTT",
            "SJALU",
            "KABEL",
            "FLÅTT",
            "FRYKT",
            "FLYTT",
            "FLINK",
            "KRONE",
            "LØYPE",
            "KONGE",
            "FALLE",
            "STEIN",
            "STIGE",
            "HUMLE",
            "GUMLE",
            "DUMLE",
            "SMISK",
            "KJØTT",
            "SLOTT",
            "SILKE",
            "SYLTE",
            "JENTE",
            "FYLLE",
            "FLAGG",
            "SLAPP",
            "FLEIP",
            "KNASK",
            "BOMBE",
            "KAPPE",
            "REKER",
            "EKKEL",
            "LASTE",
            "LØNNE",
            "REKER",
            "EKKEL",
            "KAFFE",
            "RIBBE",
            "PØLSE",
            "FJELL"
        )

        val ordliste = norskord.size
        val randomNr = Random.nextInt(ordliste)

        val kalender = Calendar.getInstance()
        val dagenIdag = kalender.get(Calendar.DAY_OF_YEAR)

        var ord = "" //TODO: once I get 364+ words Ill change this to val and remove the if test
        if(dagenIdag < norskord.size){
            ord = norskord.get(dagenIdag)
        }
        else{
            ord = norskord.get(0)
        }
        Log.i("Ord: ", ord)

        var pos = 0

        var tast = 0

        var attempt : String = ""

        fun iterate(pos: Int, letter: String, v : Button){
            for(j in rows.get(pos)){
                val s: String = j.getText().toString()
                Log.i("rowtext", "row: " + s + " button: " + letter)
                if (s == " "){
                    v.setBackgroundColor(Color.GRAY)
                    j.setText(letter)
                    Log.i("rowtext", "row: " + s + " button: " + letter)
                    break
                }
            }
            attempt += rows.get(pos).get(tast).getText()
        }

        enter.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view: View){
                Log.i("ATTEMPT ", attempt)
                if(attempt in norskord){
                    if(tast>4){
                        Log.i("1TAST OG POS ER LIK ", "TAST = " + tast.toString() + "POS ER LIK = " + pos.toString())
                        Log.i("2TAST OG POS ER LIK ", "TAST = " + tast.toString() + "POS ER LIK = " + pos.toString())
                        Log.i("3TAST OG POS ER LIK ", "TAST = " + tast.toString() + "POS ER LIK = " + pos.toString())
                        for(j in rows.get(pos)){
                            for(o in ord){
                                Log.i("J og O", "J = " + j.getText() + " O = " + o.toString())
                                if(j.getText() == o.toString()){
                                    j.setBackgroundColor(Color.YELLOW)
                                    for (i in 0 until keyboard.getChildCount()) {
                                        val v: View = keyboard.getChildAt(i)
                                        if (v is Button) {
                                            if(v.getText() in attempt && v.getText() == o.toString()){ //TODO: make it so that the buttons dont change color if its already changed
                                                v.setBackgroundColor(Color.YELLOW)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        val ordl = ord.split("")
                        for(x in 0..4){
                            Log.i("CURRENT ITERATION: ", x.toString())
                            Log.i("RESULT FROM ROWS: ", rows.get(pos).get(x).getText().toString())
                            Log.i("RESULT FROM ORDL: ", ordl.get(x+1))
                            if(rows.get(pos).get(x).getText() == ordl.get(x+1)){
                                rows.get(pos).get(x).setBackgroundColor(Color.GREEN)
                                for (i in 0 until keyboard.getChildCount()) {
                                    val v: View = keyboard.getChildAt(i)
                                    if (v is Button) {
                                        if(v.getText() in attempt && v.getText() in ord){
                                            v.setBackgroundColor(Color.GREEN)
                                        }
                                    }
                                }
                            }
                        }

                        pos++
                        tast=0
                        attempt = ""
                    }
                }
                else {
                    snackbar.show()
                }
            }
        })

        delete.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view: View){
                Log.i("Attempt before delete", attempt)
                if(tast>0){
                    rows.get(pos).get(tast-1).setText(" ")
                    attempt = attempt.dropLast(1)
                    for (i in 0 until keyboard.getChildCount()) {
                        val v: View = keyboard.getChildAt(i)
                        if (v is Button) {
                            if(v.getText() !in attempt){
                                v.setBackgroundColor(Color.parseColor("#FF6200EE"))
                            }
                        }
                    }
                    tast--
                    Log.i("Attempt after delete", attempt)
                }
            }
        })

        for (i in 0 until keyboard.getChildCount()) {
            val v: View = keyboard.getChildAt(i)
            if (v is Button) {
                val button: Button = v
                button.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(view: View) {
                        val letter: String = (view as Button).getText().toString()
                        Log.i("letter", letter)
                        var string1: String = ""
                        if(tast<5){
                            iterate(pos, letter, v)
                            tast++
                        }
                    }
                })
            }
        }
    }
}