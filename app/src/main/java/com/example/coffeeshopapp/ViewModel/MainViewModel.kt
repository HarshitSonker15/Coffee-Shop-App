package com.example.coffeeshopapp.ViewModel

import android.content.ClipData.Item
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.coffeeshopapp.Domain.BannerModel
import com.example.coffeeshopapp.Domain.CategoryModel
import com.example.coffeeshopapp.Domain.ItemsModel
import com.example.coffeeshopapp.Repository.MainRepository

class MainViewModel: ViewModel() {

    private val repository= MainRepository()

    fun loadBanner(): LiveData<MutableList<BannerModel>>{
        return repository.loadBanner()
    }

    fun loadCategory(): LiveData<MutableList<CategoryModel>>{
        return repository.loadCategory()
    }

    fun loadPopular(): LiveData<MutableList<ItemsModel>>{
        return repository.loadPopular()
    }

    fun loadItems(categoryId: String): LiveData<MutableList<ItemsModel>>{
        return repository.loadItemCategory(categoryId)
    }
}