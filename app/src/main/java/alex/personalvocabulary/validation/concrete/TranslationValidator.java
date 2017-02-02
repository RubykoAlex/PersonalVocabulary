package alex.personalvocabulary.validation.concrete;

import android.support.annotation.IdRes;

import alex.personalvocabulary.validation.LocalValidator;
import alex.personalvocabulary.validation.LocalValidatorException;
import alex.personalvocabulary.validation.exception.KeyNotFoundException;
import alex.personalvocabulary.validation.exception.TranslationException;
import alex.personalvocabulary.view.RubykoEditText;

/**
 * Created by alex on 21.02.16.
 */
public class TranslationValidator extends LocalValidator<String, RubykoEditText> {

    private final RubykoEditText mTranslationEdt;

    public TranslationValidator(RubykoEditText pPasswordEdt){
        this.mTranslationEdt = pPasswordEdt;
        mTranslationEdt.addValidator(this);
        vMap.put(mTranslationEdt.getId(), mTranslationEdt);
    }

    @Override
    public void update() {
        mTranslationEdt.update(false);
    }

    @Override
    public void svalidate() throws LocalValidatorException {
        final String passwrod = mTranslationEdt.getText().toString();

        if (!passwrod.isEmpty() && passwrod.length() <= MIN_TRANSLATION_LENGTH){
            throw new TranslationException("Must be more then " + MIN_TRANSLATION_LENGTH + " letters");
        }
        if( passwrod.isEmpty()){
            throw new TranslationException("Please type the translation");
        }
    }

    protected String getData(@IdRes Integer pViewId) throws KeyNotFoundException {
        if(!vMap.containsKey(pViewId))
            throw new KeyNotFoundException();
        return vMap.get(pViewId).getText().toString();
    }

}


