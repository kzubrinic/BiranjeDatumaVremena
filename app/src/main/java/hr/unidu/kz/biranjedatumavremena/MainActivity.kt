package hr.unidu.kz.biranjedatumavremena

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
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
            izaberiVrijeme()
        }

        // 2. BIRAČ VREMENA
        binding.btnVrijeme.setOnClickListener {
            izaberiDatum()
        }

        registerForContextMenu(binding.btnDatum)
    }
    fun izaberiVrijeme(){
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

    fun izaberiDatum(){
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // U Kotlinu koristimo 'when' umjesto 'switch'
        return when (item.itemId) {
            R.id.izbor_datum -> {
                izaberiDatum()
                true
            }
            R.id.izbor_vrijeme -> {
                izaberiVrijeme()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        // U Kotlinu koristimo 'when' umjesto 'switch'
        return when (item.itemId) {
            R.id.izbor_datum -> {
                izaberiDatum()
                true
            }
            R.id.izbor_vrijeme -> {
                izaberiVrijeme()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}