package food.com.groupproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerViewListener implements RecyclerView.OnItemTouchListener {

    private GestureDetector gestureDetector;
    private RecyclerViewListener.ClickListener clickListener;
 RecyclerView recyclerView;
    public  RecyclerViewListener(Context context, final RecyclerView recyclerView, final RecyclerViewListener.ClickListener clickListener){
        this.clickListener=clickListener;
        this.recyclerView=recyclerView;
        gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return super.onSingleTapUp(e);
            }

            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);

            }
        });
        }



    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

        View Child=recyclerView.findChildViewUnder(e.getX(),e.getY());

        if (Child!=null && clickListener!=null && gestureDetector.onTouchEvent(e)){
            clickListener.onClick(Child,recyclerView.getChildAdapterPosition(Child));
        }
    return  false;
    }






    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        View Child=recyclerView.findChildViewUnder(e.getX(),e.getY());

        if (Child!=null && clickListener!=null){
            clickListener.onClick(Child,recyclerView.getChildAdapterPosition(Child));
        }
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public interface  ClickListener{

        void onClick(View view, int position);
    }
}
