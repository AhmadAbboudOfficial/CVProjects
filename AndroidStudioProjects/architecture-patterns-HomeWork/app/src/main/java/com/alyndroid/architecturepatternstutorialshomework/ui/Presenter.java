package com.alyndroid.architecturepatternstutorialshomework.ui;

import com.alyndroid.architecturepatternstutorialshomework.pojo.DataBase;
import com.alyndroid.architecturepatternstutorialshomework.pojo.NumberModel;

public class Presenter {

    private IDiv iDiv;
    DataBase db = new DataBase();

    public Presenter(IDiv iDiv) {
        this.iDiv = iDiv;
    }

    public NumberModel getNumberModel() {
        return db.getNumbers();
    }

    public void getDiv(){
        iDiv.onGetDiv(getNumberModel().getFirstNum() / getNumberModel().getSecondNum());
    }
}
