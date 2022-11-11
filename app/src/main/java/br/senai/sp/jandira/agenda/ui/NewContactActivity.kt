package br.senai.sp.jandira.agenda.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import br.senai.sp.jandira.agenda.R
import br.senai.sp.jandira.agenda.databinding.ActivityNewContactBinding
import br.senai.sp.jandira.agenda.model.Contact
import br.senai.sp.jandira.agenda.repository.ContactRepository
import java.time.LocalDate

class NewContactActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewContactBinding
    lateinit var contactRepository: ContactRepository
    lateinit var contato: Contact
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewContactBinding.inflate(layoutInflater)

        setContentView(binding.root)
        contato = Contact()

        binding.buttonSave.setOnClickListener {
            save()
        }

        binding.buttonExcluir.setOnClickListener {
            excluir()
        }

        id = intent.getIntExtra("id", 0)

        if(id > 0) {
            binding.buttonExcluir.visibility = View.VISIBLE
            binding.buttonSave.text = "Atualizar"
            carregarContato()
        }

    }

    private fun excluir() {

        val confirmacao = AlertDialog.Builder(this)
        confirmacao.setTitle("Exclusão")
        confirmacao.setMessage("Confirma a exclusão do(a) ${contato.nome}?")

        confirmacao.setPositiveButton("Sim, por favor"){ _, _ ->
            contactRepository.delete(contato)
            finish()
        }

        confirmacao.setNegativeButton("Não, deixa pra lá"){ _, _ ->

        }

        confirmacao.show()


    }

    private fun carregarContato() {

        contactRepository = ContactRepository(this)
        contato = contactRepository.getContactById(id)

        binding.txtEmail.setText(contato.email)
        binding.txtNome.setText(contato.nome)
        binding.txtTelefone.setText(contato.telefone)

    }

    private fun save() {

        // Criar o objeto Contact
        contato.dataNascimento =
            binding.txtDataNascimento.text.toString()

        contato.email = binding.txtEmail.text.toString()
        contato.nome = binding.txtNome.text.toString()
        contato.telefone = binding.txtTelefone.text.toString()

        // Criar uma instância do repositório
        contactRepository = ContactRepository(this)

        if(id > 0) {
            contactRepository.update(contato)
        } else {
            val id = contactRepository.save(contato)
        }

        Toast.makeText(
            this,
            "ID: $id",
            Toast.LENGTH_LONG).show()

        finish()

    }
}