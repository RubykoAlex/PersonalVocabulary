package alex.personalvocabulary.validation.concrete;

import android.support.annotation.IdRes;

import alex.personalvocabulary.validation.LocalValidator;
import alex.personalvocabulary.validation.LocalValidatorException;
import alex.personalvocabulary.validation.exception.WordException;
import alex.personalvocabulary.validation.exception.KeyNotFoundException;
import alex.personalvocabulary.view.RubykoEditText;

/**
 * Created by alex on 21.02.16.
 */
public class WordValidator extends LocalValidator<String, RubykoEditText> {



    private final RubykoEditText mWordEdt;

    public WordValidator(RubykoEditText pWordEdt){
        this.mWordEdt = pWordEdt;
        mWordEdt.addValidator(this);
        vMap.put(mWordEdt.getId(), mWordEdt);
    }

    @Override
    public void update() {
        mWordEdt.update(false);
    }

    @Override
    public void svalidate() throws LocalValidatorException {
        final String email = mWordEdt.getText().toString();
        if(email.isEmpty()){
            throw new WordException("Please type a word you want to add");
        }
    }

    @Override
    protected String getData(@IdRes Integer pViewId) throws KeyNotFoundException {
        if(!vMap.containsKey(pViewId))
            throw new KeyNotFoundException();
        return vMap.get(pViewId).getText().toString();
    }

}
