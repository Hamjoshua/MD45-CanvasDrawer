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
import com.example.md45_canvasdrawer.color_objects.colorDataList
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
        var listAdapter = ColorListAdapter(this)
        listAdapter.submitList(colorDataList)

        var horizontalLayoutManager = LinearLayoutManager(this)
        horizontalLayoutManager.orientation = RecyclerView.HORIZONTAL

        binding.colorList.apply {
            adapter = listAdapter
            layoutManager = horizontalLayoutManager
        }
    }

    override fun onColorButtonClick(colorDC: ColorDataClass) {
        Toast.makeText(this, "Выбран ${colorDC.name} цвет", Toast.LENGTH_SHORT).show()
        binding.drawer.paintColor = colorDC.color
    }
}