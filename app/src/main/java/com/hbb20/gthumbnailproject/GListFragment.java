package com.hbb20.gthumbnailproject;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hbb20.GThumb;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class GListFragment extends Fragment {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    UserRecyclerAdapter userRecyclerAdapter;

    public GListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, getView());
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        userRecyclerAdapter = new UserRecyclerAdapter(getContext(), getDummyUserList());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(userRecyclerAdapter);
        GThumb gt = new GThumb(getContext());
    }

    private List<CaptainPlayer> getDummyUserList() {
        List<CaptainPlayer> captainPlayerList = new ArrayList<>();

        captainPlayerList.add(new CaptainPlayer("Asghar", "Stanikzai", "Afhanistan", ""));
        captainPlayerList.add(new CaptainPlayer("Steven", "Smith", "Australia", ""));
        captainPlayerList.add(new CaptainPlayer("Eoin", "Morgan", "England", ""));
        captainPlayerList.add(new CaptainPlayer("Mahendrasinh", "Dhoni", "India", "https://s.yimg.com/qx/cricket/fufp/images/3676_large-16-1-2011-df12c86ee47d28cdc1e6776c202f321c.jpg"));
        captainPlayerList.add(new CaptainPlayer("William", "Porterfield", "Ireland", ""));
        captainPlayerList.add(new CaptainPlayer("Kane", "Williamson", "New Zealand", ""));
        captainPlayerList.add(new CaptainPlayer("Azhar", "Ali", "Pakistan", ""));
        captainPlayerList.add(new CaptainPlayer("Preston", "Mommsen", "Scotland", ""));
        captainPlayerList.add(new CaptainPlayer("AB de", "Villiers", "South Africa", "https://s.yimg.com/qx/cricket/fufp/images/3675_large-20-6-2012-9549fcb8e83238bb4736dafd21cf2569.jpg"));
        captainPlayerList.add(new CaptainPlayer("Angelo", "Mathews", "Sri Lanka", ""));
        captainPlayerList.add(new CaptainPlayer("Mohammad", "Tauqir", "UAE", ""));
        captainPlayerList.add(new CaptainPlayer("Jason", "Holder", "West Indies", "https://s.yimg.com/qx/cricket/fufp/images/10232_large-20-1-2013-7e85cbf37032ba0009a093a009a37616.jpg"));
        captainPlayerList.add(new CaptainPlayer("Elton", "Chigumbura", "Zimbabwe", ""));
        return captainPlayerList;
    }

    public void updateAdapter(ConfigFragment configFragment) {
        userRecyclerAdapter.setUseBold(configFragment.useBold());
        userRecyclerAdapter.setBackgroundOption(configFragment.getSelectedShape());
        userRecyclerAdapter.setNeedToSetClickListener(configFragment.needToSetClickListener());
        userRecyclerAdapter.notifyDataSetChanged();
        userRecyclerAdapter.setUseMonoColor(configFragment.useMonoColor());
        userRecyclerAdapter.setCustomMonoColor(configFragment.getCustomMonoColor());
    }
}
