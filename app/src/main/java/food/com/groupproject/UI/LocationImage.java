package food.com.groupproject.UI;


import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.FloatMath;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import food.com.groupproject.Detailkeeper;
import food.com.groupproject.R;

public class LocationImage extends Fragment implements View.OnTouchListener {
    ImageView loc_img;
    TextView  loc_name;
    Matrix matrix=new Matrix();
    Matrix savedmatrix=new Matrix();
    PointF  start=new PointF();
    public  static  PointF mid=new PointF();

    public static final int NONE=0;
    public static final  int DRAG=1;
    public  static final  int ZOOM=2;
    public  static  int mode=NONE;

    float oldDist;
    public static  Fragment getInstance(){

        LocationImage locationImage=new LocationImage();
        return locationImage;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.imagelocation,container,false);
      loc_img=view.findViewById(R.id.location_image);
      loc_name=view.findViewById(R.id.location_name);
      loc_name.setText(Detailkeeper.Detail.getInstance().getLocationName());
        Glide.with(getContext()).load(Detailkeeper.Detail.getInstance().getImagelocation()).error(R.drawable.oaulogo).into(loc_img);
        view.setOnTouchListener(this);
        return view;

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
       switch (motionEvent.getAction() & MotionEvent.ACTION_MASK){

           case MotionEvent.ACTION_DOWN:
               savedmatrix.set(matrix);
               start.set(motionEvent.getX(),motionEvent.getY());
               mode=DRAG;

               break;
           case  MotionEvent.ACTION_POINTER_DOWN:
                oldDist=spacing(motionEvent);
                if (oldDist>10f){

                    savedmatrix.set(matrix);
                    midpoint(mid,motionEvent);
                    mode=ZOOM;
                }
                break;
                case MotionEvent.ACTION_MOVE:
                    if (mode==DRAG){

                        matrix.set(savedmatrix);
                        matrix.postTranslate(motionEvent.getX()-start.x,motionEvent.getY()-start.y);

                    }else if (mode==ZOOM){

                        float newDist=spacing(motionEvent);
                        if (newDist > 10f){

                            matrix.set(savedmatrix);
                            float scale=newDist/oldDist;
                            matrix.postScale(scale,scale,mid.x,mid.y);

                        }

                    }
                    break;
           case  MotionEvent.ACTION_UP:
           case MotionEvent.ACTION_POINTER_UP:
               mode=NONE;
               break;
       }
        loc_img.setImageMatrix(matrix);
        return true;
    }

    private  float spacing(MotionEvent event){

        float x=event.getX(0)-event.getX(1);
        float y= event.getY(0) - event.getY(0);
        return (float) Math.sqrt(x*x +y*y);
    }

    private  void  midpoint(PointF point, MotionEvent event){
        float x=event.getX(0) +event.getX(1);
        float y= event.getY(0)+ event.getY(1);
        point.set(x/2,y/2);
    }
}
