package com.a6tak.practice.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.createGraph
import androidx.navigation.findNavController
import androidx.navigation.fragment.fragment
import com.a6tak.practice.R
import com.a6tak.practice.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        enableEdgeToEdge()
        val navController = findNavController(R.id.nav_host_fragment)
//        println(navController)
        navController.graph = navController.createGraph(
            startDestination = FriendsList
        ) {
            // Associate each destination with one of the route constants.
            fragment<ProfileFragment, Profile> {
                label = "Profile"
            }

            fragment<FriendsListFragment, FriendsList>() {
                label = "Friends List"
            }

            // Add other fragment destinations similarly.
        }


//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }
}

