package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice12CameraRotateFixedView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);
    Camera camera = new Camera();
    Matrix matrix = new Matrix();

    public Practice12CameraRotateFixedView(Context context) {
        super(context);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * camera 坐标系：向上为Y轴正向
         *              向右为X轴正向
         *              向屏幕外为Z轴付负向
         */

        //要反着写

        canvas.save();

        canvas.translate(point1.x+bitmap.getWidth()/2,point1.y+bitmap.getHeight()/2);//3.投影完成之后将画布移回到原来的位置

        camera.save();
        camera.rotateX(30);//2.沿x轴旋转，并且投影
        camera.applyToCanvas(canvas);
        camera.restore();

        canvas.translate(-(point1.x+bitmap.getWidth()/2),-(point1.y+bitmap.getHeight()/2));//1.移动画布的中心到camera的Z轴中心（投影原点）

        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(point2.x+bitmap.getWidth()/2,point2.y+bitmap.getHeight()/2);//3.投影完成之后将画布移回到原来的位置
        camera.save();
        camera.rotateY(30);//2.沿y轴旋转，并且投影
        camera.applyToCanvas(canvas);
        camera.restore();

        canvas.translate(-(point2.x+bitmap.getWidth()/2),-(point2.y+bitmap.getHeight()/2));//1.移动画布的中心到camera的Z轴中心（投影原点）


        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();
    }
}
