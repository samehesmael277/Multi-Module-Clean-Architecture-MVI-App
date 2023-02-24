package com.example.multimodulecleanarchitectureapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.Category
import com.example.multimodulecleanarchitectureapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var mealsAdapter: MealsAdapter

    private val mealsViewModel: MealsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecView()

        stateObserver()

        binding.btnGetMeals.setOnClickListener {
            lifecycleScope.launch {
                mealsViewModel.intentChannel.send(MainIntent.GetMeals)
            }
        }
    }

    private fun setUpRecView() {
        binding.recView.adapter = mealsAdapter
        binding.recView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun stateObserver() {
        lifecycleScope.launchWhenStarted {
            mealsViewModel.state.collect {
                when (it) {
                    is MainState.Idle -> {}
                    is MainState.Loading -> {
                        showProgressBar()
                    }
                    is MainState.Error -> {
                        hideProgressBar()
                        showErrorToast(it.message)
                    }
                    is MainState.Meals -> {
                        hideProgressBar()
                        setDataToAdapter(it.meals.categories)
                    }
                }
            }
        }
    }

    private fun showErrorToast(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun setDataToAdapter(categories: List<Category>) {
        mealsAdapter.submitList(categories)
    }
}