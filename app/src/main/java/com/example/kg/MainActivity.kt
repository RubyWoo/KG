package com.example.kg

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var score: Int = 0
    var imageArray = ArrayList<ImageView>()
    var handler: Handler = Handler()
    var runnable: Runnable = Runnable { }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageArray = arrayListOf(
            imageView,
            imageView2,
            imageView3,
            imageView4,
            imageView5,
            imageView6,
            imageView7,
            imageView8,
            imageView9
        )

        for (image in imageArray) {
            image.visibility = View.INVISIBLE
        }

        btn.setOnClickListener {
            iniciarJuego()
        }

    }

    fun iniciarJuego() {

        moverKenny()

        btn.visibility = View.INVISIBLE

        score = 0



        object : CountDownTimer(10000, 1000) {
            override fun onFinish() {
                timeText.text = "Tiempo: 0"
                handler.removeCallbacks(runnable)
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
                btn.visibility = View.VISIBLE
                alerta()

            }

            override fun onTick(p0: Long) {
                timeText.text = "Tiempo: " + p0 / 1000
            }

        }.start()
    }


    fun moverKenny() {

        runnable = object : Runnable {
            override fun run() {

                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }

                val random = Random()
                val index = random.nextInt(9 - 0)
                imageArray[index].visibility = View.VISIBLE

                handler.postDelayed(runnable, 500)

            }

        }

        handler.post(runnable)

    }


    fun increaseScore(view: View) {
        score++

        scoreText.text = "Score: " + score
    }

    fun alerta() {
        val alerta = AlertDialog.Builder(this@MainActivity)
        alerta.setTitle("Alerta Android")
        alerta.setMessage("¿Quieres reiniciar el juego?")
        alerta.setIcon(R.drawable.kennymccormick)
        alerta.setPositiveButton("Si") { dialog: DialogInterface?, which: Int ->
            iniciarJuego()
        }
        alerta.setNegativeButton("No") { dialog: DialogInterface?, which: Int ->
            Toast.makeText(this, "No quieres jugar :'(", Toast.LENGTH_SHORT).show()
        }
        alerta.show()
    }
}
