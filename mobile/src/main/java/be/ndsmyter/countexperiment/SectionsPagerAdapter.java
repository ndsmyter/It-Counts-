package be.ndsmyter.countexperiment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "CE";

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    private List<SectionFragment> fragmentList = new ArrayList<SectionFragment>();

    @Override
    public Fragment getItem(int position) {
        while (position >= fragmentList.size()) {
            // Instantiate a new section fragment
            fragmentList.add(SectionFragment.newInstance(fragmentList.size() + 1));
        }
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        // The size is asked first. And using the size, the correct number of items is requested so they can be shown on the screen.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ((SectionFragment) getItem(position)).getTitle();
    }
}
