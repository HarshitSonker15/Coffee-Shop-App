package com.example.coffeeshopapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.coffeeshopapp.Adapter.ItemListCategoryAdapter
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.ViewModel.MainViewModel
import com.example.coffeeshopapp.databinding.ActivityItemsListBinding

class ItemsListActivity : AppCompatActivity() {

    lateinit var binding: ActivityItemsListBinding
    private val viewModel = MainViewModel()
    private var id: String=""
    private var title: String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityItemsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBundles()
        initList()
    }

    private fun initList() {
        binding.apply{
            progressBar.visibility= View.VISIBLE
            viewModel.loadItems(id).observe(this@ItemsListActivity, Observer{
                listView.layoutManager=
                    GridLayoutManager(this@ItemsListActivity,2)
                listView.adapter = ItemListCategoryAdapter(it)
                progressBar.visibility= View.GONE
            })
            backBtn.setOnClickListener { finish() }
        }
    }

    private fun getBundles(){
        id = intent.getStringExtra("id")!!
        title = intent.getStringExtra("title")!!

        binding.categoryTxt.text = title
    }
}