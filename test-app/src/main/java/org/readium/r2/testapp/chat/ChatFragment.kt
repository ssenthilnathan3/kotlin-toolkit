package org.readium.r2.testapp.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.readium.r2.testapp.MainActivity
import org.readium.r2.testapp.databinding.FragmentChatBinding

class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        // Hide the Floating Action Button
        (activity as MainActivity).hideFab() // Call method from MainActivity to hide FAB
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
