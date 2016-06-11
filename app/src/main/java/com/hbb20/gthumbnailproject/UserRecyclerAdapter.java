package com.hbb20.gthumbnailproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hbb20.GThumb;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hbb20 on 30/4/16.
 */
public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.ContactViewHolder> {

    List<CaptainPlayer> captainPlayerList;
    Context context;
    private boolean useBold = false;
    private GThumb.BACKGROUND_SHAPE backgroundOption = GThumb.BACKGROUND_SHAPE.ROUND;
    private boolean needToSetClickListener = true;
    private int customMonoColor;
    private boolean useMonoColor;

    public UserRecyclerAdapter(Context context, List<CaptainPlayer> captainPlayerList) {
        this.context = context;
        this.captainPlayerList = captainPlayerList;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_list_tile, parent, false));
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        holder.bindUser(captainPlayerList.get(position));
    }

    @Override
    public int getItemCount() {
        return captainPlayerList.size();
    }

    public void setUseBold(boolean useBold) {
        this.useBold = useBold;
    }

    public GThumb.BACKGROUND_SHAPE getBackgroundOption() {
        return backgroundOption;
    }

    public void setBackgroundOption(GThumb.BACKGROUND_SHAPE backgroundOption) {
        this.backgroundOption = backgroundOption;
    }

    public boolean isNeedToSetClickListener() {
        return needToSetClickListener;
    }

    public void setNeedToSetClickListener(boolean needToSetClickListener) {
        this.needToSetClickListener = needToSetClickListener;
    }

    public int getCustomMonoColor() {
        return customMonoColor;
    }

    public void setCustomMonoColor(int customMonoColor) {
        this.customMonoColor = customMonoColor;
    }

    public boolean isUseMonoColor() {
        return useMonoColor;
    }

    public void setUseMonoColor(boolean useMonoColor) {
        this.useMonoColor = useMonoColor;
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.textView_name)
        TextView textViewName;
        @Bind(R.id.gThumb)
        GThumb gThumb;
        @Bind(R.id.textView_country)
        TextView textViewCountry;

        public ContactViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindUser(final CaptainPlayer captainPlayer) {
            textViewName.setText(captainPlayer.getFirstName() + " " + captainPlayer.getLastName());
            textViewCountry.setText(captainPlayer.getCountryName());

            //click listener
            if (needToSetClickListener) {
                gThumb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "Clicked on " + captainPlayer.getFirstName(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                gThumb.setOnClickListener(null);
            }

            //bold
            gThumb.setUseBoldText(useBold);

            //shape
            gThumb.setBackgroundShape(backgroundOption);

            //multi color
            if (useMonoColor) {
                gThumb.setMonoColor(customMonoColor);
            } else {
                gThumb.applyMultiColor();
            }

            gThumb.loadThumbForName(captainPlayer.getUrl(), captainPlayer.getFirstName(), captainPlayer.getLastName());
        }
    }
}
