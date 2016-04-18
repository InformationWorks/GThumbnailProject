package com.hbb20.gthumbnaillibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
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
    ImageView imageViewRealImage, imageViewColorBg;
    int maxLength;
    boolean flagMonoColor;
    private static String TAG = "GThumb :";

    public GThumb(Context context) {
        super(context);
        this.context = context;
    }


    public GThumb(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    public GThumb(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        log("Initialization");
        rootView = LayoutInflater.from(context).inflate(R.layout.layout_user_thumb, this, true);
        relativeForeground = (RelativeLayout) rootView.findViewById(R.id.relative_foreground);
        textViewInitials = (TextView) rootView.findViewById(R.id.textView_initials);
        imageViewRealImage = (ImageView) rootView.findViewById(R.id.imageView_real);
        imageViewColorBg = (ImageView) rootView.findViewById(R.id.imageView_color_bg);
        applyCustomAttributes(attrs);
    }

    private void applyCustomAttributes(AttributeSet attrs) {
        log("Applying custom attribute");
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.GThumb, 0, 0);
        try {

            //custom text size
            int textSize = a.getDimensionPixelSize(R.styleable.GThumb_textSize, Math.round(12 * (context.getResources().getDisplayMetrics().xdpi / DisplayMetrics.DENSITY_DEFAULT)));
            setTextSize(textSize);

            //colors
            int monoBG = a.getColor(R.styleable.GThumb_monoBGColor, -1);
            int monoText = a.getColor(R.styleable.GThumb_monoTextColor, -1);
            if (monoBG != -1 || monoText != -1) {
                flagMonoColor = true;
            }

            if (monoBG == -1) {
                monoBG = context.getResources().getColor(R.color.google_deepPurple_500);
            }
            setThumbBackground(monoBG);


            if (monoText == -1) {
                monoText = context.getResources().getColor(R.color.g_white);
            }
            setTextColor(monoText);

            //preview text
            String preview=a.getString(R.styleable.GThumb_previewText);
            if(preview==null || preview.length()==0){
                preview="hb";
            }
            setAsInitialText(preview);


        } catch (Exception ex) {
            ex.printStackTrace();
            log("Error in applying custom attributes");
        }
    }

    private void setAsInitialText(String initials) {
        if(initials==null || initials.length()==0){
            initials="";
        }else {
            if(initials.length()>2){
                initials=initials.substring(0,2);
            }
            initials=initials.toUpperCase();
        }
        textViewInitials.setText(initials);
    }

    private void setTextColor(int color) {
        textViewInitials.setTextColor(color);
    }

    private void setThumbBackground(int color) {
        imageViewColorBg.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
    }

    private int getFitSize() {
        int minDim = getWidth();
        if (minDim > getHeight()) {
            minDim = getHeight();
        }
        return minDim == 0 ? 25 : minDim;
    }

    /**
     * This will update the size of text
     *
     * @param textSize size of text in pixel
     */
    public void setTextSize(int textSize) {
        textViewInitials.setTextSize(textSize);
    }

    private void log(String s) {
        Log.d(TAG, s);
    }

}
