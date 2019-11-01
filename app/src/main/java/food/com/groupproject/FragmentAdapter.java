package food.com.groupproject;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import food.com.groupproject.UI.LocationImage;
import food.com.groupproject.UI.Mapactivity;

public class FragmentAdapter extends FragmentPagerAdapter {

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return Mapactivity.getInstance();

                case 1:
            return LocationImage.getInstance();



        }

        return null;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {


        return super.instantiateItem(container, position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
