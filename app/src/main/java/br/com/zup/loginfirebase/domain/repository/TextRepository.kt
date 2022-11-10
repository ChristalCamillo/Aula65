package br.com.zup.loginfirebase.domain.repository

import br.com.zup.loginfirebase.utils.APP_PATH
import br.com.zup.loginfirebase.utils.TEXT_PATH
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.ktx.Firebase

class TextRepository {
    private val authentication: FirebaseAuth = Firebase.auth
    private val database = FirebaseDatabase.getInstance()
    private val reference = database.getReference("$APP_PATH/${authentication.currentUser?.uid}/$TEXT_PATH")

    fun databaseReference() = reference

    fun getTextList(): Query {
        return reference.orderByValue()
    }
}