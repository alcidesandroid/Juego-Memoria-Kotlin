package com.ing240789.juegomemoria

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cartas_imagen.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var turno: Int=1
    var puntosPlayer1: Int = 0
    var puntosPlayer2: Int = 0
    var primeraCarta: Int = 0
    var secundaCarta: Int = 0
    var primerClick: Int = 0
    var secundoClick: Int = 0
    var numeroCarta: Int = 1
    var cartas = ArrayList<Int>(listOf(11,12,13,14,15,21,22,23,24,25))

    var imagen11: Int = 0
    var imagen12: Int = 0
    var imagen13: Int = 0
    var imagen14: Int = 0
    var imagen15: Int = 0

    var imagen21: Int = 0
    var imagen22: Int = 0
    var imagen23: Int = 0
    var imagen24: Int = 0
    var imagen25: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //1.- Setup UI
        setUpUi()
        //2.- Cargar Cartas
        cargarCartas()
        //3.- Barajar las cartas
        Collections.shuffle(cartas)
        //4.- Setup OnclickListener
        setUpOnclickListener()
    }
    private fun setUpUi() {
        mainActivityTvPlayer1.setTextColor(Color.GREEN)
        mainActivityTvPlayer1.setTypeface(null, Typeface.BOLD)

        mainActivityTvPlayer2.setTextColor(Color.GRAY)
        mainActivityTvPlayer2.setTypeface(null, Typeface.NORMAL)
    }
    private fun cargarCartas() {
        imagen11 = R.drawable.ic_bicle
        imagen12 = R.drawable.ic_barco
        imagen13 = R.drawable.ic_coche
        imagen14 = R.drawable.ic_avion
        imagen15 = R.drawable.ic_tren

        imagen21 = R.drawable.ic_bicle
        imagen22 = R.drawable.ic_barco
        imagen23 = R.drawable.ic_coche
        imagen24 = R.drawable.ic_avion
        imagen25 = R.drawable.ic_tren
    }
    private fun setUpOnclickListener() {
        im11.setOnClickListener(){
            var carta: Int = 0
            asignarImagenalaCarta(im11,carta)
        }
        im12.setOnClickListener(){
            var carta: Int = 1
            asignarImagenalaCarta(im12,carta)
        }
        im13.setOnClickListener(){
            var carta: Int = 2
            asignarImagenalaCarta(im13,carta)
        }
        im21.setOnClickListener(){
            var carta: Int = 3
            asignarImagenalaCarta(im21,carta)
        }
        im22.setOnClickListener(){
            var carta: Int = 4
            asignarImagenalaCarta(im22,carta)
        }
        im23.setOnClickListener(){
            var carta: Int = 5
            asignarImagenalaCarta(im23,carta)
        }
        im31.setOnClickListener(){
            var carta: Int = 6
            asignarImagenalaCarta(im31,carta)
        }
        im32.setOnClickListener(){
            var carta: Int = 7
            asignarImagenalaCarta(im32,carta)
        }
        im33.setOnClickListener(){
            var carta: Int = 8
            asignarImagenalaCarta(im33,carta)
        }
        im41.setOnClickListener(){
            var carta: Int = 9
            asignarImagenalaCarta(im41,carta)
        }
    }
    private fun asignarImagenalaCarta(image: ImageView, carta: Int) {
        when (cartas[carta]) {
            11 -> image.setImageResource(imagen11)
            12 -> image.setImageResource(imagen12)
            13 -> image.setImageResource(imagen13)
            14 -> image.setImageResource(imagen14)
            15 -> image.setImageResource(imagen15)

            21 -> image.setImageResource(imagen21)
            22 -> image.setImageResource(imagen22)
            23 -> image.setImageResource(imagen23)
            24 -> image.setImageResource(imagen24)
            25 -> image.setImageResource(imagen25)
        }
        if (numeroCarta == 1) {
            primeraCarta=cartas[carta]
            if (primeraCarta > 20) {
                primeraCarta = primeraCarta - 10
            }
            numeroCarta = 2
            primerClick = carta
            image.isEnabled = false
        }else if (numeroCarta == 2) {
            secundaCarta=cartas[carta]
            if (secundaCarta > 20) {
                secundaCarta = secundaCarta - 10
            }
            numeroCarta = 1
            secundoClick = carta

            im11.isEnabled = false
            im12.isEnabled = false
            im13.isEnabled = false
            im21.isEnabled = false
            im22.isEnabled = false
            im23.isEnabled = false
            im31.isEnabled = false
            im32.isEnabled = false
            im33.isEnabled = false
            im41.isEnabled = false

            var handler = Handler()
            handler.postDelayed(Runnable {
                comprobrarCorrecto()
            },1000)
        }
    }
    private fun comprobrarCorrecto() {
        if (primeraCarta == secundaCarta) {
            when (primerClick) {
                0 -> im11.visibility = View.INVISIBLE
                1 -> im12.visibility = View.INVISIBLE
                2 -> im13.visibility = View.INVISIBLE
                3 -> im21.visibility = View.INVISIBLE
                4 -> im22.visibility = View.INVISIBLE
                5 -> im23.visibility = View.INVISIBLE
                6 -> im31.visibility = View.INVISIBLE
                7 -> im32.visibility = View.INVISIBLE
                8 -> im33.visibility = View.INVISIBLE
                9 -> im41.visibility = View.INVISIBLE
            }
            when (secundoClick) {
                0 -> im11.visibility = View.INVISIBLE
                1 -> im12.visibility = View.INVISIBLE
                2 -> im13.visibility = View.INVISIBLE
                3 -> im21.visibility = View.INVISIBLE
                4 -> im22.visibility = View.INVISIBLE
                5 -> im23.visibility = View.INVISIBLE
                6 -> im31.visibility = View.INVISIBLE
                7 -> im32.visibility = View.INVISIBLE
                8 -> im33.visibility = View.INVISIBLE
                9 -> im41.visibility = View.INVISIBLE
            }
            if (turno == 1) {
                puntosPlayer1++
                mainActivityTvPlayer1.setText("Player 1: " + puntosPlayer1)
            } else {
                puntosPlayer2++
                mainActivityTvPlayer2.setText("Player 2: " + puntosPlayer2)
            }
        } else {
            im11.setImageResource(R.drawable.ic_caja_negra)
            im12.setImageResource(R.drawable.ic_caja_negra)
            im13.setImageResource(R.drawable.ic_caja_negra)

            im21.setImageResource(R.drawable.ic_caja_negra)
            im22.setImageResource(R.drawable.ic_caja_negra)
            im23.setImageResource(R.drawable.ic_caja_negra)

            im31.setImageResource(R.drawable.ic_caja_negra)
            im32.setImageResource(R.drawable.ic_caja_negra)
            im33.setImageResource(R.drawable.ic_caja_negra)

            im41.setImageResource(R.drawable.ic_caja_negra)

            if (turno == 1) {
                turno = 2
                mainActivityTvPlayer1.setTextColor(Color.GRAY)
                mainActivityTvPlayer1.setTypeface(null, Typeface.NORMAL)
                mainActivityTvPlayer2.setTextColor(Color.RED)
                mainActivityTvPlayer2.setTypeface(null, Typeface.BOLD)
            } else {
                turno = 1
                mainActivityTvPlayer1.setTextColor(Color.GREEN)
                mainActivityTvPlayer1.setTypeface(null,Typeface.BOLD)
                mainActivityTvPlayer2.setTextColor(Color.GRAY)
                mainActivityTvPlayer2.setTypeface(null,Typeface.NORMAL)
            }
        }
        im11.isEnabled = true
        im12.isEnabled = true
        im13.isEnabled = true

        im21.isEnabled = true
        im22.isEnabled = true
        im23.isEnabled = true

        im31.isEnabled = true
        im32.isEnabled = true
        im33.isEnabled = true

        im41.isEnabled = true

        finPartida()
    }
    private fun finPartida() {
        if (im11.visibility == View.INVISIBLE &&
            im12.visibility == View.INVISIBLE &&
            im13.visibility == View.INVISIBLE &&
            im21.visibility == View.INVISIBLE &&
            im22.visibility == View.INVISIBLE &&
            im23.visibility == View.INVISIBLE &&
            im31.visibility == View.INVISIBLE &&
            im32.visibility == View.INVISIBLE &&
            im33.visibility == View.INVISIBLE &&
            im41.visibility == View.INVISIBLE) {
            //Crear una ventana de dialogo para mostrar resultados
            var  alerDialog=AlertDialog.Builder(this@MainActivity).create()
            alerDialog.setTitle("Fin de partida")
            alerDialog.setMessage("Player 1: " + puntosPlayer1 + "\n Player 2: " + puntosPlayer2)
            alerDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK"){
                dialogInterface, i ->
                var intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            alerDialog.show()
        }
    }
}
