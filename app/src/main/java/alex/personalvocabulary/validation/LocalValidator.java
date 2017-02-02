package alex.personalvocabulary.validation;

import android.support.annotation.IdRes;
import android.view.View;

import alex.personalvocabulary.validation.exception.KeyNotFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 18.02.16.
 */
public abstract class LocalValidator<T, V extends View> {

    private LocalValidator<T, V> localValidator;
    protected final Map<Integer, V> vMap = new HashMap<>();

    protected boolean isValid;

    protected static final int MIN_WORD_LENGTH = 1;
    protected static final int MIN_TRANSLATION_LENGTH = 1;

    protected abstract void svalidate() throws LocalValidatorException;

    public void validate() throws LocalValidatorException {
        try{
            this.svalidate();
        } catch (LocalValidatorException e){
            isValid = false;
            throw e;
        }
        isValid = true;
    }

    public boolean isValid() {
        if(localValidator!=null)
            return isValid && localValidator.isValid();
        else
            return isValid;
    }

    public LocalValidator<T, V> and(LocalValidator<T, V> localValidator) {
        localValidator.localValidator = this;
        return localValidator;
    }

    protected abstract void update();

    public final void updateAll(){
        this.update();
        if(localValidator!=null)
            localValidator.updateAll();
    }

    protected abstract T getData(@IdRes Integer pViewId) throws KeyNotFoundException;

    public final T getDataAll(@IdRes int pViewId){
        try {
            final T data = getData(pViewId);
            return data;
        } catch (KeyNotFoundException e){
            return localValidator.getDataAll(pViewId);
        }
    }

}