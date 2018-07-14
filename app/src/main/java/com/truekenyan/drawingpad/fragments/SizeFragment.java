package com.truekenyan.drawingpad.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

import com.truekenyan.drawingpad.R;
import com.truekenyan.drawingpad.interfaces.OnValueChanged;

/**
 * Created by password
 * on 7/12/18.
 */

public class SizeFragment extends DialogFragment {

    private OnValueChanged onValueChanged;
    private SeekBar seekBar;

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_size, container, false);
        seekBar = rootView.findViewById(R.id.change_size);
        Button cancelButton = rootView.findViewById(R.id.cancel_button);
        Button okButton = rootView.findViewById(R.id.ok_button);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                int value = seekBar.getProgress();
                onValueChanged.onSizeSelected((float) value);
                getDialog().dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                getDialog().dismiss();
            }
        });
        return rootView;
    }

    @Override
    public void onAttach (Context context) {
        super.onAttach(context);
        onValueChanged = (OnValueChanged) context;
    }
}
