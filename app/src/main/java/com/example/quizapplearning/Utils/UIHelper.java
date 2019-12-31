package com.example.quizapplearning.Utils;

import android.content.Context;
import android.widget.Toast;

public class UIHelper {
    public static UIHelper instance;

    public static UIHelper get(){
        if(instance == null){
            instance = new UIHelper();
        }
        return instance;
    }
    public void showMessage(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
