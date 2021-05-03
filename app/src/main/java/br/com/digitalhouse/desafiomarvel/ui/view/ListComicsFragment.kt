package br.com.digitalhouse.desafiomarvel.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import br.com.digitalhouse.desafiomarvel.databinding.FragmentListComicsBinding
import br.com.digitalhouse.desafiomarvel.remote.api.MarvelApi
import br.com.digitalhouse.desafiomarvel.remote.model.ComicsResponse
import br.com.digitalhouse.desafiomarvel.ui.adapter.ListComicAdapter
import br.com.digitalhouse.desafiomarvel.ui.view.viewmodel.ListComicViewModel

class ListComicsFragment: Fragment(), ListComicAdapter.OnItemClickListener{

    private val viewModel: ListComicViewModel by viewModels(){
        object: ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ListComicViewModel(MarvelApi.create()) as T
            }
        }
    }

    private lateinit var binding: FragmentListComicsBinding
    private lateinit var comicResponse: ComicsResponse

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListComicsBinding.inflate(inflater, container, false)

        val comicAdapter = ListComicAdapter(null, this)
        val recyclerView = binding.recylerComics
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        recyclerView.adapter = comicAdapter

        viewModel.comicList.observe(viewLifecycleOwner){
            Log.i("ComicList", it.toString())
            comicResponse = it
            comicAdapter.comicResponse = comicResponse
            recyclerView.adapter = comicAdapter
        }
        viewModel.getSpiderManComics()
        return binding.root
    }

    override fun onItemClick(position: Int) {
        val clickedItem = comicResponse.data.results[position]

        NavHostFragment.findNavController(this).navigate(
            ListComicsFragmentDirections.actionListComicsFragmentToDetailComicFragment(
                "${clickedItem.thumbnail.path}.${clickedItem.thumbnail.extension}",
                clickedItem.title,
                clickedItem.description ?: "No description.",
                clickedItem.prices.first { i -> i.type == "printPrice" }.price.toFloat(),
                clickedItem.pageCount,
                clickedItem.dates.first{ i -> i.type == "focDate"}.date.substring(0,10),
                clickedItem
            )
        )
    }


}


