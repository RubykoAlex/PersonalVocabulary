package alex.personalvocabulary.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by alex on 22.03.16.
 */
public class RandomGenerator  {

    private int min;
    private int max;
    LinkedList<Integer> mylist = new LinkedList<>();


    public RandomGenerator(int minVal, int maxVal){
        this.setMin(minVal);
        this.setMax(maxVal);
    }


    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void getRandomNumbers()

    {
        int col = max - min;
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = min; i < col+min + 1; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
               mylist = list;

        }


    public LinkedList<Integer> getMylistNumber(){
        getRandomNumbers();
        return mylist;
    }
}
