package ru.navodnikov.denis.karagatantour.ui.base

import android.os.Bundle
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import ru.navodnikov.denis.karagatantour.R
import ru.navodnikov.denis.karagatantour.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.main_fragment)
        val menu = PopupMenu(this,null).menu
        menuInflater.inflate(R.menu.navigation_menu,menu)
        binding.bottomBar.setupWithNavController(menu,navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp()
        return true
    }

    fun showMessage(error: Int) {
        val snackbar = binding.coordinator?.let {
            Snackbar.make(
                it,
                getString(error),
                Snackbar.LENGTH_LONG
            )
        }
        snackbar?.show()
    }
}