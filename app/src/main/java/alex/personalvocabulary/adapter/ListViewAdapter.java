package alex.personalvocabulary.adapter;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import alex.personalvocabulary.R;
import alex.personalvocabulary.WordTransl;

/**
 * Created by alex on 11.03.16.
 * public ListViewAdapter(Context context, String nummber, String myWord, String myTranslation) {
 super(context, R.layout.row_layout, nummber, myWord, myTranslation);
 }
 */
public class ListViewAdapter extends ArrayAdapter<WordTransl>{

    //List list = new ArrayList();
    private Activity activity;
    int id;
    LinkedList<WordTransl> items;
    public ListViewAdapter(Activity context, int resource, LinkedList<WordTransl> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.id = resource;
        this.items = objects;
    }



    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(id, null);
        }
        WordTransl item = items.get(position);
        TextView tv_word = (TextView) convertView.findViewById(R.id.list_view_word_TextView);
        TextView tv_transl = (TextView) convertView.findViewById(R.id.list_view_translation_TextView);
        TextView tv_number = (TextView) convertView.findViewById(R.id.number_TextView);

        tv_word.setText(item.getMyWord());
        tv_transl.setText(item.getMyTranslation());
        int number  = items.indexOf(item) + 1;
        String numberStr = "" + number;
        tv_number.setText(numberStr);

        return convertView;
    }




}
