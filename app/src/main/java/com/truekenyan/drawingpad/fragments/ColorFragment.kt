package com.truekenyan.drawingpad.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.truekenyan.drawingpad.R
import com.truekenyan.drawingpad.interfaces.OnValueChanged

import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder

/**
 * Created by password
 * on 7/12/18.
 */

class ColorFragment : DialogFragment() {

    private var valueChanged: OnValueChanged? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_color, container, false)
        dialog.setTitle("Pick a Color")
        return view
    }

    @OnClick(R.id.button_black, R.id.button_blue, R.id.button_green, R.id.button_grey, R.id.button_orange, R.id.button_pink, R.id.button_red, R.id.button_white)
    fun onViewClicked(view: View) {
        val selectedColor: Int
        when (view.id) {
            R.id.button_black -> {
                selectedColor = -0x1000000
                valueChanged!!.onColorChanged(selectedColor)
                dismiss()
            }
            R.id.button_blue -> {
                selectedColor = -0xf2b85f
                valueChanged!!.onColorChanged(selectedColor)
                dismiss()
            }
            R.id.button_green -> {
                selectedColor = -0xffb2c0
                valueChanged!!.onColorChanged(selectedColor)
                dismiss()
            }
            R.id.button_grey -> {
                selectedColor = -0x616162
                valueChanged!!.onColorChanged(selectedColor)
                dismiss()
            }
            R.id.button_orange -> {
                selectedColor = -0xa80e9
                valueChanged!!.onColorChanged(selectedColor)
                dismiss()
            }
            R.id.button_pink -> {
                selectedColor = -0x23f0be
                valueChanged!!.onColorChanged(selectedColor)
                dismiss()
            }
            R.id.button_red -> {
                selectedColor = -0x48e3e4
                valueChanged!!.onColorChanged(selectedColor)
                dismiss()
            }
            R.id.button_white -> {
                selectedColor = -0x1
                valueChanged!!.onColorChanged(selectedColor)
                dismiss()
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        valueChanged = context as OnValueChanged?
    }
}
