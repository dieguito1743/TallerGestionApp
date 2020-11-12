package com.buildingdreamcars.tallergestionapp.Utiles;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.buildingdreamcars.tallergestionapp.Activities.EstadoActivity;
import com.buildingdreamcars.tallergestionapp.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static com.buildingdreamcars.tallergestionapp.Activities.EstadoActivity.mPath;
import static com.buildingdreamcars.tallergestionapp.Activities.EstadoActivity.paint_brush;


public class display extends View {

    public static ArrayList<Path>pathList = new ArrayList<>();
    private ViewGroup.LayoutParams params;




    public display(Context context) {
        super(context);
        init(context);
    }

    public display(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context){
        paint_brush.setAntiAlias(true);
        paint_brush.setColor(Color.BLACK);
        paint_brush.setStyle(Paint.Style.STROKE);
        paint_brush.setStrokeCap(Paint.Cap.ROUND);
        paint_brush.setStrokeJoin(Paint.Join.ROUND);
        paint_brush.setStrokeWidth(4f);

        params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


      }
    @Override
    protected void onDraw(Canvas canvas) {
        for (int i=0;i<pathList.size();i++){
            canvas.drawPath(pathList.get(i),paint_brush);
            invalidate();
        }
    }





    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x,y);
                invalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x,y);
                pathList.add(mPath);
                invalidate();
                return true;
            default:
                return false;
        }
    }




}
