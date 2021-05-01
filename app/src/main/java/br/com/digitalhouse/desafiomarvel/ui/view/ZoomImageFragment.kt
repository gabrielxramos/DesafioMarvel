package br.com.digitalhouse.desafiomarvel.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.digitalhouse.desafiomarvel.databinding.FragmentZoomImageBinding
import com.squareup.picasso.Picasso


class ZoomImageFragment : Fragment() {
    private val args: ZoomImageFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentZoomImageBinding.inflate(inflater, container, false)

        val navController = findNavController()
        val imgUrl = args.imgUrl

        binding.toolbarZoom.setNavigationOnClickListener {
            navController.navigateUp()
        }

        Picasso.get().load(imgUrl).fit().centerInside().into(binding.zoomImage)

        return binding.root
    }

}