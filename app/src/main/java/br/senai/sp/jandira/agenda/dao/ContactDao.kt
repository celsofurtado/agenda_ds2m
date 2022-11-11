package br.senai.sp.jandira.agenda.dao

import androidx.room.*
import br.senai.sp.jandira.agenda.model.Contact

@Dao
interface ContactDao {

    @Insert
    fun save(contact: Contact): Long

    @Delete
    fun delete(contact: Contact): Int

    @Update
    fun update(contact: Contact): Int

    @Query("SELECT * FROM tbl_contact ORDER BY nome ASC")
    fun getAll(): List<Contact>

    @Query("SELECT * FROM tbl_contact WHERE id = :id")
    fun getContactById(id: Int): Contact

}