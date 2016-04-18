package com.hbb20.gthumbnaillibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by hbb20 on 18/4/16.
 */
public class GThumb extends RelativeLayout {

    Context context;
    View rootView;
    private static String TAG="GThumb :";
    public GThumb(Context context) {
        super(context);
        this.context=context;
    }


    public GThumb(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init(attrs);
    }

    public GThumb(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        init(attrs);
    }

    private void init(AttributeSet attrs){
        Log.d(TAG,"Initialization");
        rootView= LayoutInflater.from(context).inflate(R.layout.layout_user_thumb,this,true);
    }

}
