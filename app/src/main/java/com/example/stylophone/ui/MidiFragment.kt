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


class MidiFragment : Fragment() {

    //--------------------values
    var isPlaying: Boolean = false
    lateinit var Track: AudioTrack
    val Fs: Int = 44100
    val buffLength: Int = AudioTrack.getMinBufferSize(Fs, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT)

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_midi, container, false)

        //buttons
        val A = view.findViewById<Button>(R.id.Anote)
        val B = view.findViewById<Button>(R.id.Bnote)
        val C = view.findViewById<Button>(R.id.Cnote)

        //notes
        note(A, 440)
        note(B, 493)
        note(C, 523)


        return view
    }

    @SuppressLint("ClickableViewAccessibility")
    fun note(btn: Button, i: Int) {

        btn.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                // Pressed
                if (!isPlaying) {

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
        val twopi: Double = 16.0 * Math.atan(1.0)
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


