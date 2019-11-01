package food.com.groupproject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import food.com.groupproject.R;
import food.com.groupproject.RecyclerViewListener;
import food.com.groupproject.RoomDatabase.Item;

public class recycleradapter extends RecyclerView.Adapter<recycleradapter.viewholder> {

 private    ArrayList<Item> mitem;
  private   Context context;
 private RecyclerViewListener.ClickListener clickListener;
  public  recycleradapter(Context context,ArrayList<Item> mitem,RecyclerViewListener.ClickListener clickListener){
      this.context=context;
      this.mitem=mitem;
this.clickListener=clickListener;

  }
    @NonNull
    @Override
    public recycleradapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.lecturetreathrelist,parent,false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recycleradapter.viewholder holder, int position) {

      Item item=mitem.get(position);


holder.loc_name.setText(item.getLocation_name());
        Glide.with(context).load(item.getImage_url()).error(R.drawable.oaulogo).into(holder.loc_image);
holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mitem.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
CircleImageView loc_image;
TextView loc_name;
        public viewholder(@NonNull View itemView) {
            super(itemView);

       loc_image=itemView.findViewById(R.id.location_img);
       loc_name=itemView.findViewById(R.id.location_name);

        new ViewHolder(itemView);
        }
    }

public  class  ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
   itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        int position=(Integer) view.getTag();
        clickListener.onClick(view,position);
    }
}



}
