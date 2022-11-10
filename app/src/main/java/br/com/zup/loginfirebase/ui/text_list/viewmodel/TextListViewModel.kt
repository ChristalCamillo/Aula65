package br.com.zup.loginfirebase.ui.text_list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.cafeteriasimcity.domain.repository.AuthenticationRepository
import br.com.zup.loginfirebase.data.datasource.model.Text
import br.com.zup.loginfirebase.domain.repository.TextRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class TextListViewModel: ViewModel(){
    private val textRepository = TextRepository()
    private val authRepository = AuthenticationRepository()

    private var _textListState = MutableLiveData<List<Text>>()
    val textState: LiveData<List<Text>> = _textListState

    private var _messageState = MutableLiveData<String>()
    val messageState: LiveData<String> = _messageState

    fun getList() {
        textRepository.getTextList()
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    val textList = mutableListOf<Text>()

                    for (resultSnapshot in snapshot.children) {
                        val saveResponse = resultSnapshot.getValue(Text::class.java)
                        saveResponse?.let { textList.add(it) }
                    }
                    _textListState.value = textList
                }

                override fun onCancelled(error: DatabaseError) {
                    _messageState.value = error.message
                }
            })
    }

    fun getCurrentUser() = authRepository.getCurrentUser()
}