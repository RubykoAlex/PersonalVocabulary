package alex.personalvocabulary.validation.exception;

import alex.personalvocabulary.validation.LocalValidatorException;

/**
 * Created by alex on 23.02.16.
 */
public class TranslationException extends LocalValidatorException {
    public TranslationException(String pDetailMessage){
        super(pDetailMessage);
    }
}
