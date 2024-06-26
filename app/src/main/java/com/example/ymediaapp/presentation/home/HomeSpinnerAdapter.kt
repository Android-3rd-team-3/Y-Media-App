package com.example.ymediaapp.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.ymediaapp.databinding.SpinnerItemDropdownHomeBinding
import com.example.ymediaapp.databinding.SpinnerItemHomeBinding
import com.example.ymediaapp.presentation.model.CategoryModel

class HomeSpinnerAdapter(
    context: Context,
    items: List<CategoryModel>,
): ArrayAdapter<CategoryModel>(context, 0, items) {
    private var itemList = items

    override fun getItem(position: Int): CategoryModel? {
        return itemList[position]
    }

    override fun getCount(): Int {
        return itemList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: SpinnerItemHomeBinding
        val view: View

        if (convertView == null){
            binding = SpinnerItemHomeBinding.inflate(LayoutInflater.from(context), parent, false)
            view = binding.root
            view.tag = binding
        }else{
            binding = convertView.tag as SpinnerItemHomeBinding
            view = convertView
        }

        val item = getItem(position)
        binding.tvSpinnerItem.text = item?.name

        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: SpinnerItemDropdownHomeBinding
        val view: View

        if (convertView == null){
            binding = SpinnerItemDropdownHomeBinding.inflate(LayoutInflater.from(context), parent, false)
            view = binding.root
            view.tag = binding
        }else{
            binding = convertView.tag as SpinnerItemDropdownHomeBinding
            view = convertView
        }

        val item = getItem(position)
        binding.tvSpinnerItem.text = item?.name

        return view
    }

    fun updateItems(newItems: List<CategoryModel>) {
        itemList = newItems
        notifyDataSetChanged()
    }

}