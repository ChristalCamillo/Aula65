package br.com.zup.loginfirebase.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import br.com.zup.cafeteriasimcity.domain.repository.AuthenticationRepository

class HomeViewModel : ViewModel() {
    private val repository = AuthenticationRepository()

    fun getNameUser() = repository.getNameUser()

    fun getEmailUser() = repository.getEmailUser()

    fun logoutUser() {
        repository.logoutOut()
    }
}