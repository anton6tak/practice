package com.a6tak.practice.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.toRoute
import com.a6tak.practice.PracticeApp
import com.a6tak.practice.R
import com.a6tak.practice.databinding.ProfileFragmentBinding
import kotlinx.coroutines.launch
import kotlin.random.Random

class ProfileFragment : Fragment(R.layout.profile_fragment) {
    private var _binding: ProfileFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProfileFragmentBinding.inflate(layoutInflater)
        val view = binding.root
        val profileRoute = findNavController().getBackStackEntry<Profile>().toRoute<Profile>()
        val profileId = profileRoute.name

        val repo = (this.requireContext().applicationContext as PracticeApp).repo
        val viewModel: ProfileViewModel by viewModels { ProfileViewModelFactory(repo) }

        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    val scrollState = rememberScrollState()
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
//                        modifier = Modifier.verticalScroll(scrollState)
                    ) {
                        val scope = rememberCoroutineScope()
                        val state by viewModel.state.collectAsStateWithLifecycle()

                        TextButton(onClick = { scope.launch { viewModel.resetAndRestart() } }) {
                            Text(text = "Reset and restart")
                        }

                        var randomColor by remember {
                            mutableStateOf(generateRandomColor())
                        }

                        Card(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
//                            Image(
//                                painter = painterResource(id = R.drawable.img_2),
//                                contentDescription = "Warhammer",
//                                contentScale = ContentScale.Crop,
//                                modifier = Modifier.fillMaxWidth()
//                            )
                        }

                        Button(colors = ButtonDefaults.buttonColors(
                            containerColor = randomColor,
                            contentColor = Color.Black
                        ), onClick = {
                            randomColor = generateRandomColor()
                        }) {
                            Text(text = "Play color")
                        }

                        when (val currentState = state) {
                            is ProfileViewModel.State.Data -> {
                                var inputNews by rememberSaveable {
                                    mutableStateOf("")
                                }
                                TextField(value = inputNews, onValueChange = {
                                    inputNews = it
                                })
                                Button(onClick = { scope.launch { viewModel.addNewItem(inputNews) } }) {
                                    Text(text = "ADD")
                                }
                                LazyColumn {
                                    items(currentState.list) {
                                        Row {
                                            Text(
                                                modifier = Modifier
                                                    .background(Color.LightGray)
                                                    .fillMaxWidth(),
                                                fontSize = 15.sp,
                                                text = it.title
                                            )
                                            Text(
                                                modifier = Modifier
                                                    .background(Color.Cyan)
                                                    .fillParentMaxWidth(),
                                                fontSize = 10.sp,
                                                text = it.message
                                            )
                                        }
                                    }
                                }
                            }

                            ProfileViewModel.State.Idle -> {
                                Text("IDLE")
                                Button(onClick = {
                                    scope.launch {
                                        viewModel.loadNews()
                                    }
                                }) {
                                    Text(text = "RUN!")
                                }
                            }

                            ProfileViewModel.State.Loading -> {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        }
        return view
    }

    fun generateRandomColor(): Color {
        val red = Random.nextFloat() // Random value between 0.0 and 1.0 for red
        val green = Random.nextFloat() // Random value between 0.0 and 1.0 for green
        val blue = Random.nextFloat() // Random value between 0.0 and 1.0 for blue
        return Color(red, green, blue, 1f) // Set alpha to 1f for full opacity
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}