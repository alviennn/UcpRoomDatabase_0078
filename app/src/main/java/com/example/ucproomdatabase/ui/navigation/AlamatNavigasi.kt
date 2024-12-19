package com.example.ucproomdatabase.ui.navigation

import com.example.ucproomdatabase.ui.navigation.DestinasiDetail.idBarang

interface AlamatNavigasi{
    val route: String
}

object DestinasiHome : AlamatNavigasi {
    override val route = "home"
}
object DestinasiInsert: AlamatNavigasi {
    override val route = "insert"
}
object DestinasiDetail: AlamatNavigasi {
    override val route = "detail"
    const val idSuplier = "idSuplier"
    const val idBarang = "idBarang"
    val routeWithArg = "$route/{$idSuplier}/{$idBarang}"
}
object DestinasiUpdate: AlamatNavigasi {
    override val route = "update"
    const val idSuplier = "idBarang"
    val routeWithArg = "$route/{$idBarang}"
}