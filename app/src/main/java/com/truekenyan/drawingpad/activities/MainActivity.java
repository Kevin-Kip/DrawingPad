package com.truekenyan.drawingpad.activities;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.truekenyan.drawingpad.R;
import com.truekenyan.drawingpad.fragments.ColorFragment;
import com.truekenyan.drawingpad.fragments.SizeFragment;
import com.truekenyan.drawingpad.interfaces.OnValueChanged;
import com.truekenyan.drawingpad.utilities.Drawing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements OnValueChanged {

    private Drawing drawingPad;
    private long back_pressed;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawingPad = findViewById(R.id.canvas);
        drawingPad.setDrawingCacheEnabled(true);
    }

    public void clearCanvas(View view){

        DialogInterface.OnClickListener dialogClickListener = (dialogInterface, i) -> {
            if (i == DialogInterface.BUTTON_POSITIVE){
                drawingPad.clearAll();
            } else if (i == DialogInterface.BUTTON_NEGATIVE){
                dialogInterface.dismiss();
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

    public void saveCanvas (View view) throws IOException {
        String path = Environment.getExternalStorageDirectory().toString();
        path = path+"/Drawings";
        File dir = new File(path);
        drawingPad.setDrawingCacheEnabled(true);

        String name = "Drawing_"+System.currentTimeMillis()+".png";
        String savedImage = MediaStore.Images.Media.insertImage(getContentResolver(), drawingPad.getDrawingCache(), name, "A drawing");

        try {

            if (!dir.isDirectory() || !dir.exists()){
                dir.mkdirs();
            }

            drawingPad.setDrawingCacheEnabled(true);
            File file = new File(dir, name);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            Bitmap bitmap = drawingPad.getDrawingCache();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);

        } catch (FileNotFoundException e){

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Error")
                    .setMessage("Ooops! Could not save.")
                    .setNegativeButton("CANCEL", (dialogInterface, i) -> dialogInterface.dismiss());
            AlertDialog n = builder.create();
            n.show();
        }

        if (savedImage != null){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Saved")
                    .setMessage("Saved Successfully. Dou you want to clear the canvas?")
                    .setPositiveButton("Yes", (dialogInterface, i) -> drawingPad.clearAll())
                    .setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss());
            AlertDialog n = builder.create();
            n.show();
        }

        drawingPad.destroyDrawingCache();
    }

    @Override
    public void onBackPressed () {
        int TIME_UNIT = 2000;
        if (TIME_UNIT + back_pressed > System.currentTimeMillis()){
            finishAffinity();
            System.exit(0);
        } else {
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }
}
