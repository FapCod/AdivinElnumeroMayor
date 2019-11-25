package com.example.adivinelnumeromayor

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import java.util.*

class MainActivity : AppCompatActivity() {
    //Aqui van las variables globales
    var puntos=0
    var puntosPerdidos=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        generadorNumeroRandom()
    }
    fun clickBotonIzquierdo(view: View){
        revisarRespuesta(esIzq = true)
    }
    fun clickBotonDerecho(view: View){
        revisarRespuesta(esIzq = false)
    }


    fun generadorNumeroRandom(){
        val botonIzq = findViewById<Button>(R.id.btnIzquierdo)
        val botonDer = findViewById<Button>(R.id.btnDerecho)
        var r = Random()
        var num1 = r.nextInt(10)
        botonIzq.text="$num1"
        var num2= r.nextInt(10)
        botonDer.text="$num2"
        if(num1==num2){
            generadorNumeroRandom()
        }
    }
    fun revisarRespuesta(esIzq: Boolean){
        val botonIzq = findViewById<Button>(R.id.btnIzquierdo)
        val botonDer = findViewById<Button>(R.id.btnDerecho)
        val numIzq= botonIzq.text.toString().toInt()
        val numDer= botonDer.text.toString().toInt()
        if (esIzq && numIzq> numDer || !esIzq && numIzq<numDer){
            //Ganar puntos
            puntos++
            if (puntos >= 10 ){
                val builder = AlertDialog.Builder(this )
                builder.setTitle("Felicidades ganaste")
                builder.setMessage("Felicitaciones ganaste :) ")
                builder.setPositiveButton("ok",{ dialogInterface: DialogInterface, i: Int ->})
                builder.show()
                puntos=0
                puntosPerdidos=0
            }
        }else{
            //Perder puntos
            //puntos--
            puntosPerdidos++
            if (puntosPerdidos >= 10){
                val builder = AlertDialog.Builder(this )
                builder.setTitle("Lo siento Perdiste")
                builder.setMessage("Lo siento Perdiste :( ")
                builder.setPositiveButton("ok",{ dialogInterface: DialogInterface, i: Int ->})
                builder.show()
                puntosPerdidos=0
                puntos=0

            }
        }

        findViewById<TextView>(R.id.tvPuntos).text="Puntos Ganados: $puntos"
        findViewById<TextView>(R.id.tvPuntos2).text="Puntos Perdidos: $puntosPerdidos"
        generadorNumeroRandom()
    }
}
