package alex.personalvocabulary;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Type;

import alex.personalvocabulary.common.RubykoFragment;


/**
 * Created by yegor on 14/02/16.
 */
public final class ChooseFragment extends RubykoFragment<VocabularyActivity> implements View.OnClickListener {
    private TextView textView;

    /*
    textMain = (TextView) findViewById(R.id.textMain);
    Typeface face=Typeface.createFromAsset(getAssets(), "fons/digital-7 (mono italic).ttf");
    textMain.setTypeface(face);*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_choose, container, false);

        textView = (TextView) view.findViewById(R.id.choose_Edt);
        Typeface face = Typeface.createFromAsset(getActivity().getAssets(), "fonts/one.ttf");
        textView.setTypeface(face);
        final Button loginBtn = (Button) view.findViewById(R.id.loginBtn);
        final Button registrBtn = (Button) view.findViewById(R.id.regisrBtn);
        loginBtn.setOnClickListener(this);
        registrBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                getFragmentActivity().replaceFragment(new Bundle(), RepeatFragment.class, 1);
                break;
            case R.id.regisrBtn:
                getFragmentActivity().replaceFragment(new Bundle(), AddNewWordsFragment.class, 1);
                break;

        }
    }

}
