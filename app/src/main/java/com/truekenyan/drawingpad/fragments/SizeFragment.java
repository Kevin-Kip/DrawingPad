package com.truekenyan.drawingpad.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.truekenyan.drawingpad.R;
import com.truekenyan.drawingpad.interfaces.OnValueChanged;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by password
 * on 7/12/18.
 */

public class SizeFragment extends DialogFragment {

    @BindView (R.id.change_size)
    SeekBar changeSize;
    Unbinder unbinder;
    private OnValueChanged onValueChanged;

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_size, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        getDialog().setTitle("Change Brush Size");
        return rootView;
    }

    @Override
    public void onAttach (Context context) {
        super.onAttach(context);
        onValueChanged = (OnValueChanged) context;
    }

    @Override
    public void onDestroyView () {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick ({R.id.cancel_button, R.id.ok_button})
    public void onViewClicked (View view) {
        switch (view.getId()) {
            case R.id.cancel_button:
                getDialog().dismiss();
                break;
            case R.id.ok_button:
                int value = changeSize.getProgress();
                onValueChanged.onSizeSelected((float) value);
                getDialog().dismiss();
                break;
        }
    }
}
