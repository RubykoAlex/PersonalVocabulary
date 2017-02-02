package alex.personalvocabulary.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import alex.personalvocabulary.common.RubykoFragment;

import java.util.ArrayList;

/**
 * Created by yegor on 26/02/16.
 */

public class RubykoPagerAdapter extends SmartFragmentStatePagerAdapter {

    private ArrayList<Fragment> arrFragment = new ArrayList<>();
    private final ViewPager viewPager;

    public RubykoPagerAdapter(FragmentManager fragmentManager, ViewPager viewPager) {
        super(fragmentManager);
        this.viewPager = viewPager;
    }

    public void add(RubykoFragment rubykoFragment, int pos) {
        viewPager.setCurrentItem(0, false);
        if (arrFragment.size() <= pos) {
            arrFragment.add(rubykoFragment);
        } else {
            arrFragment.set(pos, rubykoFragment);
        }
        this.notifyDataSetChanged();
        viewPager.setCurrentItem(pos - 1, false);
        viewPager.setCurrentItem(pos);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return arrFragment.size();
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        return arrFragment.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

}