package com.example.p1_conversiondivisas_gonzalezmoreno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*


class MainActivity : AppCompatActivity() {

    // Simulación de tasas de cambio (puedes usar una API externa para obtener estos valores)
    val exchangeRates = mapOf(
        "USD" to 1.0,  // Suponiendo que USD es la base
        "EUR" to 0.92,
        "PEN" to 3.70
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etAmount = findViewById<EditText>(R.id.et_amount)
        val spCurrency = findViewById<Spinner>(R.id.sp_currency)
        val btnConvert = findViewById<Button>(R.id.btn_convert)
        val tvResult = findViewById<TextView>(R.id.tv_result)

        // Configurar Spinner con divisas
        val currencies = arrayOf("USD", "EUR", "PEN")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, currencies)
        spCurrency.adapter = adapter

        // Acción del botón de conversión
        btnConvert.setOnClickListener {
            val amountStr = etAmount.text.toString()
            if (amountStr.isNotEmpty()) {
                val amount = amountStr.toDouble()
                val selectedCurrency = spCurrency.selectedItem.toString()
                val rate = exchangeRates[selectedCurrency]

                // Realizar la conversión
                if (rate != null) {
                    val result = amount * rate
                    tvResult.text = "Resultado: $amount USD = %.2f $selectedCurrency".format(result)
                } else {
                    tvResult.text = "Divisa no válida"
                }
            } else {
                Toast.makeText(this, "Por favor ingrese un valor", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
