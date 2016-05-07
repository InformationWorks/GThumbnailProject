package com.hbb20.gthumbnailproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.tabLayout)
    android.support.design.widget.TabLayout tabLayout;
    GListFragment gListFragment;
    ConfigFragment configFragment;
    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupPager();
    }

    private void setupPager() {
        if (pagerAdapter == null) {
            pagerAdapter = new PagerAdapter(getSupportFragmentManager(), this);
        }
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public GListFragment getListFragment() {
        return gListFragment;
    }

    public void scrollToList() {
        if(viewPager!=null){
            viewPager.setCurrentItem(0);
        }
    }

    class PagerAdapter extends FragmentPagerAdapter {
        MainActivity mainActivity;

        public PagerAdapter(FragmentManager fm, MainActivity mainActivity) {
            super(fm);
            this.mainActivity = mainActivity;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (mainActivity.gListFragment == null) {
                        mainActivity.gListFragment = new GListFragment();
                    }
                    return mainActivity.gListFragment;
                case 1:
                    if (mainActivity.configFragment == null) {
                        mainActivity.configFragment = new ConfigFragment();
                    }
                    configFragment.setMainActivity(mainActivity);
                    return mainActivity.configFragment;
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "LIST";
                case 1:
                    return "CONFIG";
            }
            return "";
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
