package com.truekenyan.drawingpad.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import com.truekenyan.drawingpad.R
import com.truekenyan.drawingpad.interfaces.OnValueChanged

import com.truekenyan.drawingpad.utilities.Commons

/**
 * Created by password
 * on 7/12/18.
 */

class ColorFragment : DialogFragment() {

    private var valueChanged: OnValueChanged? = null
    private var buttonBlack: Button? = null
    private var buttonBlue: Button? = null
    private var buttonGreen: Button? = null
    private var buttonGrey: Button? = null
    private var buttonOrange: Button? = null
    private var buttonPink: Button? = null
    private var buttonRed: Button? = null
    private var buttonWhite: Button? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_color, container, false)
        buttonBlack = view.findViewById(R.id.button_black)
        buttonBlue = view.findViewById(R.id.button_blue)
        buttonGreen = view.findViewById(R.id.button_green)
        buttonGrey = view.findViewById(R.id.button_grey)
        buttonOrange = view.findViewById(R.id.button_orange)
        buttonPink = view.findViewById(R.id.button_pink)
        buttonRed = view.findViewById(R.id.button_red)
        buttonWhite = view.findViewById(R.id.button_white)

        buttonBlack!!.setOnClickListener(onViewClicked(Commons.BLACK))
        buttonBlue!!.setOnClickListener(onViewClicked(Commons.BLUE))
        buttonGreen!!.setOnClickListener(onViewClicked(Commons.GREEN))
        buttonGrey!!.setOnClickListener(onViewClicked(Commons.GREY))
        buttonOrange!!.setOnClickListener(onViewClicked(Commons.ORANGE))
        buttonPink!!.setOnClickListener(onViewClicked(Commons.PINK))
        buttonRed!!.setOnClickListener(onViewClicked(Commons.RED))
        buttonWhite!!.setOnClickListener(onViewClicked(Commons.WHITE))

        dialog.setTitle("Pick a Color")
        return view
    }

    private fun onViewClicked(action: String): View.OnClickListener {
        var selectedColor: Int
        return View.OnClickListener {
            when (action) {
                Commons.BLACK -> {
                    selectedColor = -0x1000000
                    valueChanged!!.onColorChanged(selectedColor)
                    dismiss()
                }
                Commons.BLUE -> {
                    selectedColor = -0xf2b85f
                    valueChanged!!.onColorChanged(selectedColor)
                    dismiss()
                }
                Commons.GREEN -> {
                    selectedColor = -0xffb2c0
                    valueChanged!!.onColorChanged(selectedColor)
                    dismiss()
                }
                Commons.GREY -> {
                    selectedColor = -0x616162
                    valueChanged!!.onColorChanged(selectedColor)
                    dismiss()
                }
                Commons.ORANGE -> {
                    selectedColor = -0xa80e9
                    valueChanged!!.onColorChanged(selectedColor)
                    dismiss()
                }
                Commons.PINK -> {
                    selectedColor = -0x23f0be
                    valueChanged!!.onColorChanged(selectedColor)
                    dismiss()
                }
                Commons.RED -> {
                    selectedColor = -0x48e3e4
                    valueChanged!!.onColorChanged(selectedColor)
                    dismiss()
                }
                Commons.WHITE -> {
                    selectedColor = -0x1
                    valueChanged!!.onColorChanged(selectedColor)
                    dismiss()
                }
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        valueChanged = context as OnValueChanged?
    }
}
