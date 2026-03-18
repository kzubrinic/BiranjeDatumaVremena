package hr.unidu.kz.biranjedatumavremena

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hr.unidu.kz.biranjedatumavremena.databinding.ActivityMainBinding

import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. BIRAČ DATUMA
        binding.btnDatum.setOnClickListener {
            val c = Calendar.getInstance()
            val godina = c.get(Calendar.YEAR)
            val mjesec = c.get(Calendar.MONTH)
            val dan = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, { _, year, monthOfYear, dayOfMonth ->
                // Mjeseci idu od 0 do 11, pa dodajemo 1
                val formatiraniDatum = String.format("%02d.%02d.%04d.", dayOfMonth, monthOfYear + 1, year)
                binding.izDatum.text = "Datum: $formatiraniDatum"
            }, godina, mjesec, dan)

            dpd.show()
        }

        // 2. BIRAČ VREMENA
        binding.btnVrijeme.setOnClickListener {
            val c = Calendar.getInstance()
            val sat = c.get(Calendar.HOUR_OF_DAY)
            val minuta = c.get(Calendar.MINUTE)

            val tpd = TimePickerDialog(this, { _, hourOfDay, minute ->
                // Formatiranje da minuta uvijek ima dvije znamenke (npr. 05 umjesto 5)
                val formatiranoVrijeme = String.format("%02d:%02d", hourOfDay, minute)
                binding.izVrijeme.text = "Vrijeme: $formatiranoVrijeme"
            }, sat, minuta, true) // 'true' znači 24-satni prikaz

            tpd.show()
        }
    }
}