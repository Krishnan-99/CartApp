package com.assessment.cartapp.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.assessment.cartapp.R
import com.assessment.cartapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


/*
 * @author Krish
 */
class HomeActivity : AppCompatActivity() {
    private var navigationView: BottomNavigationView? = null
    private lateinit var binding: ActivityMainBinding

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(getLayoutInflater())
        setContentView(binding.getRoot())
        navigationView = findViewById(R.id.nav_view)
        val navController: NavController =
            Navigation.findNavController(this, R.id.nav_host_fragment_activity_home)
        navController.setGraph(R.navigation.app_navigation)

        val appBarConfiguration: AppBarConfiguration =
            AppBarConfiguration.Builder(R.id.servicesFragment, R.id.cartListFragment)
                .build()
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    fun showProgress(show: Boolean) {
        if (show) {
            binding.pb.root.visibility = View.VISIBLE
            binding.navView.isEnabled = true
        } else {
            binding.pb.root.visibility = View.GONE
            binding.navView.isEnabled = false
        }
    }
}