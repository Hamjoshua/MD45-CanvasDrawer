package com.example.md45_canvasdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Orientation
import com.example.md45_canvasdrawer.color_objects.ColorDataClass
import com.example.md45_canvasdrawer.color_objects.ColorListAdapter
import com.example.md45_canvasdrawer.color_objects.IColorButtonOnclick
import com.example.md45_canvasdrawer.color_objects.colorList
import com.example.md45_canvasdrawer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), IColorButtonOnclick {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initColorList()
    }

    fun initColorList(){
        val listAdapter = ColorListAdapter(this)
        listAdapter.apply {
            currentList = colorList
        }

        val horizLayoutManager = LinearLayoutManager(this)
        layoutManager.apply {
            orientation = RecyclerView.HORIZONTAL
        }

        binding.colorList.apply {
            adapter = listAdapter
            layoutManager = horizLayoutManager
        }
    }

    override fun onColorButtonClick(colorDC: ColorDataClass) {
        Toast.makeText(this, "Выбран ${colorDC.name} цвет", Toast.LENGTH_SHORT).show()
        // TODO смена текса
    }
}