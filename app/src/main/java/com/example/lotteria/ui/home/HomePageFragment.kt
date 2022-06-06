package com.example.lotteria.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lotteria.R
import com.example.lotteria.data.Image
import com.example.lotteria.databinding.FragmentHomePageBinding
import com.example.lotteria.helper.ListRecentImageAdapter
import com.example.lotteria.ui.camera.CameraActivity
import com.example.lotteria.ui.detail.DetailPageActivity
import java.io.File
import java.nio.file.Files
import java.nio.file.attribute.BasicFileAttributes

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
            recentGambarBakteri.layoutManager = LinearLayoutManager(activity)
            recentGambarBakteri.addItemDecoration(DividerItemDecoration(
                activity,
                LinearLayoutManager(activity).orientation))
            setImagesList()
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

    private fun setImagesList() {
        val listImages = ArrayList<Image>()
        val path = "/storage/emulated/0/Download/LOTTERIA"
        val directory = File(path)
        val files = directory.listFiles()
        if (files != null) {
            files.forEach {
                val attrs = Files.readAttributes(it.toPath(), BasicFileAttributes::class.java)
                val gambar = Image(it.nameWithoutExtension,
                    it.absolutePath,
                    attrs.lastAccessTime().toString(),
                    null)
                listImages.add(gambar)
            }
        }
        val adapter = ListRecentImageAdapter(listImages, 10)
        binding.recentGambarBakteri.adapter = adapter

        adapter.setOnItemClickCallback(object : ListRecentImageAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Image) {
                val intentToDetail = Intent(activity, DetailPageActivity::class.java)
                intentToDetail.putExtra(DetailPageActivity.DATA, data)
                startActivity(intentToDetail)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}