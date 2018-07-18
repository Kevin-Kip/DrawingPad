package com.truekenyan.drawingpad.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.truekenyan.drawingpad.R;
import com.truekenyan.drawingpad.interfaces.OnValueChanged;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by password
 * on 7/12/18.
 */

public class ColorFragment extends DialogFragment {

    Unbinder unbinder;
    private OnValueChanged valueChanged;

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_color, container, false);
        unbinder = ButterKnife.bind(this, view);
        getDialog().setTitle("Pick a Color");
        return view;
    }

    @Override
    public void onDestroyView () {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick ({R.id.button_black, R.id.button_blue, R.id.button_green, R.id.button_grey, R.id.button_orange, R.id.button_pink, R.id.button_red, R.id.button_white})
    public void onViewClicked (View view) {
        int selectedColor;
        switch (view.getId()) {
            case R.id.button_black:
                selectedColor = 0xFF000000;
                valueChanged.onColorChanged(selectedColor);
                dismiss();
                break;
            case R.id.button_blue:
                selectedColor = 0xFF0d47a1;
                valueChanged.onColorChanged(selectedColor);
                dismiss();
                break;
            case R.id.button_green:
                selectedColor = 0xFF004d40;
                valueChanged.onColorChanged(selectedColor);
                dismiss();
                break;
            case R.id.button_grey:
                selectedColor = 0xFF9e9e9e;
                valueChanged.onColorChanged(selectedColor);
                dismiss();
                break;
            case R.id.button_orange:
                selectedColor = 0xFFf57f17;
                valueChanged.onColorChanged(selectedColor);
                dismiss();
                break;
            case R.id.button_pink:
                selectedColor = 0xFFdc0f42;
                valueChanged.onColorChanged(selectedColor);
                dismiss();
                break;
            case R.id.button_red:
                selectedColor = 0xFFb71c1c;
                valueChanged.onColorChanged(selectedColor);
                dismiss();
                break;
            case R.id.button_white:
                selectedColor = 0xFFFFFFFF;
                valueChanged.onColorChanged(selectedColor);
                dismiss();
                break;
        }
    }

    @Override
    public void onAttach (Context context) {
        super.onAttach(context);
        valueChanged = (OnValueChanged) context;
    }
}
