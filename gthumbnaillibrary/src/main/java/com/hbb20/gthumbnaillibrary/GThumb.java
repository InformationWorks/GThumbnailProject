package com.hbb20.gthumbnaillibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by hbb20 on 18/4/16.
 */
public class GThumb extends RelativeLayout {

    Context context;
    View rootView;
    RelativeLayout relativeForeground;
    TextView textViewInitials;
    ImageView imageViewRealImage,imageViewColorBg;
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
        log("Initialization");
        rootView= LayoutInflater.from(context).inflate(R.layout.layout_user_thumb,this,true);
        relativeForeground=(RelativeLayout) rootView.findViewById(R.id.relative_foreground);
        textViewInitials=(TextView) rootView.findViewById(R.id.textView_initials);
        imageViewRealImage=(ImageView) rootView.findViewById(R.id.imageView_real);
        imageViewColorBg=(ImageView) rootView.findViewById(R.id.imageView_color_bg);
        applyCustomAttributes(attrs);
    }

    private void applyCustomAttributes(AttributeSet attrs) {
        log("Applying custom attribute");
        TypedArray a= context.getTheme().obtainStyledAttributes(attrs,R.styleable.GThumb,0,0);
        try{

            //custom text size
            int textSize=a.getDimensionPixelSize(R.styleable.GThumb_textSize,Math.round(12 * (context.getResources().getDisplayMetrics().xdpi / DisplayMetrics.DENSITY_DEFAULT)));
            setTextSize(textSize);


        }catch (Exception ex){
            ex.printStackTrace();
            log("Error in applying custom attributes");
        }
    }

    private int getFitSize() {
        int minDim=getWidth();
        if(minDim>getHeight()){
            minDim=getHeight();
        }
        return minDim==0?25:minDim;
    }

    /**
     * This will update the size of text
     * @param textSize size of text in pixel
     */
    public void setTextSize(int textSize) {
        textViewInitials.setTextSize(textSize);
    }

    private void log(String s) {
        Log.d(TAG,s);
    }

}
