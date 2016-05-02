package com.hbb20.gthumbnailproject;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import com.hbb20.GThumb;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfigFragment extends Fragment {

    List<GThumb> gThumbList;
    List<CaptainPlayer> captainPlayers;
    MainActivity mainActivity;
    String selectedShape = GThumb.SHAPE_ROUND;
    boolean needToSetClickListener = true;
    boolean useBold = false;
    @Bind(R.id.demo1)
    GThumb gThumb1;
    @Bind(R.id.demo2)
    GThumb gThumb2;
    @Bind(R.id.demo3)
    GThumb gThumb3;
    @Bind(R.id.demo4)
    GThumb gThumb4;
    @Bind(R.id.checkbox_useBold)
    CheckBox checkBoxUseBold;

    @Bind(R.id.radioGroupShape)
    RadioGroup radioGroupShape;
    @Bind(R.id.radio_round)
    RadioButton radioRound;
    @Bind(R.id.radio_square)
    RadioButton radioSquare;

    @Bind(R.id.radioGroupClick)
    RadioGroup radioGroupClick;
    @Bind(R.id.radio_click_set)
    RadioButton radioClickSet;
    @Bind(R.id.radio_click_avoid)
    RadioButton radioClickAvoid;

    @Bind(R.id.radioGroupColor)
    RadioGroup radioGroupColor;

    @Bind(R.id.viewSampleColor)
    View viewSampleColor;
    @Bind(R.id.seekbar_red)
    SeekBar seekBarRed;
    @Bind(R.id.seekbar_green)
    SeekBar seekBarGreen;
    @Bind(R.id.seekbar_blue)
    SeekBar seekBarBlue;
    @Bind(R.id.overlay)
    View overlay;
    int customMonoColor;
    boolean useMonoColor;

    public ConfigFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_config, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, getView());
        setInitial();
        setListeners();
    }

    private void setListeners() {

        //for bold
        checkBoxUseBold.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                useBold = isChecked;
                applyChanges();
            }
        });

        //shape
        radioGroupShape.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_round:
                        selectedShape = GThumb.SHAPE_ROUND;
                        break;
                    case R.id.radio_square:
                        selectedShape = GThumb.SHAPE_SQUARE;
                        break;
                }
                applyChanges();
            }
        });

        //click listener
        radioGroupClick.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_click_set:
                        needToSetClickListener = true;
                        break;
                    case R.id.radio_click_avoid:
                        needToSetClickListener = false;
                        break;
                }
                applyChanges();
            }
        });

        //mono color selector
        radioGroupColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_color_mono:
                        useMonoColor = true;
                        enableSeekbars(true);
                        break;
                    case R.id.radio_color_multi:
                        useMonoColor = false;
                        enableSeekbars(false);
                        break;
                }
                applyChanges();
            }
        });

        //seek bar listeners
        SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setSampleColorFromSeekBar();
                applyChanges();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };
        seekBarRed.setOnSeekBarChangeListener(seekBarChangeListener);
        seekBarGreen.setOnSeekBarChangeListener(seekBarChangeListener);
        seekBarBlue.setOnSeekBarChangeListener(seekBarChangeListener);
    }

    private void enableSeekbars(boolean b) {
        seekBarRed.setEnabled(b);
        seekBarGreen.setEnabled(b);
        seekBarBlue.setEnabled(b);
        if (b) {
            overlay.setVisibility(View.GONE);
        } else {
            overlay.setVisibility(View.VISIBLE);
        }
    }

    private void setInitial() {
        //set demo thumbs
        gThumbList = new ArrayList<>();
        gThumbList.add(gThumb1);
        gThumbList.add(gThumb2);
        gThumbList.add(gThumb3);
        gThumbList.add(gThumb4);

        //set captainPlayer
        captainPlayers = new ArrayList<>();
        captainPlayers.add(new CaptainPlayer("Eoin", "Morgan", "England", ""));
        captainPlayers.add(new CaptainPlayer("Elton", "Chigumbura", "Zimbabwe", ""));
        captainPlayers.add(new CaptainPlayer("AB de", "Villiers", "South Africa", "https://s.yimg.com/qx/cricket/fufp/images/3675_large-20-6-2012-9549fcb8e83238bb4736dafd21cf2569.jpg"));
        captainPlayers.add(new CaptainPlayer("Steven", "Smith", "Australia", ""));

        loadAllThumbs();
        seekBarRed.setProgress(255);
        seekBarGreen.setProgress(86);
        seekBarBlue.setProgress(34);
        //seek bar disabled
        setSampleColorFromSeekBar();


        enableSeekbars(false);
    }

    private void setSampleColorFromSeekBar() {
        readCustomColor();
        viewSampleColor.setBackgroundColor(customMonoColor);
    }

    private void readCustomColor() {
        int r = seekBarRed.getProgress();
        int g = seekBarGreen.getProgress();
        int b = seekBarBlue.getProgress();
        customMonoColor = Color.rgb(r, g, b);
    }

    private void loadAllThumbs() {
        for (int i = 0; i < gThumbList.size() && i < captainPlayers.size(); i++) {
            GThumb gThumb = gThumbList.get(i);
            final CaptainPlayer captainPlayer = captainPlayers.get(i);
            gThumb.loadThumbForName(captainPlayer.getUrl(), captainPlayer.getFirstName(), captainPlayer.getLastName());
            if (needToSetClickListener()) {
                gThumb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Clicked on " + captainPlayer.getFirstName() + "\nClick listener is optional", Toast.LENGTH_LONG).show();
                    }
                });
            } else {
                gThumb.setOnClickListener(null);
            }
        }
    }

    public void applyChanges() {
        //changes to demo
        setChangesForAllDemo();

        //to changes to list
        if (mainActivity != null && mainActivity.getgListFragment() != null) {
            mainActivity.getgListFragment().updateAdapter(this);
        }
    }

    private void setShapeForAllDemo() {

    }

    private void setChangesForAllDemo() {
        for (GThumb gThumb : gThumbList) {
            gThumb.setBoldText(useBold());
            gThumb.setBackgroundShape(getSelectedShape());
            gThumb.setOnClickListener(null);
            if (useMonoColor) {
                gThumb.setMonoColor(customMonoColor);
            } else {
                gThumb.applyMultiColor(true);
            }
        }
        loadAllThumbs();
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public boolean useBold() {
        return useBold;
    }

    public String getSelectedShape() {
        return selectedShape;
    }

    public boolean needToSetClickListener() {
        return needToSetClickListener;
    }

    public boolean useMonoColor() {
        return useMonoColor;
    }

    public int getCustomMonoColor() {
        return customMonoColor;
    }
}
