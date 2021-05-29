package id.rama.githubapp.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.rama.githubapp.repository.RepoUsers
import id.rama.githubapp.viewmodel.ViewModelUsers

@Suppress("UNCHECKED_CAST")
class FactoryUsers(private val repo : RepoUsers) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelUsers(repo) as T
    }
}