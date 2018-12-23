package com.truekenyan.drawingpad.activities

import android.content.DialogInterface
import android.graphics.Bitmap
import android.os.Environment
import android.provider.MediaStore
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

import com.truekenyan.drawingpad.R
import com.truekenyan.drawingpad.fragments.ColorFragment
import com.truekenyan.drawingpad.fragments.SizeFragment
import com.truekenyan.drawingpad.interfaces.OnValueChanged
import com.truekenyan.drawingpad.utilities.Drawing

import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), OnValueChanged {

    private var drawingPad: Drawing? = null
    private var backPressed  = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawingPad = findViewById(R.id.canvas)
        drawingPad!!.isDrawingCacheEnabled = true
    }

    fun clearCanvas(view: View) {

        val dialogClickListener = DialogInterface.OnClickListener { dialog, which ->
            if (which == DialogInterface.BUTTON_POSITIVE) {
                drawingPad!!.clearAll()
            } else if (which == DialogInterface.BUTTON_NEGATIVE) {
                dialog!!.dismiss()
            }
        }

        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Clear")
                .setMessage("Are you sure you want to clear?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener)
        val n = builder.create()
        n.show()
    }

    override fun onColorChanged(selectedColor: Int) {
        drawingPad!!.setBrushColor(selectedColor)
    }

    override fun onSizeSelected(selectedSize: Float) {
        drawingPad!!.setBrushSize(selectedSize)
    }

    fun changeSizeDialog(view: View) {
        val sizeFragment = SizeFragment()
        sizeFragment.show(supportFragmentManager, sizeFragment.tag)
    }

    fun changeColorDialog(view: View) {
        val colorFragment = ColorFragment()
        colorFragment.show(supportFragmentManager, colorFragment.tag)
    }

    @Throws(IOException::class)
    fun saveCanvas(view: View) {
        var path = Environment.getExternalStorageDirectory().toString()
        path = "$path/Drawings"
        val dir = File(path)
        drawingPad!!.isDrawingCacheEnabled = true

        val name = "Drawing_" + System.currentTimeMillis() + ".png"
        val savedImage = MediaStore.Images.Media.insertImage(contentResolver, drawingPad!!.drawingCache, name, "A drawing")

        try {

            if (!dir.isDirectory || !dir.exists()) {
                dir.mkdirs()
            }

            drawingPad!!.isDrawingCacheEnabled = true
            val file = File(dir, name)
            val fileOutputStream = FileOutputStream(file)
            val bitmap = drawingPad!!.drawingCache
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)

        } catch (e: FileNotFoundException) {

            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Error")
                    .setMessage("Ooops! Could not save.")
                    .setNegativeButton("CANCEL") { dialogInterface, _ -> dialogInterface.dismiss() }
            val n = builder.create()
            n.show()
        }

        if (savedImage != null) {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Saved")
                    .setMessage("Saved Successfully. Dou you want to clear the canvas?")
                    .setPositiveButton("Yes") { _, _ -> drawingPad!!.clearAll() }
                    .setNegativeButton("No") { dialogInterface, _ -> dialogInterface.dismiss() }
            val n = builder.create()
            n.show()
        }

        drawingPad!!.destroyDrawingCache()
    }

    override fun onBackPressed() {
        val timeUnit = 2000
        if (timeUnit + backPressed > System.currentTimeMillis()) {
            finishAffinity()
            System.exit(0)
        } else {
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show()
        }
        backPressed = System.currentTimeMillis()
    }
}
