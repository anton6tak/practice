package com.a6tak.practice.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.toRoute
import com.a6tak.practice.PracticeApp
import com.a6tak.practice.R
import com.a6tak.practice.databinding.ProfileFragmentBinding
import com.a6tak.practice.db.NewsEntity
import kotlinx.coroutines.launch

class ProfileFragment : Fragment(R.layout.profile_fragment) {
    private var _binding: ProfileFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProfileFragmentBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        val profileRoute = findNavController().getBackStackEntry<Profile>().toRoute<Profile>()
        val profileId = profileRoute.name

//        val dao = (requireContext().applicationContext as PracticeApp).database.newsDao()



        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    var list = remember {
                        mutableStateListOf<Int>()
                    }






                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text("Hello Compose! $profileId")
                        Row {
                            Button(onClick = {
                                list.add((0..100).random())
                            }) {
                                Text("Plus")
                            }
                            Button(onClick = {
//                                list.removeFirst()
                            }) {
                                Text("remove first")
                            }
                        }
                        LazyColumn(
                            modifier = Modifier
//                                .fillMaxHeight()
                                .padding(10.dp)
                                .border(
                                    border = BorderStroke(1.dp, Color.Blue),
                                    shape = CutCornerShape(5.dp)
                                )
                                .fillMaxSize()
//                                .verticalScroll(
//                                    rememberScrollState()
//                                )
                        ) {
                            item {
                                Text("Header Content", modifier = Modifier
                                    .padding(16.dp)
                                    .background(Color.Red))
                            }

                            items(100) { item ->
                                if (item % 2 == 0) {
                                    Text(
                                        modifier = Modifier
                                            .background(color = Color.Gray)
                                            .fillMaxSize(),
                                        text = "$item"
                                    )
                                } else {
                                    Text("$item")
                                }

                            }
                        }
                    }
                }
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}