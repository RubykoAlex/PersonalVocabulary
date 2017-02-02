package alex.personalvocabulary;

/**
 * Created by alex on 11.03.16.
 */
public class WordTransl {
    private String myWord, myTranslation;


    public WordTransl(String myWord, String myTranslation){
        this.setMyWord(myWord);
        this.setMyTranslation(myTranslation);



    }


    public String getMyWord() {
        return myWord;
    }

    public void setMyWord(String myWord) {
        this.myWord = myWord;
    }

    public String getMyTranslation() {
        return myTranslation;
    }

    public void setMyTranslation(String myTranslation) {
        this.myTranslation = myTranslation;
    }
}
