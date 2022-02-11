package com.example.stylophone.ui

import android.media.JetPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.stylophone.R

class MidiFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_midi, container, false)

        view.findViewById<Button>(R.id.button).setOnClickListener{
            Toast.makeText(requireContext(), "RRRRRRRRRRRRRRRR", Toast.LENGTH_SHORT).show()

            

        }


        return view
    }

}