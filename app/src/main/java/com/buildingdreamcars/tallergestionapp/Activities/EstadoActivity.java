package com.buildingdreamcars.tallergestionapp.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.buildingdreamcars.tallergestionapp.R;
import com.buildingdreamcars.tallergestionapp.Utiles.display;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.buildingdreamcars.tallergestionapp.Utiles.display.pathList;


public class EstadoActivity extends AppCompatActivity {

    public static Path mPath = new Path();
    public static Paint paint_brush = new Paint();
    private Bitmap mBitmap;


    Button btnsave,btndelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado);
        DisplayMetrics medidas = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidas);
        int ancho = medidas.widthPixels;
        int alto = medidas.heightPixels;
        getWindow().setLayout(ancho=1000,alto=1750);

        paint_brush.setColor(Color.BLACK);
        mBitmap=Bitmap.createBitmap(ancho,alto, Bitmap.Config.ARGB_8888);



        btnsave=findViewById(R.id.btn_guardar_estado);
        btndelete=findViewById(R.id.btn_borrar_estado);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asksave();
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });
    }
    public void asksave(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atención");
        builder.setMessage("¿Deseas guardar el estado actual del vehiculo?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                save();


            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }
    public void delete(){
        pathList.clear();
        mPath.reset();
        Toast.makeText(this, "Borrado", Toast.LENGTH_SHORT).show();
    }
    public void save(){
        File sdDirectory = Environment.getStorageDirectory();
        File image = new File(sdDirectory, "/test.jpg");

        try {
            FileOutputStream fileOutputStream;
            fileOutputStream = new FileOutputStream(image);

            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            Log.e("TAG", "Entro en el try");
            finish();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("TAG", "Entro en el 1 catch");

        } catch (IOException e) {
            Log.e("TAG", "Entro en el 2 catch");

        }
        }

}