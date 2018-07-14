package com.truekenyan.drawingpad.activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.truekenyan.drawingpad.R;
import com.truekenyan.drawingpad.fragments.ColorFragment;
import com.truekenyan.drawingpad.fragments.SizeFragment;
import com.truekenyan.drawingpad.interfaces.OnValueChanged;
import com.truekenyan.drawingpad.utilities.Drawing;

public class MainActivity extends AppCompatActivity implements OnValueChanged {

    private Drawing drawingPad;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawingPad = findViewById(R.id.canvas);
    }

    public void clearCanvas(View view){

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick (DialogInterface dialogInterface, int i) {
                if (i == DialogInterface.BUTTON_POSITIVE){
                    drawingPad.clearAll();
                } else if (i == DialogInterface.BUTTON_NEGATIVE){
                    dialogInterface.dismiss();
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Clear")
                .setMessage("Are you sure you want to clear?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener);
        AlertDialog n = builder.create();
        n.show();
    }

    @Override
    public void onColorChanged (int selectedColor) {
        drawingPad.setBrushColor(selectedColor);
    }

    @Override
    public void onSizeSelected (float selectedSize) {
        drawingPad.setBrushSize(selectedSize);
    }

    public void changeSizeDialog (View view) {
        SizeFragment sizeFragment = new SizeFragment();
        sizeFragment.show(getSupportFragmentManager(), sizeFragment.getTag());
    }

    public void changeColorDialog (View view) {
        ColorFragment colorFragment = new ColorFragment();
        colorFragment.show(getSupportFragmentManager(), colorFragment.getTag());
    }
}
