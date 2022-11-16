package br.com.zup.loginfirebase.ui.home.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.loginfirebase.R
import br.com.zup.loginfirebase.databinding.ActivityHomeBinding
import br.com.zup.loginfirebase.ui.home.viewmodel.HomeViewModel
import br.com.zup.loginfirebase.ui.login.view.LoginActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private var messageList = mutableListOf<String>()

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    private val textAdapter: TextAdapter by lazy {
        TextAdapter(arrayListOf())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showDataUser()
        viewModel.getSavedMessagesList()
        showRecycler()
        //crashButton()

        binding.btnSaveMessage.setOnClickListener {
            if(validateField()){
                viewModel.saveUserMessage(getMessage())
                clearField()
            }
        }
        observer()
    }

    /*private fun crashButton(){
        val crashButton = Button(this)
        crashButton.text = getString(R.string.test_crash)
        crashButton.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }

        addContentView(crashButton, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT))
    }*/

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.exit -> {
                viewModel.logout()
                this.finish()
                goToLogin()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showDataUser() {
        val name = viewModel.getUserEmail()
        val email = viewModel.getUserEmail()
        binding.tvNameUser.text = name
        binding.tvEmailUser.text = email
    }

    private fun getMessage(): String{
        val message = binding.etTypeHere.text.toString()
        messageList.add(message)
        return message
    }

    private fun validateField(): Boolean{
        return if(binding.etTypeHere.text.isEmpty()){
            binding.etTypeHere.error = "Escreva sua mensagem"
            false
        }else{
            true
        }
    }

    private fun clearField(){
        binding.etTypeHere.text.clear()
    }

    private fun observer(){
        viewModel.msgState.observe(this){
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

        viewModel.messageListState.observe(this){
            textAdapter.updateTextList(it.toMutableList())
        }
    }

    private fun showRecycler(){
        binding.rvMsg.adapter = textAdapter
        binding.rvMsg.layoutManager = LinearLayoutManager(this)
    }

    private fun goToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}