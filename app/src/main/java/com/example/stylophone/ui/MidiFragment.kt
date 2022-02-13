package com.example.stylophone.ui

import android.media.AudioFormat
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.stylophone.R
import android.media.AudioTrack

import android.media.AudioManager
import android.view.MotionEvent

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import com.example.stylophone.viewModel.MainViewModel


class MidiFragment : Fragment() {

    //--------------------values
    var isPlaying: Boolean = false
    lateinit var Track: AudioTrack
    val Fs: Int = 44100
    val buffLength: Int = AudioTrack.getMinBufferSize(Fs, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT)
    private lateinit var mainViewModel: MainViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_midi, container, false)


        //buttons
        val A = view.findViewById<Button>(R.id.n1)
        val B = view.findViewById<Button>(R.id.n2)
        val C = view.findViewById<Button>(R.id.n3)
        val D = view.findViewById<Button>(R.id.n4)
        val E = view.findViewById<Button>(R.id.n5)
        val F = view.findViewById<Button>(R.id.n6)
        val G = view.findViewById<Button>(R.id.n7)

        val a = view.findViewById<Button>(R.id.n8)
        val b = view.findViewById<Button>(R.id.n9)
        val c = view.findViewById<Button>(R.id.n10)
        val d = view.findViewById<Button>(R.id.n11)
        val e = view.findViewById<Button>(R.id.n12)

        val Ac = view.findViewById<Button>(R.id.n1_5)
        val Cc = view.findViewById<Button>(R.id.n3_5)
        val Dc = view.findViewById<Button>(R.id.n4_5)
        val Fc = view.findViewById<Button>(R.id.n6_5)
        val Gc = view.findViewById<Button>(R.id.n7_5)

        val ac = view.findViewById<Button>(R.id.n8_5)
        val cc = view.findViewById<Button>(R.id.n10_5)
        val dc = view.findViewById<Button>(R.id.n11_5)

        //notes
        note(A, 440)
        note(B, 493)
        note(C, 523)
        note(D, 587)
        note(E, 659)
        note(F, 692)
        note(G, 784)

        note(a, 880)
        note(b, 986)
        note(c, 1046)
        note(d, 1174)
        note(e, 1368)

        note(Ac, 466)
        note(Cc, 554)
        note(Dc, 622)
        note(Fc, 740)
        note(Gc, 830)

        note(ac, 932)
        note(cc, 1108)
        note(dc, 1244)



        return view
    }

    @SuppressLint("ClickableViewAccessibility")
    fun note(btn: Button, i: Int) {

        btn.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                // Pressed
                if (!isPlaying) {
                    mainViewModel.setNum(i.toFloat())
                    Thread {
                        initTrack()
                        startPlaying()
                        playback(i)
                    }.start()
                }
            }
            else if (event.action == MotionEvent.ACTION_UP) {
                // Released
                stopPlaying()
                mainViewModel.setNum(1f)
            }
            true
        }

    }

    private fun initTrack() {
        Track = AudioTrack(
            AudioManager.MODE_NORMAL, Fs, AudioFormat.CHANNEL_OUT_MONO,
            AudioFormat.ENCODING_PCM_16BIT, buffLength, AudioTrack.MODE_STREAM
        )
    }

    private fun playback(i: Int) {
        val frame_out = ShortArray(buffLength)
        val amplitude = 42767
        val frequency = i
        val twopi: Double = 8.0 * Math.atan(1.0)
        var phase = 0.0

        while (isPlaying) {
            for (i in 0 until buffLength) {
                frame_out[i] = (amplitude * Math.sin(phase)).toInt().toShort()
                phase += twopi * frequency / Fs
                if (phase > twopi) {
                    phase -= twopi
                }
            }
            Track.write(frame_out, 0, buffLength)
        }
    }

    private fun startPlaying() {
        Track.play()
        isPlaying = true
    }

    private fun stopPlaying() {
        if (isPlaying) {
            isPlaying = false
            // Stop playing the audio data and release the resources
            Track.stop()
            Track.release()
        }
    }


}


