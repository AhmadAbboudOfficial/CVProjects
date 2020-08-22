package com.alyndroid.architecturepatternstutorialshomework.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alyndroid.architecturepatternstutorialshomework.pojo.NumberModel;

public class MulViewModel extends ViewModel {

    MutableLiveData<Integer> ResultMutableLiveData = new MutableLiveData<>();

    //first get data from database
    private NumberModel getNumbers(){
        return new NumberModel(4,2);
    }

    //second get multiplication result and saved in a mutable live data
    public void getResult(){
        int result = getNumbers().getFirstNum() * getNumbers().getSecondNum();
        ResultMutableLiveData.setValue(result);
    }

}
