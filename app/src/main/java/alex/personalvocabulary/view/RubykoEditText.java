package alex.personalvocabulary.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;



import java.util.ArrayList;
import java.util.List;

import alex.personalvocabulary.validation.LocalValidator;
import alex.personalvocabulary.validation.LocalValidatorException;

/**
 * Created by alex on 20.02.16.
 */
public class RubykoEditText extends com.rey.material.widget.EditText implements TextWatcher {

    private static final int MESSAGE_ID = 0;
    private static final int ERROR_DELAY = 500;

    {
        mInputView.addTextChangedListener(this);
    }

    private ValidateHandler mHandler = new ValidateHandler();

    public RubykoEditText(Context context) {
        super(context);
    }

    public RubykoEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RubykoEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private List<LocalValidator> localValidators = new ArrayList<>();

    public void addValidator(LocalValidator localValidator){
        localValidators.add(localValidator);
    }

    public void update(boolean isDelayed){
        mHandler.removeMessages(MESSAGE_ID);
        try {
            for (LocalValidator localValidator : localValidators){
                localValidator.validate();
            }
            clearError();
        } catch (LocalValidatorException e){
            final Message message = new Message();
            message.arg1 = MESSAGE_ID;
            message.obj = e;
            int delay =  isDelayed ? ERROR_DELAY : 0;
            mHandler.sendMessageDelayed(message, delay);
        }
    }

    private class ValidateHandler extends  Handler {

        @Override
        public void handleMessage(Message msg) {
            if(msg.arg1 == MESSAGE_ID){
                String message = ((LocalValidatorException)msg.obj).getMessage();
                setError(message);
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        this.update(true);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
