package br.com.digitalhouse.desafiomarvel.ui.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.digitalhouse.desafiomarvel.domain.utils.SPIDER_MAN_ID
import br.com.digitalhouse.desafiomarvel.remote.api.MarvelApi
import br.com.digitalhouse.desafiomarvel.remote.model.ComicsResponse
import kotlinx.coroutines.launch

class ListComicViewModel(val repository: MarvelApi): ViewModel() {
    val comicList = MutableLiveData<ComicsResponse>()

    fun getSpiderManComics(){
        getComicsByCharacterId(SPIDER_MAN_ID)
    }

    private fun getComicsByCharacterId(characterId: Int){
        viewModelScope.launch {
            comicList.value = repository.getComicDateByCharacterId(characterId)
        }
    }
}