package com.example.md45_canvasdrawer

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.md45_canvasdrawer.color_objects.ColorDataClass
import com.example.md45_canvasdrawer.color_objects.ColorListAdapter
import com.example.md45_canvasdrawer.color_objects.IColorButtonOnclick
import com.example.md45_canvasdrawer.color_objects.colorDataList
import com.example.md45_canvasdrawer.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), IColorButtonOnclick {
    private lateinit var binding: ActivityMainBinding
    val REQUEST_CODE_PICK_IMAGE: Int = 666
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initColorList()
        initSizeBar()
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

    fun openImage(){
        val intent = Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_PICK_IMAGE &&
            resultCode == Activity.RESULT_OK) {
            val uri = data?.data ?: return
            binding.drawer.openDrawing(uri)
        }
    }

    fun initSizeBar(){
        binding.sizeBar.progress = binding.drawer.paintSize.toInt()
        binding.sizeBarText.setText(binding.sizeBar.progress.toString())

        binding.sizeBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                binding.drawer.paintSize = binding.sizeBar.progress.toFloat()
                binding.sizeBarText.setText(binding.sizeBar.progress.toString())

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // TODO Auto-generated method stub
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // TODO Auto-generated method stub
            }
        })
    }

    override fun onColorButtonClick(colorDC: ColorDataClass) {
        Toast.makeText(this, "Выбран ${colorDC.name} цвет", Toast.LENGTH_SHORT).show()
        binding.drawer.paintColor = colorDC.color
    }
}