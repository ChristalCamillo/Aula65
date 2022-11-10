package br.com.zup.loginfirebase.ui.text_list.view

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.loginfirebase.databinding.ActivityTextListBinding
import br.com.zup.loginfirebase.ui.text_list.viewmodel.TextListViewModel

class TextListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTextListBinding

    private val viewModel: TextListViewModel by lazy {
        ViewModelProvider(this)[TextListViewModel::class.java]
    }

    private val adapter: TextAdapter by lazy {
        TextAdapter(arrayListOf())
    }

    override fun onStart() {
        super.onStart()
        val actualUser = viewModel.getCurrentUser()
        actualUser?.reload()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel.getList()
        showRecyclerView()
        initObservers()

    }

    private fun showRecyclerView() {
        binding.rvTexts.layoutManager = LinearLayoutManager(this)
        binding.rvTexts.adapter = adapter
    }

    private fun initObservers() {
        viewModel.textState.observe(this) {
            adapter.updateTextList(it.toMutableList())
        }

        viewModel.messageState.observe(this) {
            loadMessage(it)
        }

    }

    private fun loadMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    }
