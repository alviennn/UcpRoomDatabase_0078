package com.example.ucproomdatabase.dependenciesinjection

import android.content.Context
import com.example.ucproomdatabase.data.database.TokoDatabase
import com.example.ucproomdatabase.repository.LocalRepositoryBrg
import com.example.ucproomdatabase.repository.LocalRepositorySpl
import com.example.ucproomdatabase.repository.RepositoryBrg
import com.example.ucproomdatabase.repository.RepositorySpl

interface InterfaceContainerApp{
    val repositoryBrg: RepositoryBrg
    val repositorySpl: RepositorySpl
}

class ContainerApp(private val context: Context) : InterfaceContainerApp{
    override val repositoryBrg: RepositoryBrg by lazy {
        LocalRepositoryBrg(TokoDatabase.getDatabase(context).barangDao())
        }
    override val repositorySpl: RepositorySpl by lazy {
        LocalRepositorySpl(TokoDatabase.getDatabase(context).suplierDao())
    }
}