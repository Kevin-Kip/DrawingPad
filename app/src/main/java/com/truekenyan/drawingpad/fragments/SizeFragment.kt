package com.truekenyan.drawingpad.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar

import com.truekenyan.drawingpad.R
import com.truekenyan.drawingpad.interfaces.OnValueChanged
import com.truekenyan.drawingpad.utilities.Commons

/**
 * Created by password
 * on 7/12/18.
 */

class SizeFragment : DialogFragment() {

    private var changeSize: SeekBar? = null
    private var onValueChanged: OnValueChanged? = null
    private var cancelButton: Button? = null
    private var okButton: Button? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_size, container, false)
        changeSize = rootView.findViewById(R.id.change_size)
        cancelButton = rootView.findViewById(R.id.cancel_button)
        okButton = rootView.findViewById(R.id.ok_button)

        cancelButton!!.setOnClickListener(onViewClicked(Commons.CANCEL))
        okButton!!.setOnClickListener(onViewClicked(Commons.OK))
        dialog.setTitle("Change Brush Size")
        return rootView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        onValueChanged = context as OnValueChanged?
    }

    fun onViewClicked(action: String): View.OnClickListener {
        return View.OnClickListener {
            when (action) {
                Commons.CANCEL -> dialog.dismiss()
                Commons.OK -> {
                    val value = changeSize!!.progress
                    onValueChanged!!.onSizeSelected(value.toFloat())
                    dialog.dismiss()
                }
            }
        }
    }
}
