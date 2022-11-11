package br.senai.sp.jandira.agenda.repository

import android.content.Context
import br.senai.sp.jandira.agenda.dao.ContactDb
import br.senai.sp.jandira.agenda.model.Contact

class ContactRepository(context: Context) {

    private val db = ContactDb
        .getDataBase(context)
        .contactDao()

    fun save(contact: Contact): Long {
        return db.save(contact)
    }

    fun update(contact: Contact): Int {
        return db.update(contact)
    }

    fun delete(contact: Contact): Int {
        return db.delete(contact)
    }

    fun getAll(): List<Contact> {
        return db.getAll()
    }

    fun getContactById(id: Int): Contact {
        return db.getContactById(id)
    }

}