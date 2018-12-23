package com.truekenyan.drawingpad.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar

import com.truekenyan.drawingpad.R
import com.truekenyan.drawingpad.interfaces.OnValueChanged

/**
 * Created by password
 * on 7/12/18.
 */

class SizeFragment : DialogFragment() {

    internal var changeSize: SeekBar? = null
    private var onValueChanged: OnValueChanged? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_size, container, false)
        unbinder = ButterKnife.bind(this, rootView)
        dialog.setTitle("Change Brush Size")
        return rootView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        onValueChanged = context as OnValueChanged?
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder.unbind()
    }

    @OnClick(R.id.cancel_button, R.id.ok_button)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.cancel_button -> dialog.dismiss()
            R.id.ok_button -> {
                val value = changeSize!!.progress
                onValueChanged!!.onSizeSelected(value.toFloat())
                dialog.dismiss()
            }
        }
    }
}
