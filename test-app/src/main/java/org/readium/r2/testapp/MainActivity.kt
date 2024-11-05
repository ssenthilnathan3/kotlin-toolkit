package org.readium.r2.testapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private val viewModel: MainViewModel by viewModels()

    private lateinit var floatingActionButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_bookshelf,
                R.id.navigation_catalog_list,
                R.id.navigation_about,
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Floating Action Button setup for navigating to ChatFragment
        floatingActionButton = findViewById(R.id.floatingActionButton)
        floatingActionButton.setOnClickListener {
            findNavController(R.id.nav_host_fragment).navigate(R.id.action_bookshelf_to_chat)
        }


        viewModel.channel.receive(this) { handleEvent(it) }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun handleEvent(event: MainViewModel.Event) {
        when (event) {
            is MainViewModel.Event.ImportPublicationSuccess -> {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    getString(R.string.import_publication_success),
                    Snackbar.LENGTH_LONG
                ).show()
            }
            is MainViewModel.Event.ImportPublicationError -> {
                event.error.toUserError().show(this)
            }
        }
    }

    fun hideFab() {
        floatingActionButton.hide() // Hides the FAB
    }

    fun showFab() {
        floatingActionButton.show() // Shows the FAB
    }
}
