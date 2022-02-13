package com.example.stylophone.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stylophone.R
import com.example.stylophone.ui.graphic.Oscilloscope
import processing.android.CompatUtils
import processing.android.PFragment


class CanvasFragment : Fragment() {

    //------------values
    lateinit var sketch:Oscilloscope


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_canvas, container, false)

        view.id = CompatUtils.getUniqueViewId()
        sketch = Oscilloscope()
        val fragment = PFragment(sketch)
        fragment.setView(view, requireActivity())

        return view
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        sketch.onRequestPermissionsResult(
            requestCode, permissions, grantResults
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        sketch.pause()
    }


}