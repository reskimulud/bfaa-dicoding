package com.mankart.myflexiblefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment

class OptionDialogFragment : DialogFragment() {

    private val optionDialogListener: OnOptionDialogListener? = null
    private lateinit var btnChoose: Button
    private lateinit var btnClose: Button
    private lateinit var rgOption: RadioGroup
    private lateinit var rbSaf: RadioButton
    private lateinit var rbMou: RadioButton
    private lateinit var rbLvg: RadioButton
    private lateinit var rbMoyes: RadioButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnChoose = view.findViewById(R.id.btn_choose)
        btnClose = view.findViewById(R.id.btn_close)
        rgOption = view.findViewById(R.id.rg_option)
        rbSaf = view.findViewById(R.id.rb_saf)
        rbMou = view.findViewById(R.id.rb_mou)
        rbLvg = view.findViewById(R.id.rb_lvg)
        rbMoyes = view.findViewById(R.id.rb_moyes)

        btnChoose.setOnClickListener {
            val checkedRadioButtonId = rgOption.checkedRadioButtonId
            if (checkedRadioButtonId != -1) {
                var coach: String? = null
                when (checkedRadioButtonId) {
                    R.id.rb_saf -> coach = rbSaf.toString().trim()

                    R.id.rb_mou -> coach = rbMou.toString().trim()

                    R.id.rb_lvg -> coach = rbLvg.toString().trim()

                    R.id.rb_moyes -> coach = rbMoyes.toString().trim()
                }
                optionDialogListener?.onOptionChosen(coach)
                dialog?.dismiss()
            }
        }

        btnClose.setOnClickListener {
            dialog?.dismiss()
        }
    }

    interface OnOptionDialogListener {
        fun onOptionChosen(text: String?)
    }
}