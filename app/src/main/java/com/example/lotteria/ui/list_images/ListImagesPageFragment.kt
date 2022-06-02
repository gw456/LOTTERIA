package com.example.lotteria.ui.list_images

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.lotteria.R
import com.example.lotteria.databinding.FragmentListImagesPageBinding
import com.example.lotteria.ui.camera.CameraActivity

class ListImagesPageFragment : Fragment() {

    private var _binding: FragmentListImagesPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListImagesPageBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            dropdownTag.text = R.string.filter_type.toString()
            sortImage.setOnClickListener {
                Toast.makeText(requireActivity(), R.string.not_working_feature, Toast.LENGTH_SHORT).show()
            }
            changeLayout.setOnClickListener {
                Toast.makeText(requireActivity(), R.string.not_working_feature, Toast.LENGTH_SHORT).show()
            }
            imageLabel.setOnClickListener {
                Toast.makeText(requireActivity(), R.string.not_working_feature, Toast.LENGTH_SHORT).show()
            }
            buttonCamera.setOnClickListener {
                val intentCamera = Intent(requireActivity(), CameraActivity::class.java)
                startActivity(intentCamera)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}