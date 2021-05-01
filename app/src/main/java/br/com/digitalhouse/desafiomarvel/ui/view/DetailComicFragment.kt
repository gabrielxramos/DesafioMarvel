package br.com.digitalhouse.desafiomarvel.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.digitalhouse.desafiomarvel.databinding.FragmentDetailComicBinding
import com.squareup.picasso.Picasso

class DetailComicFragment : Fragment() {

    private lateinit var binding: FragmentDetailComicBinding
    private val args: DetailComicFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailComicBinding.inflate(inflater, container, false)

        val navController = findNavController()

        val comicTitleText = args.comicResponseResult.title
        val comicDescriptionText = args.comicResponseResult.description //args.comicDescription
        val comicPriceText =
            "$${args.comicResponseResult.prices.first { i -> i.type == "printPrice" }.price.toFloat()}" //"$${args.comicPrice}"
        val pageCountText =
            args.comicResponseResult.pageCount.toString() //args.pageCount.toString()
        val imgUrl =
            "${args.comicResponseResult.thumbnail.path}.${args.comicResponseResult.thumbnail.extension}" //args.imgUrl
        val publicationDate =
            args.comicResponseResult.dates.first { i -> i.type == "focDate" }.date.substring(0, 10)

        //setando os textos em seus respectivos lugares
        binding.comicTitle.text = comicTitleText
        binding.comicDescrip.text = comicDescriptionText
        binding.comicPrice.text = comicPriceText
        binding.pageCount.text = pageCountText
        binding.publicationDate.text = publicationDate

        binding.toolbarDetail.setNavigationOnClickListener {
            navController.navigateUp()
        }

        binding.bannerImage.setOnClickListener {
            navController.navigate(
                DetailComicFragmentDirections.actionDetailComicFragmentToZoomImageFragment(
                    imgUrl
                )
            )
        }

        //carregando a imagem de fundo
        Picasso.get().load(imgUrl).fit().centerCrop().into(binding.bannerImage)
        //carregando o poster da hq
        Picasso.get().load(imgUrl).fit().centerCrop().into(binding.posterHq)

        return binding.root
    }
}