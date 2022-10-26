package com.erick.practica1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.erick.practica1.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    private var pi : Double = 3.1416

    private lateinit var datos_a : EditText
    private lateinit var datos_b : EditText
    private lateinit var datos_c : EditText

    private lateinit var txt_a : TextView
    private lateinit var txt_b : TextView
    private lateinit var txt_c : TextView

    private lateinit var imgView : ImageView

    private var aux : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        Ocultando barra de estado
        actionBar?.hide()

//        Añadiendo viewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinner: Spinner = binding.formulasSpinner
        spinner.onItemSelectedListener = this
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this, R.array.formulas_array, android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

//        Inicializo widgets
        datos_a = binding.etNumber1
        datos_b = binding.etNumber2
        datos_c = binding.etNumber3

        txt_a = binding.tvDato1
        txt_b = binding.tvDato2
        txt_c = binding.tvDato3

        imgView = binding.ivForm

        muestraNada()
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        when (position) {
//            0 -> Toast.makeText(this, "Cilindro", Toast.LENGTH_SHORT).show()
            0 -> muestraNada()
            1 -> muestraCilindro()
            2 -> muestraPiramide()
            3 -> muestraBalcuadrado()
            4 -> muestraBalcubo()
            else -> {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Toast.makeText(this, "No se selecciono nada", Toast.LENGTH_SHORT).show()
    }

    private fun muestraCilindro(){
        aux = 1
        limpiaInputs()

        txt_a.setText(R.string.altura)
        txt_b.setText(R.string.radio)


        imgView.setImageResource(R.drawable.cilindro)
        imgView.isVisible = true

        muestraInputs(true, true, false)
    }

    private fun muestraPiramide(){
        aux = 2
        limpiaInputs()

        txt_a.setText(R.string.lados)
        txt_b.setText(R.string.altura)
        txt_c.setText(R.string.lado)

        imgView.setImageResource(R.drawable.piramides)
        imgView.isVisible = true

        muestraInputs(true, true, true)
    }

    private fun muestraBalcuadrado(){
        aux = 3
        limpiaInputs()

        txt_a.setText(R.string.a)
        txt_b.setText(R.string.b)

        imgView.setImageResource(R.drawable.b_cuadrado)
        imgView.isVisible = true

        muestraInputs(true, true, false)
    }

    private fun muestraBalcubo(){
        aux = 4
        limpiaInputs()

        txt_a.setText(R.string.a)
        txt_b.setText(R.string.b)

        imgView.setImageResource(R.drawable.b_cubo)
        imgView.isVisible = true

        muestraInputs(true, true, false)
    }

    private fun limpiaInputs(){
        datos_a.setText("")
        datos_b.setText("")
        datos_c.setText("")
    }

    private fun muestraInputs(datos1 : Boolean, datos2 : Boolean, datos3 : Boolean ){
        datos_a.isVisible = datos1
        datos_b.isVisible = datos2
        datos_c.isVisible = datos3

        txt_a.isVisible = datos1
        txt_b.isVisible = datos2
        txt_c.isVisible = datos3
    }

    private fun muestraNada(){
        aux = 0
        datos_a.isVisible = false
        datos_b.isVisible = false
        datos_c.isVisible = false

        txt_a.isVisible = false
        txt_b.isVisible = false
        txt_c.isVisible = false

        imgView.isVisible = false
    }

    fun click(view: View) {

        when(aux){
            0 -> Toast.makeText(this, getString(R.string.error_formula), Toast.LENGTH_SHORT).show()
            1 -> cilindro()
            2 -> piramide()
            3 -> bCuadrado()
            4 -> bCubo()
        }
    }
    
    private fun esPositivo(numero: Double): Boolean{
        if(numero >= 0) {
            return true
        }
        return false
    }
    
    private fun cilindro(){
        if (datos_a.text.isEmpty() || datos_b.text.isEmpty()){
            Toast.makeText(this, "Ingresa datos!!!", Toast.LENGTH_SHORT).show()
        }else{
            if ((esPositivo(datos_a.text.toString().toDouble())) && (esPositivo(datos_b.text.toString().toDouble()))){
                var ans = "${datos_b.text.toString().toDouble().pow(2) * pi * datos_a.text.toString().toDouble()}"
                val intent = Intent(this, MainActivity2::class.java)
                intent.putExtra("answer", ans)
                startActivity(intent)
            }else{
                Toast.makeText(this, getString(R.string.error_negativo), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun piramide(){
        if (datos_a.text.isEmpty() || datos_b.text.isEmpty() || datos_c.text.isEmpty()){
            Toast.makeText(this, "Ingresa datos!!!", Toast.LENGTH_SHORT).show()
        }else{
            if ((esPositivo(datos_a.text.toString().toDouble())) && (esPositivo(datos_b.text.toString().toDouble())) && (esPositivo(datos_c.text.toString().toDouble()))  ){

                var ans : String
                var aux : Double = 0.0

                when(datos_a.text.toString().toInt()){
                    3 -> {
                        aux = (datos_b.text.toString().toDouble() *  areaTriangulo(datos_c.text.toString().toDouble()) )/3
                    }
                    4 -> {
                        aux = (datos_b.text.toString().toDouble() *  areaCuadrado(datos_c.text.toString().toDouble()) )/3
                    }
                    else -> {
                        Toast.makeText(this, getString(R.string.error_lados), Toast.LENGTH_SHORT).show()
                    }
                }
                ans = aux.toString()
                val intent = Intent(this, MainActivity2::class.java)
                intent.putExtra("answer", ans)
                startActivity(intent)
            }else{
                Toast.makeText(this, getString(R.string.error_negativo), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun areaTriangulo(lado : Double): Double{
        return (lado*lado)/2
    }

    private fun areaCuadrado(lado : Double): Double {
        return lado*lado
    }

    private fun bCuadrado(){
        if (datos_a.text.isEmpty() || datos_b.text.isEmpty()){
            Toast.makeText(this, "Ingresa datos!!!", Toast.LENGTH_SHORT).show()
        }else{
            var a = datos_a.text.toString().toDouble()
            var b = datos_b.text.toString().toDouble()
            var ans : String

            if (esPositivo(b)){
                ans = "${a.pow(2)} + ${2*a*b} + ${b.pow(2)}"
            }else{
                ans = "${a.pow(2)} ${2*a*b} + ${b.pow(2)}"
            }
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("answer", ans)
            startActivity(intent)
        }
    }

    private fun bCubo() {
        if (datos_a.text.isEmpty() || datos_b.text.isEmpty()){
            Toast.makeText(this, "Ingresa datos!!!", Toast.LENGTH_SHORT).show()
        }else{
            var a = datos_a.text.toString().toDouble()
            var b = datos_b.text.toString().toDouble()
            var ans: String

            if (esPositivo(b)) {
                ans = "${a.pow(3)} + ${3 * (a.pow(2)) * b} + ${3 * a * (b.pow(2))} + ${b.pow(3)}"
            } else {
                ans = "${a.pow(3)} ${3 * (a.pow(2)) * b} + ${3 * a * (b.pow(2))} ${b.pow(3)}"
            }
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("answer", ans)
            startActivity(intent)
        }
    }
}