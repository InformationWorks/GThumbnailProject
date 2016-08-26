package com.hbb20;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hbb20.gthumbnaillibrary.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by hbb20 on 18/4/16.
 */
public class GThumb extends RelativeLayout {

    private static String SHAPE_ROUND = "0";
    private static String SHAPE_SQUARE = "1";
    private static String TAG = "GThumb:";
    Context context;
    View rootView;
    RelativeLayout relativeForeground, relativeHolder, relativeBackgroundHolder;
    TextView textViewInitials;
    ImageView imageViewRealImage, imageViewColorBg;
    String bgShape, textStyle = null;
    boolean flagBoldText = false;
    int blockSize = -1;
    int maxLength;
    int monoBGColor, monoTextColor;
    boolean flagMonoColor;
    boolean hideUntilImageLoaded;
    private int bgColorEntropy;

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
        rootView = LayoutInflater.from(context).inflate(R.layout.layout_gt_user_thumb, this, true);
        relativeForeground = (RelativeLayout) rootView.findViewById(R.id.relative_gt_foreground);
        relativeHolder = (RelativeLayout) rootView.findViewById(R.id.relative_gt_holder);
        textViewInitials = (TextView) rootView.findViewById(R.id.textView_gt_initials);
        imageViewRealImage = (ImageView) rootView.findViewById(R.id.imageView_gt_real);
        imageViewColorBg = (ImageView) rootView.findViewById(R.id.imageView_gt_color_bg);
        relativeBackgroundHolder=(RelativeLayout)rootView.findViewById(R.id.relative_gt_background);
        applyCustomAttributes(attrs);
    }

    private void applyCustomAttributes(AttributeSet attrs) {
        log("Applying custom attribute");
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.GThumb, 0, 0);
        try {

            //custom text size
            int textSize = a.getDimensionPixelSize(R.styleable.GThumb_gtTextSize, Math.round(30 * (context.getResources().getDisplayMetrics().xdpi / DisplayMetrics.DENSITY_DEFAULT)));
            setTextSize(textSize);

            //colors
            monoBGColor = a.getColor(R.styleable.GThumb_gtMonoBGColor, -1);
            monoTextColor = a.getColor(R.styleable.GThumb_gtMonoTextColor, -1);
            if (monoBGColor != -1) {
                flagMonoColor = true;

                if (monoTextColor == -1) {
                    monoTextColor = getContrastGrayColor(monoBGColor);
                }
            }
            setColors();

            //preview text
            String preview = a.getString(R.styleable.GThumb_gtPreviewText);
            if (preview == null || preview.length() == 0) {
                preview = "GT";
            }
            setAsInitialText(preview);

            //hide until image loaded
            hideUntilImageLoaded=a.getBoolean(R.styleable.GThumb_gtHideUntilImageLoaded, false);

            //set bg shape
            bgShape = a.getString(R.styleable.GThumb_gtBackgroundShape);
            applyBackgroundShape();

            //text style (bold/normal)
            flagBoldText = a.getBoolean(R.styleable.GThumb_gtUseBoldText, false);
            setUseBoldText(flagBoldText);

            //null click listener
            setOnClickListener(null);

        } catch (Exception ex) {
            ex.printStackTrace();
            log("Error in applying custom attributes");
        }
    }

    /**
     * refresh the background shape what ever is set. By default, it will be ROUND
     */
    private void applyBackgroundShape() {
        if (bgShape == null) {
            bgShape = SHAPE_ROUND;
        }

        if (bgShape.equals(SHAPE_SQUARE)) {
            imageViewColorBg.setImageDrawable(context.getResources().getDrawable(R.drawable.square_white));
        } else {
            imageViewColorBg.setImageDrawable(context.getResources().getDrawable(R.drawable.oval_white));
        }
    }

    private void setAsInitialText(String initials) {
        if (initials == null || initials.trim().length() == 0) {
            initials = "";
        } else {
            initials = initials.trim();
            if (initials.length() > 2) {
                initials = initials.substring(0, 2);
            }
            initials = initials.trim().toUpperCase();
        }
        textViewInitials.setText(initials);
    }

    private void setTextColor(int color) {
        textViewInitials.setTextColor(color);
    }

    private void setThumbBackground(int color) {
        imageViewColorBg.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
    }

    /**
     * This might be useful for textAutoFit feature.
     *
     * @return
     */
    private int getFitSize() {
        int minDim = getWidth();
        if (minDim > getHeight()) {
            minDim = getHeight();
        }
        textViewInitials.setText("" + minDim);
        return minDim == 0 ? 25 : minDim;
    }

    private int getContrastGrayColor(int color) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int avg = (blue + green + red) / 3;
        int gray = 128;
        if (avg >= 128) {
            gray = 64 - (avg / 4);
        } else {
            gray = 192 + (avg / 2);
        }
        //textViewInitials.setText("" + gray);
        return Color.rgb(gray, gray, gray);
    }

    private void log(String s) {
        Log.d(TAG, s);
    }

    private void loadImage(String imageURL) {
        if (URLUtil.isValidUrl(imageURL)) {


            if(hideUntilImageLoaded){
                relativeForeground.setVisibility(INVISIBLE);
                relativeBackgroundHolder.setVisibility(INVISIBLE);
            }else {
                relativeBackgroundHolder.setVisibility(VISIBLE);
                relativeForeground.setVisibility(VISIBLE);
            }
            Callback callback=new Callback() {
                @Override
                public void onSuccess() {
                    relativeForeground.setVisibility(VISIBLE);
                    relativeBackgroundHolder.setVisibility(VISIBLE);
                }

                @Override
                public void onError() {
                    relativeForeground.setVisibility(INVISIBLE);
                    relativeBackgroundHolder.setVisibility(VISIBLE);
                }
            };
            if (bgShape.equals(SHAPE_SQUARE)) {
                Picasso.with(context).load(imageURL).into(imageViewRealImage,callback);
            } else {
                Picasso.with(context).load(imageURL).transform(new CircleTransform()).into(imageViewRealImage,callback);
            }
        } else { //if URL is not valid
            relativeForeground.setVisibility(GONE);
            relativeBackgroundHolder.setVisibility(VISIBLE);
        }
    }

    /**
     * Entropy is responsible for background color.
     *
     * @param s
     * @return
     */
    private int getEntropy(String s) {
        int entropy = 0;
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            entropy = entropy + c;
        }
        return entropy;
    }

    /**
     * Colors are set using entropy. Making this private will force user to add name. Names are bigger than initials so a good variation in entropy will be generated.
     *
     * @param imageURL
     * @param initials
     * @param colorEntropy
     */
    private void loadThumbForInitials(String imageURL, String initials, int colorEntropy) {
        this.bgColorEntropy = colorEntropy;
        setAsInitialText(initials);
        setColors();
        loadImage(imageURL);
    }

    private void setColors() {
        if (flagMonoColor) {
            setThumbBackground(monoBGColor);
            setTextColor(monoTextColor);
        } else {
            GThumbSwatch gThumbSwatch = GThumbSwatch.getGThumbSwatchForEntropy(bgColorEntropy);
            setThumbBackground(gThumbSwatch.colorBG);
            setTextColor(gThumbSwatch.colorText);
        }
    }



    /*
    Public APIs*/

    /**
     *       AAAA       PPPPPPPPPP      IIIIIIIIIIIII
     *      AA  AA      PP        PP          II
     *     AA    AA     PP         PP         II
     *    AA      AA    PP        PP          II
     *   AAAAAAAAAAAA   PPPPPPPPPP            II
     *  AA          AA  PP                    II
     * AA            AA PP                    II
     *AA              AAPP              IIIIIIIIIIIIII
     */

    /**
     * This will set two characters as thumb initials. First characters of both names will together make initials.
     *
     * @param imageURL   URL of thumbnail which you intended to load as thumb
     *                   if null then only initials will be set, it will not
     * @param firstName  first name of entity
     * @param secondName second name of entity
     *                   firstName:"Steve" , secondName:"jobs" --output initials "SJ"
     *                   firstName:"Stephen" , secondName:"amell" --output initials "SA"
     */
    public void loadThumbForName(String imageURL, String firstName, String secondName) {
        if (secondName == null || secondName.trim().length() == 0) {
            if (firstName != null && firstName.trim().length() > 0) {
                firstName = firstName.trim();
                loadThumbForName(imageURL, firstName);
            } else {
                loadThumbForName(imageURL, "");
            }
            return;
        }

        if (firstName == null || firstName.trim().length() == 0) {
            secondName = secondName.trim();
            loadThumbForName(imageURL, secondName);
            return;
        }

        firstName = firstName.trim();
        secondName = secondName.trim();
        String initials = firstName.substring(0, 1) + secondName.substring(0, 1);
        loadThumbForInitials(imageURL, initials, getEntropy(imageURL + firstName + secondName));
    }

    /**
     * This function will set one character as thumb initials.
     *
     * @param imageURL  URL of thumbnail which you intended to load as thumb
     * @param firstName first name of entity. First character of this name will be set as Thumb initial.
     *                  firstname:"Daizy"--output "D"
     *                  firstname:"daina"--output "D"
     */
    public void loadThumbForName(String imageURL, String firstName) {
        String initials = "";
        if (firstName != null && firstName.trim().length() >= 1) {
            firstName = firstName.trim();
            initials = firstName.charAt(0) + "";
        }
        loadThumbForInitials(imageURL, initials, getEntropy(imageURL + firstName));
    }
    /**
     * There are few cases where developer want to set different event than entity tile.
     *
     * @param clickListener listener for thumb, null will remove click listener.
     */
    @Override
    public void setOnClickListener(View.OnClickListener clickListener) {
        if (clickListener == null) {
            relativeHolder.setOnClickListener(null);
            relativeHolder.setEnabled(false);
            relativeHolder.setClickable(false);
        } else {
            relativeHolder.setClickable(true);
            relativeHolder.setEnabled(true);
            relativeHolder.setOnClickListener(clickListener);
        }
    }

    /**
     * This will set background shape for thumbnail
     *
     * @param backgroundShape GThumb.BACKGROUND_SHAPE.SQUARE to set square background
     *                        GThumb.BACKGROUND_SHAPE.ROUND to set round background
     */
    public void setBackgroundShape(BACKGROUND_SHAPE backgroundShape) {
        if (backgroundShape == BACKGROUND_SHAPE.SQUARE) {
            bgShape = SHAPE_SQUARE;
        } else if (backgroundShape == BACKGROUND_SHAPE.ROUND) {
            bgShape = SHAPE_ROUND;
        }
        applyBackgroundShape();
    }

    /**
     * To apply multicolor, removes mono color from background and set color based on entropy.
     */
    public void applyMultiColor() {
        flagMonoColor = false;
        setColors();
    }

    /**
     * This will set mono color for thumb background.
     * Color of initials text will be set as gray scale contrast of given backgroundColor.
     * to set custom text color as well, use setMonoColor(color,color); variant of the method
     *
     * @param monoBackgroundColor color for background
     */
    public void setMonoColor(int monoBackgroundColor) {
        setMonoColor(monoBackgroundColor, getContrastGrayColor(monoBackgroundColor));
    }

    /**
     * This will enable mono color and set custom background color and custom initial text color
     *
     * @param monoBackgroundColor custom background color
     * @param monoTextColor       custom text color
     */
    public void setMonoColor(int monoBackgroundColor, int monoTextColor) {
        flagMonoColor = true;
        this.monoBGColor = monoBackgroundColor;
        this.monoTextColor = monoTextColor;
        setColors();
    }

    /**
     * To make initials bold or not.
     *
     * @param useBoldText will set text bold if true, else it will make font normal.
     */
    public void setUseBoldText(boolean useBoldText) {
        this.flagBoldText = useBoldText;
        if (isInEditMode()) {
            if (useBoldText) {
                textViewInitials.setTypeface(textViewInitials.getTypeface(), Typeface.BOLD);
            } else {
                textViewInitials.setTypeface(textViewInitials.getTypeface(), Typeface.NORMAL);
            }
        } else {
            if (useBoldText) {
                textViewInitials.setTypeface(null, Typeface.BOLD);
            } else {
                textViewInitials.setTypeface(null, Typeface.NORMAL);
            }
        }
    }

    /**
     * This will update the size of text
     *
     * @param textSize size of text in pixel
     */
    public void setTextSize(int textSize) {
        textViewInitials.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    public boolean isHideUntilImageLoaded() {
        return hideUntilImageLoaded;
    }

    /**
     * If this flag will be true, gthumb will be hidden until image is loaded.
     * if the image load fails or image url is not valid, it will show initials.
     * @param hideUntilImageLoaded
     */
    public void setHideUntilImageLoaded(boolean hideUntilImageLoaded) {
        this.hideUntilImageLoaded = hideUntilImageLoaded;
    }

    public enum BACKGROUND_SHAPE {ROUND, SQUARE}
}
