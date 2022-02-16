package com.example.stylophone.ui.graphic

import com.example.stylophone.viewModel.MainViewModel
import processing.core.PApplet

class Oscilloscope(var mainViewModel: MainViewModel): PApplet() {

    //--------------values
    //var period: Float = 1.0f

    var xspacing: Int = 8
    var w: Int = 0

    var theta: Float = 0.0f
    var amplitude: Float = 40.0f

    var dx: Float = 0.0f
    lateinit var yvalues: FloatArray

    override fun setup() {
        background(0f)
        w = width+16

    }

    override fun draw() {

        var period: Float? = mainViewModel.num.value

        if (period == null){
            dx = (TWO_PI / 1) * xspacing
        }
        else{dx = (TWO_PI / period) * xspacing}

        yvalues = FloatArray(w / xspacing)

        background(0f)
        calcWave()
        renderWave()
    }
    fun calcWave() {
        theta += 0.02f

        var x = theta
        for (i in 0 until yvalues.size) {
            yvalues[i] = sin(x) * amplitude
            x += dx
        }
    }

    fun renderWave() {
        noStroke()
        fill(255)
        for (x in 0 until yvalues.size) {
            // ! #ed1e79
            fill(237f, 30f, 121f)
            stroke(255f)
            strokeWeight(2f)
            ellipse((x * xspacing).toFloat(), height / 2 + yvalues[x], 16f, 16f)
        }
    }

}