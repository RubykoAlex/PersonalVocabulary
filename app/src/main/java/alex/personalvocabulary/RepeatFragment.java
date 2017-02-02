package alex.personalvocabulary;


import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;

import alex.personalvocabulary.adapter.DBAdapter;
import alex.personalvocabulary.adapter.ListViewAdapter;
import alex.personalvocabulary.adapter.Message;
import alex.personalvocabulary.adapter.RandomGenerator;
import alex.personalvocabulary.common.RubykoFragment;
import alex.personalvocabulary.view.RubykoEditText;


public final class RepeatFragment extends RubykoFragment<VocabularyActivity> implements View.OnClickListener {

    DBAdapter dbAdapter;
    RubykoEditText startWordEdt, endtWordEdt;
    TextView wordEdt, translationEdt;
    LinkedList<WordTransl> items, itemsInQueue;
    ListViewAdapter viewAdapter;
    private int myStart, myFinish;
    LinkedList<Integer> ramdomList;
    RandomGenerator randomGenerator;
    LinkedList<String> currentCouple;
    String currentWord, currentTranslation;


    @Nullable
    @Override
    public final View onCreateView(final LayoutInflater pInflater, final ViewGroup pContainer, final Bundle pSavedInstanceState) {
        final View view = pInflater.inflate(R.layout.fragment_repeat, pContainer, false);

        final Button showListBtn = (Button) view.findViewById(R.id.words_list_btn);
        showListBtn.setOnClickListener(this);

        final Button showTranslationBtn = (Button) view.findViewById(R.id.get_translation_btn);
        showTranslationBtn.setOnClickListener(this);

        final Button deleteBtn = (Button) view.findViewById(R.id.delete_btn);
        deleteBtn.setOnClickListener(this);

        final Button updateBtn = (Button) view.findViewById(R.id.update_btn);
        updateBtn.setOnClickListener(this);

        startWordEdt = (RubykoEditText) view.findViewById(R.id.repeat_editText_minValue);
        endtWordEdt = (RubykoEditText) view.findViewById(R.id.repeat_editText_maxValue);

        wordEdt = (TextView) view.findViewById(R.id.repeat_word_Edt);
        translationEdt = (TextView) view.findViewById(R.id.repeat_translation_Edt);

        dbAdapter = new DBAdapter(getFragmentActivity());
        return view;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case (R.id.words_list_btn): {
                getMyQueue();
                getRepeat();
                wordEdt.setText(currentWord);
                translationEdt.setText("");
            }
            break;

            case (R.id.get_translation_btn): {
                showTranslation();
                translationEdt.setText(currentTranslation);
            }
            break;

            case (R.id.delete_btn): {
                getRepeat();
                wordEdt.setText(currentWord);
                translationEdt.setText("");
            }
            break;

            case (R.id.update_btn): {
                Intent myIntent = new Intent(v.getContext(), WordsListActivity.class);
                startActivityForResult(myIntent, 0);
            }
        }

    }

    private LinkedList<String> getRepeat(){
        try {

            if (itemsInQueue.size() == 0) {
                wordEdt.setText("");
                Message.getMessage(getFragmentActivity(), "Finished");
            }
            else {
                currentCouple = new LinkedList<>();
                currentCouple.push(itemsInQueue.getFirst().getMyTranslation());
                currentCouple.push(itemsInQueue.getFirst().getMyWord());
                itemsInQueue.poll();
                currentWord = currentCouple.getFirst();
                currentTranslation = currentCouple.getLast();
                return currentCouple;
            }
        }catch (Exception e){
            Message.getMessage(getFragmentActivity(), "Please, click the" +" ''Start'' "+ "button");
        }
        return null;

    }

    public String showTranslation(){
        try {
            return currentTranslation;


        }catch (Exception e){
            Message.getMessage(getFragmentActivity(), "word is not specified");
        }
        Message.getMessage(getFragmentActivity(), "word is not specified");
        return null;

    }


    public void getMyQueue() {
        getRandomeGenerator();
        if (ramdomList.size() == 0) {
            Message.getMessage(getFragmentActivity(), "Please check the repeat setting");
        }
        itemsInQueue = new LinkedList<>();
        while (ramdomList.size() > 0) {
            int value = Integer.valueOf(ramdomList.get(0));
            ramdomList.poll();
            itemsInQueue.push(viewAdapter.getItem(value - 1));

        }
        }

    public void getWoldList() {
        DBAdapter dbAdapter = new DBAdapter(getFragmentActivity());
        dbAdapter.getAllWords();
        dbAdapter.close();
        items = dbAdapter.getArrayList();
        viewAdapter = new ListViewAdapter(getFragmentActivity(), R.layout.row_layout, items);
    }

    public int getStartPoint() {
        String start = startWordEdt.getText().toString();
        if (start.equals("")||((Integer.parseInt(start)==0))) {
            start = "1";
            int statrPoint = Integer.parseInt(start);
            myStart = statrPoint;

        } else {
            int statrPoint = Integer.parseInt(start);
            myStart = statrPoint;
        }
        return myStart;
    }

    public int getFinishPoint() {
        getWoldList();
        String finish = endtWordEdt.getText().toString();
        if ((finish.equals("")) || ((Integer.parseInt(finish)) >= viewAdapter.getCount())||((Integer.parseInt(finish)==0))) {
            int finish1 = viewAdapter.getCount();
            myFinish = finish1;
        } else {
            int finishPoint = Integer.parseInt(finish);
            myFinish = finishPoint;
        }
        return myFinish;
    }

    public void getRandomeGenerator() {
        getStartPoint();
        getFinishPoint();
        randomGenerator = new RandomGenerator(myStart, myFinish);
        ramdomList = randomGenerator.getMylistNumber();
    }
}



