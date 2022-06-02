package com.example.lotteria.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.lotteria.R
import com.example.lotteria.databinding.FragmentHomePageBinding
import com.example.lotteria.ui.camera.CameraActivity

class HomePageFragment : Fragment() {

    private var _binding: FragmentHomePageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            buttonCamera.setOnClickListener {
                val intentCamera = Intent(requireActivity(), CameraActivity::class.java)
                startActivity(intentCamera)
            }
            smartDetectIcon.setOnClickListener {
                val intentCamera = Intent(requireActivity(), CameraActivity::class.java)
                startActivity(intentCamera)
            }
            importIcon.setOnClickListener {
                Toast.makeText(requireActivity(), R.string.not_working_feature, Toast.LENGTH_SHORT).show()
            }
            favoriteIcon.setOnClickListener {
                Toast.makeText(requireActivity(), R.string.not_working_feature, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}