package alex.personalvocabulary.adapter;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by alex on 04.03.16.
 */
public class Message {
    public static  void getMessage(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
