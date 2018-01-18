package com.nkdroid.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Albrtx on 23/12/2017.
 */

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {

    private List<String> horizontalList;
    private Context context;
    private boolean CAM_STATUS = false;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtView;
        public LinearLayout camView;

        public MyViewHolder(View view) {
            super(view);
            txtView = (TextView) view.findViewById(R.id.txtView);
            camView = (LinearLayout) view.findViewById(R.id.cam_view_frame);
        }
    }


    public HorizontalAdapter(List<String> horizontalList, Context context) {
        this.horizontalList = horizontalList;
        this.context = context;
    }

    @Override
    public HorizontalAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.horizontal_item_view, parent, false);

        return new HorizontalAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final HorizontalAdapter.MyViewHolder holder, final int position) {
        final Animation scaleFromCenter = AnimationUtils.loadAnimation(context, R.anim.scale_anim);
        final Animation hideFromScreen = AnimationUtils.loadAnimation(context, R.anim.hide_anim);
        final Animation leftToRight = AnimationUtils.loadAnimation(context, R.anim.left_to_right);
        final Animation rightToLeft = AnimationUtils.loadAnimation(context, R.anim.right_to_left);

        holder.txtView.setText(horizontalList.get(position));
        holder.txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,", Position: "+holder.getAdapterPosition(),Toast.LENGTH_SHORT).show();
                if (!CAM_STATUS){
                    if(holder.camView.getVisibility()==View.GONE){
                        CAM_STATUS = true;
                        holder.camView.startAnimation(leftToRight);
                        holder.camView.setVisibility(View.VISIBLE);
                    }
                } else {
                    if(holder.camView.getVisibility()==View.VISIBLE){
                        CAM_STATUS = false;
                        holder.camView.startAnimation(rightToLeft);
                        holder.camView.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return horizontalList.size();
    }
}
