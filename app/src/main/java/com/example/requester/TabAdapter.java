package com.example.requester;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class TabAdapter extends FragmentPagerAdapter  implements TabNotifier{
    Context context;
    int totalTabs;
    int currentPos = 0;

    public TabAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        currentPos = position;
        switch (position) {
            case 0:
                return new PartownerFrag();
            case 1:
                return new ProviderFrag();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    @Override
    public void notifyAdapter() {
        System.out.println("ADAPTER CHANGING");
        switch (currentPos) {
            case 0:
                ((PartownerFrag) getItem(currentPos)).notifyAdapter();
                break;
            case 1:
                ((ProviderFrag) getItem(currentPos)).notifyAdapter();
                break;
        }
    }
}
