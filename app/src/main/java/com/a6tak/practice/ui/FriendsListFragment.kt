package com.a6tak.practice.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.a6tak.practice.R
import com.a6tak.practice.databinding.FriendsListFragmentBinding

class FriendsListFragment : Fragment(R.layout.friends_list_fragment) {

    private var _binding: FriendsListFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FriendsListFragmentBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataset = mutableListOf<Item>()
        repeat(100) {
            if (it % 5 == 0) {
                val imgRes = when ((1..3).random()) {
                    1 -> R.drawable.img_3
                    2 -> R.drawable.img_2
                    else -> R.drawable.img_4
                }
                dataset.add(Item.Image(imgRes))
            } else dataset.add(Item.News("test $it"))

        }

        val adapter = CustomAdapter(
            dataset,
            itemClick = ::navigateWithName
        )

        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter
    }

    fun navigateWithName(s: String) {
        findNavController().navigate(route = Profile(name = s))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    sealed interface Item {
        fun getType(): Int

        data class News(val text: String) : Item {
            override fun getType(): Int = TYPE

            companion object {
                const val TYPE = 1
            }
        }

        data class Image(@DrawableRes val imageRes: Int) : Item {
            override fun getType(): Int = TYPE

            companion object {
                const val TYPE = 2
            }
        }
    }
}