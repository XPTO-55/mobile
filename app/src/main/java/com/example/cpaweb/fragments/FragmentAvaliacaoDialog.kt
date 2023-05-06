package com.example.cpaweb.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import com.example.cpaweb.R
import com.example.cpaweb.databinding.ResFragAvaliacaoDialogBinding
import com.example.cpaweb.models.AvaliacaoFeedback

class FragmentAvaliacaoDialog(
    private val enviarFeedback: (AvaliacaoFeedback) -> Unit
) : DialogFragment(R.layout.res_frag_avaliacao_dialog) {

    private lateinit var binding: ResFragAvaliacaoDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ResFragAvaliacaoDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lp = FrameLayout.LayoutParams(
            MarginLayoutParams.MATCH_PARENT,
            MarginLayoutParams.MATCH_PARENT
        )
        binding.dialogContainer.layoutParams = lp

    }

}