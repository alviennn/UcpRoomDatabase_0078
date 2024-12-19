package com.example.ucproomdatabase.ui.viewmodel.suplier

import com.example.ucproomdatabase.data.entity.Suplier

fun SuplierEvent.toSuplierEntity(): Suplier = Suplier(
    idsuplier = idsuplier,
    namasuplier = namasuplier,
    kontak = kontak,
    alamat = alamat
)