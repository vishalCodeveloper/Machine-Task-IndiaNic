package com.example.machine_task_indianic.ui.home.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.example.machine_task_indianic.R
import com.example.machine_task_indianic.databinding.ActivityHomeBinding
import com.example.machine_task_indianic.pref.SharedPreferencesManagerImpl
import com.example.machine_task_indianic.ui.base.BaseActivity
import com.example.machine_task_indianic.utils.Common
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    @Inject
    lateinit var sharedPreferencesManager: SharedPreferencesManagerImpl

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
        navController = findNavController(R.id.nav_host_fragment)

        initNavDrawer()
    }

    private fun initNavDrawer() {
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.home_fragment, R.id.history_fragment),
            mViewBinding.drawerLayout
        )
        mViewBinding.navView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)

        mViewBinding.navView.menu.findItem(R.id.logout).setOnMenuItemClickListener { menuItem ->
            logoutDialog()
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment))
                || super.onOptionsItemSelected(item)


    override fun onSupportNavigateUp() =
        findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)

    private fun logoutDialog() {
        firebaseAuth.signOut()
        sharedPreferencesManager.set(Common.IS_LOGIN, false)
        navController.navigate(R.id.homeActivity)
        finish()
    }

    override fun getViewBinding(): ActivityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
}