package alex.personalvocabulary;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import alex.personalvocabulary.adapter.DBAdapter;
import alex.personalvocabulary.adapter.Message;

/**
 * Created by alex on 15.03.16.
 */
public class MyDialog extends DialogFragment implements View.OnClickListener {

    Button yes,no;
   // DBAdapter dbAdapter;
    WordsListActivity wordsListActivity;
    String thisWord;
    Communicator communicator;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        communicator = (Communicator) activity;
    }

    public MyDialog(String word){
        wordsListActivity = new WordsListActivity();
        wordsListActivity.myWord = word;
        thisWord = word;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_dialog, null);
        yes = (Button) view.findViewById(R.id.dialog_yes);
        no = (Button) view.findViewById(R.id.dialog_no);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        setCancelable(false);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.dialog_yes){
            dismiss();
            communicator.doWork();


        }
        else {
            dismiss();
        }

    }


    public void getDelete(){



    }

    interface Communicator {
         void onDialogMessage(String message);
         void doWork();
    }
}


