package org.likesyou.bensalcie.pushharder;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class PagerViewAdapter extends FragmentPagerAdapter {
    public PagerViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {


        switch (i)
        {
            case 0:
                ProfileFragment profileFragment=new ProfileFragment();
                return profileFragment;
            case 1:
                UsersFragment usersFragment=new UsersFragment();
                return usersFragment;
            case 2:
                NotificationFragment notificationFragment=new NotificationFragment();
                return notificationFragment;

        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
