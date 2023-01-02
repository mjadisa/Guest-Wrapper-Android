package net.iris.android.apps.guestwrapper

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.iris.android.apps.guestwrapper.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding?.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.webview?.settings?.javaScriptEnabled = true
        binding?.webview?.settings?.domStorageEnabled = true
        binding?.webview?.loadUrl("${BuildConfig.URL}?interfaceToken=${BuildConfig.APP_INTERFACE_TOKEN}&apiManagementKey=${BuildConfig.API_MANAGEMENT_KEY}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}