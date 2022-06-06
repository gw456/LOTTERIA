package com.example.lotteria.ui.setting

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.lotteria.R
import com.example.lotteria.databinding.FragmentListImagesPageBinding
import com.example.lotteria.databinding.FragmentSettingPageBinding
import com.example.lotteria.ui.feedback.FeedbackActivity
import com.example.lotteria.ui.login.LoginActivity

class SettingPageFragment : Fragment() {

    private var _binding: FragmentSettingPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSettingPageBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            gambarAkun.setImageResource(R.drawable.foto)
            namaAkun.text = getString(R.string.username)
            cloudStorageValue.text = getString(R.string.cloud_storage_value)
            account.setOnClickListener {
                val intentLogin = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intentLogin)
            }
            sync.setOnClickListener {
                Toast.makeText(requireActivity(), R.string.not_working_feature, Toast.LENGTH_SHORT).show()
            }
            recommend.setOnClickListener {
                val intentRecommend = Intent(requireActivity(), FeedbackActivity::class.java)
                startActivity(intentRecommend)
            }
            logout.setOnClickListener {
                Toast.makeText(requireActivity(), R.string.not_working_feature, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}