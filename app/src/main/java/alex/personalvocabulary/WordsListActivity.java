package alex.personalvocabulary;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

import alex.personalvocabulary.adapter.DBAdapter;
import alex.personalvocabulary.adapter.ListViewAdapter;
import alex.personalvocabulary.adapter.Message;

/**
 * Created by alex on 05.03.16.
 */
public class WordsListActivity extends Activity implements MyDialog.Communicator {

    ListViewAdapter viewAdapter;
    DBAdapter dbAdapter;
    LinkedList<WordTransl> arrayList;
    ListView listView;
    String word;
    String myWord;

    int position;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_list);


        dbAdapter = new DBAdapter(this);
        dbAdapter.getAllWords();
       dbAdapter.close();
        arrayList = dbAdapter.getArrayList();

        viewAdapter = new ListViewAdapter(this,R.layout.row_layout, arrayList);
        listView = (ListView) findViewById(R.id.words_list_view);
        listView.setAdapter(viewAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

               

                WordTransl item = arrayList.get(position);
                word = item.getMyWord();
                getWordString(word);
                WordsListActivity.this.position = position;

                FragmentManager fragmentManager = getFragmentManager();
                MyDialog myDialog = new MyDialog(word);
                myDialog.show(fragmentManager, "My Dialog");
                return false;
            }
        });}


        public void getWordString (String word1){
           // Message.getMessage(WordsListActivity.this, word1);
            myWord = word1;

    }


    @Override
    public void onDialogMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void doWork() {
        String mWord = myWord;
        dbAdapter.deleteRow(mWord);
        viewAdapter.remove(arrayList.get(position));
        viewAdapter.notifyDataSetChanged();
    }
}




