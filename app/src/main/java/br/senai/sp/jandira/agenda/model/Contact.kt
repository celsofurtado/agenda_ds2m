package br.senai.sp.jandira.agenda.model

import android.graphics.drawable.Drawable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "tbl_contact")
class Contact {

    @PrimaryKey(autoGenerate = true)
    var id = 0

    var nome = ""
    var telefone = ""
    var email = ""

    @ColumnInfo(name = "data_nascimento")
    var dataNascimento = ""

    var foto = ""

}