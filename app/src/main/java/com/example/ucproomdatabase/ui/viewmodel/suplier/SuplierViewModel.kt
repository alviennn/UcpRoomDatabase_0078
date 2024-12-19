package com.example.ucproomdatabase.ui.viewmodel.suplier

import com.example.ucproomdatabase.data.entity.Suplier

data class FormErrorState(
    val idsuplier: String? = null,
    val namasuplier: String? = null,
    val kontak: String? = null,
    val alamat: String? = null
){
    fun isValid(): Boolean{
        return idsuplier != null && namasuplier != null && kontak != null && alamat != null
    }
}

data class SuplierEvent(
    val idsuplier: String = "",
    val namasuplier: String = "",
    val kontak: String = "",
    val alamat: String = ""
)

fun SuplierEvent.toSuplierEntity(): Suplier = Suplier(
    idsuplier = idsuplier,
    namasuplier = namasuplier,
    kontak = kontak,
    alamat = alamat
)