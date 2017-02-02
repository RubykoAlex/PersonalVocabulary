package alex.personalvocabulary.common;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import alex.personalvocabulary.common.RubykoApplication;

/**
 * Created by yegor on 14/02/16.
 */
public abstract class RubykoBaseActivity extends FragmentActivity {

    public <T extends Fragment> void replaceFragment(final Bundle bundle, final Class<T> fragmentClass) {
        try {
            RubykoFragment fragment = (RubykoFragment) fragmentClass.newInstance();
            fragment.setArguments(bundle);

            FragmentManager fragmentManager = RubykoBaseActivity.this.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.addToBackStack(fragmentClass.getName());
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                    android.R.anim.fade_in, android.R.anim.fade_out);

            fragmentTransaction.replace(getContainerId(), fragment, fragmentClass.getName());
            fragmentTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T extends Fragment> void showFragment(final Bundle bundle, final Class<T> fragmentClass) {
        try {
            RubykoFragment fragment = (RubykoFragment) fragmentClass.newInstance();
            fragment.setArguments(bundle);
            FragmentManager fragmentManager = RubykoBaseActivity.this.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.addToBackStack(fragmentClass.getName());
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                    android.R.anim.fade_in, android.R.anim.fade_out);
            fragment.show(fragmentTransaction, fragmentClass.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract int getContainerId();

    @Override
    public RubykoApplication getApplicationContext() {
        return (RubykoApplication) super.getApplicationContext();
    }
}
