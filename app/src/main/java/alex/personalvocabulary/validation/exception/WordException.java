package alex.personalvocabulary.validation.exception;

import alex.personalvocabulary.validation.LocalValidatorException;

/**
 * Created by alex on 23.02.16.
 */
public class WordException extends LocalValidatorException {
    public WordException (String pDetailMessage){
        super(pDetailMessage);
    }
}