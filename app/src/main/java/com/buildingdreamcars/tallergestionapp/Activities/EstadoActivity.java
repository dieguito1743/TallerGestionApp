package com.buildingdreamcars.tallergestionapp.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.buildingdreamcars.tallergestionapp.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.buildingdreamcars.tallergestionapp.Utiles.display.pathList;


public class EstadoActivity extends AppCompatActivity {

    public static Path mPath = new Path();
    public static Paint paint_brush = new Paint();
    private Bitmap mBitmap;
    private Canvas mCanvas;

    private static ImageView imageView;

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

        paint_brush.setColor(Color.RED);

        // **pasos
        // 1 cargar la imagen en el bitmap
        // 2 inicializar el canvas con el bitmap pre cargado que tiene la iamgen de fondo.
        // 3 guardar lo "dibujado" en en el bitmap y copiarlo en el bitmap

        // uso un ImageView para obtener el bitmap, tu puedes hacerlo directamente, solo que no recuerdo como.
        // esta para que lo googlees.
        if(imageView == null) {
            // no quiero estar instanciado multiples ImageView, por eso hago esto.
            imageView = new ImageView(this);
        }
        // le pongo oculta, no quiero que por alguna razón se valla a ver.
        imageView.setVisibility(View.INVISIBLE);
        imageView.setImageResource(R.drawable.esquema_coche);
        // este códigodel image view no aporta nada, como tedigo puedes cargar la imagen el bitmap de otra manera, como leyendo los bytes.

        // acá inicializabas tu bitmap, pero no tenía nada, era un fondo en "negro"
        // mBitmap = Bitmap.createBitmap(ancho, alto, Bitmap.Config.RGB_565);
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        mBitmap = drawable.getBitmap().copy(Bitmap.Config.RGB_565, true);
        mBitmap = Bitmap.createScaledBitmap(mBitmap, ancho, alto, false); // AJUSTA LA DIMENSIÓN DEL DRAW y LA IMG de FONDO.!!
        mCanvas = new Canvas(mBitmap);


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

            // copio lo que dibuje en el canvas, que tiene el canvas?  tiene el fondo de los carros, recuerda que hicimos esto en
            // en el constructor
            mCanvas.drawBitmap(mBitmap, 0, 0, paint_brush);
            mCanvas.drawPath(mPath, paint_brush);

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