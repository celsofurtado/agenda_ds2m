package br.senai.sp.jandira.agenda.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.senai.sp.jandira.agenda.R
import br.senai.sp.jandira.agenda.adapter.ContactAdapter
import br.senai.sp.jandira.agenda.databinding.ActivityMainBinding
import br.senai.sp.jandira.agenda.repository.ContactRepository

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: ContactAdapter
    lateinit var repository: ContactRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fbNewContact.setOnClickListener {
            val abrirCadastro =
                Intent(this, NewContactActivity::class.java)

            startActivity(abrirCadastro)

        }



    }

    override fun onResume() {
        super.onResume()
        carregarRecyclerView()
    }

    private fun carregarRecyclerView() {

        repository = ContactRepository(this)

        val contacts = repository.getAll()
        adapter = ContactAdapter(contacts, this)
        binding.rvContatos.adapter = adapter

        binding.rvContatos.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false)

    }
}