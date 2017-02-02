package alex.personalvocabulary;


import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import alex.personalvocabulary.adapter.DBAdapter;
import alex.personalvocabulary.common.RubykoFragment;

import alex.personalvocabulary.validation.LocalValidator;
import alex.personalvocabulary.validation.concrete.TranslationValidator;
import alex.personalvocabulary.validation.concrete.WordValidator;
import alex.personalvocabulary.view.RubykoEditText;


/**
 * Created by alex on 16.02.16.
 */
public final class AddNewWordsFragment extends RubykoFragment<VocabularyActivity> implements View.OnClickListener {


    private LocalValidator<String, RubykoEditText> localValidator;
    RubykoEditText wordEdt, translEdt;
    DBAdapter dbAdapter;


    @Nullable
    @Override
    public final View onCreateView(final LayoutInflater pInflater, final ViewGroup pContainer, final Bundle pSavedInstanceState) {
        final View view = pInflater.inflate(R.layout.fragment_add_words, pContainer, false);

        wordEdt = (RubykoEditText) view.findViewById(R.id.editText_new_word);
        translEdt = (RubykoEditText) view.findViewById(R.id.editText_translation);

        localValidator = new WordValidator(wordEdt).and(new TranslationValidator(translEdt));

        final Button saveNewWordBtn = (Button) view.findViewById(R.id.save_new_word_btn);
        saveNewWordBtn.setOnClickListener(this);

        final Button newWordBtn = (Button) view.findViewById(R.id.new_word_btn);
        newWordBtn.setOnClickListener(this);

        dbAdapter = new DBAdapter(getFragmentActivity());
        return view;
    }




    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.save_new_word_btn: {
                if (localValidator.isValid()) {
                   // final String newWord = localValidator.getDataAll(R.id.editText_new_word);
                    //final String translation = localValidator.getDataAll(R.id.editText_translation);
                    addWord();

                } else {
                    localValidator.updateAll();
                }
                break;
            }
            case R.id.new_word_btn: {
                localValidator.updateAll();
                wordEdt.getText().clear();
                translEdt.getText().clear();


            } break;
        }
    }


        public void addWord(){

            String newWord = localValidator.getDataAll(R.id.editText_new_word);
            String translation = localValidator.getDataAll(R.id.editText_translation);

            long id = dbAdapter.insertRecord(newWord, translation);
            if (id < 0){
                alex.personalvocabulary.adapter.Message.getMessage(getFragmentActivity(), "Unsuccessfull");
            }
            else {
                alex.personalvocabulary.adapter.Message.getMessage(getFragmentActivity(), "Successfull");
            }
            dbAdapter.close();

        }




}
