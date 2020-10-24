package com.example.testapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.testapp.fragments.AccountFragment
import com.example.testapp.fragments.LoginFragment
import com.google.android.material.navigation.NavigationView

const val BROADCAST_PROFILE_UPDATE = "broadcast_profile_update"
@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener{
    lateinit var drawer:DrawerLayout
    lateinit var navigationView:NavigationView
    lateinit var menu: Menu
    lateinit var headerview:View
    lateinit var profile:ImageView
    lateinit var name:TextView
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LocalBroadcastManager.getInstance(this).registerReceiver(
            profileUpdateBroadcastReceiver, IntentFilter(BROADCAST_PROFILE_UPDATE)
        )
        toolbar = findViewById(R.id.toolbar)
        drawer = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.setDrawerListener(toggle)
        toggle.syncState()
        displaySelectedScreen(R.id.nav_product)
        menu= navigationView.menu
        headerview = navigationView.getHeaderView(0)
        profile = headerview.findViewById(R.id.admin_profile)
        name = headerview.findViewById(R.id.admin_name)
        checkForLogin()
        navigationView.setNavigationItemSelectedListener(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu,menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        displaySelectedScreen(item.itemId)
//        drawer.closeDrawer(GravityCompat.START)
        return true
    }
    private fun displaySelectedScreen(itemId: Int) {
        //creating fragment object
        var fragment: Fragment? = null
        when (itemId) {
            R.id.nav_login -> {
                fragment = LoginFragment()
            }
            R.id.nav_logout->logout()
            R.id.nav_product ->{
                navigationView.menu.getItem(0).isChecked = true
                fragment = ProductFragment()
            }
            R.id.nav_account -> fragment = AccountFragment()
        }
        //replacing the fragment
        if (fragment != null) {
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            ft.replace(R.id.container, fragment)
            ft.commit()
        }
        drawer.closeDrawer(GravityCompat.START)
    }

    override fun onBackPressed() {


        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    private fun checkForLogin(){
        if(App.storage.isLoggedIn){
            menu.findItem(R.id.nav_login).isVisible = false
            menu.findItem(R.id.nav_logout).isVisible = true
            profile.visibility = View.VISIBLE
            name.text = App.storage.myName
        }else{
            menu.findItem(R.id.nav_logout).isVisible = false
            menu.findItem(R.id.nav_login).isVisible = true
            profile.visibility = View.INVISIBLE
            name.text = "Please Login "
        }
    }
    private fun logout(){
        App.storage.isLoggedIn = false
        App.storage.myEmail = ""
        App.storage.myName = ""
        displaySelectedScreen(R.id.nav_product)
        val profileUpdateIntent = Intent(BROADCAST_PROFILE_UPDATE)
        LocalBroadcastManager.getInstance(this).sendBroadcast(profileUpdateIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(profileUpdateBroadcastReceiver)

    }
    private val profileUpdateBroadcastReceiver = object: BroadcastReceiver()
    {
        override fun onReceive(context: Context, intent: Intent)
        {
            displaySelectedScreen(R.id.product)
            checkForLogin()
        }
    }



}