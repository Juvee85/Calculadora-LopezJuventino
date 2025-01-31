package lopez.juventino.asignacion05_claculadora_lopez

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var puedeAnhadirPunto: Boolean = true
    var puedeAnhadirOperador: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val tvCalculo: TextView = findViewById(R.id.tvCalcular)
        val btn0: Button = findViewById(R.id.btn0)
        val btn1: Button = findViewById(R.id.btn1)
        val btn2: Button = findViewById(R.id.btn2)
        val btn3: Button = findViewById(R.id.btn3)
        val btn4: Button = findViewById(R.id.btn4)
        val btn5: Button = findViewById(R.id.btn5)
        val btn6: Button = findViewById(R.id.btn6)
        val btn7: Button = findViewById(R.id.btn7)
        val btn8: Button = findViewById(R.id.btn8)
        val btn9: Button = findViewById(R.id.btn9)
        val btnMas: Button = findViewById(R.id.btnMas)
        val btnMenos: Button = findViewById(R.id.btnMenos)
        val btnMult: Button = findViewById(R.id.btnMult)
        val btnDiv: Button = findViewById(R.id.btnDiv)
        val btnPunto:Button = findViewById(R.id.btnPunto)
        val btnEsc: Button = findViewById(R.id.btnEsc)
        val btnCalcular: Button = findViewById(R.id.btnCalcular)

        val botonNumericoListener = View.OnClickListener { view: View ->
            if (view is Button){
                if(view.text == ".") {
                    if(puedeAnhadirPunto){
                        tvCalculo.text = "${tvCalculo.text}${view.text}"
                        puedeAnhadirPunto = false
                        puedeAnhadirOperador = false
                    }
                } else {
                    tvCalculo.text = "${tvCalculo.text}${view.text}"
                    puedeAnhadirOperador = true
                }

            }
        }

        val botonOperadorListener = View.OnClickListener { view: View ->
            if (view is Button && puedeAnhadirOperador) {
                tvCalculo.text = "${tvCalculo.text}${view.text}"
                puedeAnhadirOperador = false
                puedeAnhadirPunto = true
            }
        }

        val botonRetrocederListener = View.OnClickListener { view: View ->
            val operacionIngresadaCadena: String = tvCalculo.text.toString()

            if (operacionIngresadaCadena.isNotEmpty()) {
                tvCalculo.text = operacionIngresadaCadena.dropLast(1)
            }
        }

        val calcularListener = View.OnClickListener { view: View ->
            val resultado = calcular(tvCalculo.text.toString())

            tvCalculo.text = "$resultado"
        }

        btn0.setOnClickListener(botonNumericoListener)
        btn1.setOnClickListener(botonNumericoListener)
        btn2.setOnClickListener(botonNumericoListener)
        btn3.setOnClickListener(botonNumericoListener)
        btn4.setOnClickListener(botonNumericoListener)
        btn5.setOnClickListener(botonNumericoListener)
        btn6.setOnClickListener(botonNumericoListener)
        btn7.setOnClickListener(botonNumericoListener)
        btn8.setOnClickListener(botonNumericoListener)
        btn9.setOnClickListener(botonNumericoListener)
        btnMas.setOnClickListener(botonOperadorListener)
        btnMenos.setOnClickListener(botonOperadorListener)
        btnMult.setOnClickListener(botonOperadorListener)
        btnDiv.setOnClickListener(botonOperadorListener)
        btnPunto.setOnClickListener(botonNumericoListener)
        btnEsc.setOnClickListener(botonRetrocederListener)
        btnCalcular.setOnClickListener(calcularListener)
    }

    fun calcular(expresion: String): Double {
        var primerValor = ""
        var segundoValor = ""
        var operador = ""
        for (c in expresion) {
            if ((c.isDigit() || c == '.') && operador.isBlank()) {
                primerValor += c
            } else if (c in "+-/*") {
                operador += c
            } else {
                segundoValor += c
            }
        }

        var primerNumero = primerValor.toDouble()
        var segundoNumero = segundoValor.toDouble()

        return when (operador) {
            "+" -> primerNumero + segundoNumero
            "-" -> primerNumero - segundoNumero
            "*" -> primerNumero * segundoNumero
            "/" -> primerNumero / segundoNumero
            else -> primerNumero
        }
    }

}