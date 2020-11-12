package com.buildingdreamcars.tallergestionapp.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import java.util.ArrayList;
import java.util.List;

import static com.buildingdreamcars.tallergestionapp.Utiles.display.pathList;


public class EstadoActivity extends AppCompatActivity {

    public static Path mPath = new Path();
    public static Paint paint_brush = new Paint();
    private Bitmap mBitmap;

    Button btnsave, btndelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        verificarStoragePermisos(this);
        setContentView(R.layout.activity_estado);
        DisplayMetrics medidas = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidas);
        int ancho = medidas.widthPixels;
        int alto = medidas.heightPixels;
        getWindow().setLayout(ancho = 1000, alto = 1750);

        paint_brush.setColor(Color.BLACK);
        mBitmap = Bitmap.createBitmap(ancho, alto, Bitmap.Config.ARGB_8888);


        btnsave = findViewById(R.id.btn_guardar_estado);
        btndelete = findViewById(R.id.btn_borrar_estado);
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

    public void asksave() {
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

    public void delete() {
        pathList.clear();
        mPath.reset();
        Toast.makeText(this, "Borrado", Toast.LENGTH_SHORT).show();
    }

    public void save() {
        File sdDirectory = Environment.getExternalStorageDirectory(); // Environment.getStorageDirectory();
        // sorry my divice soporting SDK 29
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // do something
            }
            return;
        }
    }

    // Storage Permisos
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Comprueba si la aplicación tiene permiso para escribir en el device storage
     *
     * Si la aplicación no tiene permiso, se le pedirá al usuario que otorgue permisos
     *
     * @param activity
     */
    public static void verificarStoragePermisos(Activity activity) {
        // Comprueba si tenemos permiso de escritura
        int iPermisos = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (iPermisos != PackageManager.PERMISSION_GRANTED) {
            // No tenemos permiso hay que solicitárselas al usuario
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

}