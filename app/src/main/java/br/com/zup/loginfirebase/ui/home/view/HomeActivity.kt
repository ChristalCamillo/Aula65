package br.com.zup.loginfirebase.ui.home.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.zup.loginfirebase.R
import br.com.zup.loginfirebase.databinding.ActivityHomeBinding
import br.com.zup.loginfirebase.ui.home.viewmodel.HomeViewModel
import br.com.zup.loginfirebase.ui.login.view.LoginActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showDataUser()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.exit -> {
                viewModel.logoutUser()
                this.finish()
                goToLogin()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showDataUser() {
        val name = viewModel.getNameUser()
        val email = viewModel.getEmailUser()
        binding.tvNameUser.text = name
        binding.tvEmailUser.text = email
//        binding.tvNameUser.text = "$name - $email"
    }

    private fun goToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}