package com.example.spacexchallenge.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.spacexchallenge.databinding.ActivityMainBinding
import com.example.spacexchallenge.ui.xml.fragments.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            SpaceXChallengeTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    NavigationView()
//                }
//            }
//        }
//    }
//}
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = binding.navHostFragment.getFragment<NavHostFragment>()
        navController = navHostFragment.navController
    }

    fun navigateToLaunchDetails(launchId: String) {
        val directions = HomeFragmentDirections.actionHomeToLaunchDetails(launchId)
        navController.navigate(directions)
    }

    fun popBackStack() {
        navController.popBackStack()
    }
}