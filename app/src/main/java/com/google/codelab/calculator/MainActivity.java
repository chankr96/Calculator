package com.google.codelab.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result_tv,solution_tv;
    MaterialButton openbrackets,closebrackets,c;
    MaterialButton divide,multiply,plus,minus,equal;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttondot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_tv = findViewById(R.id.result_tv);
        solution_tv= findViewById(R.id.solution_tv);

        assignID(c,R.id.button_c);
        assignID(openbrackets,R.id.button_openBracket);
        assignID(closebrackets,R.id.button_closeBracket);
        assignID(divide,R.id.button_divide);
        assignID(multiply,R.id.button_multiply);
        assignID(plus,R.id.button_plus);
        assignID(minus,R.id.button_minus);
        assignID(equal,R.id.button_equal);
        assignID(button0,R.id.button_0);
        assignID(button1,R.id.button_1);
        assignID(button2,R.id.button_2);
        assignID(button3,R.id.button_3);
        assignID(button4,R.id.button_4);
        assignID(button5,R.id.button_5);
        assignID(button6,R.id.button_6);
        assignID(button7,R.id.button_7);
        assignID(button8,R.id.button_8);
        assignID(button9,R.id.button_9);
        assignID(buttondot,R.id.button_dot);
        assignID(buttonAC,R.id.button_ac);




    }

    void assignID(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener((View.OnClickListener) this);
    }



    @Override
    public void onClick(View view) {
        MaterialButton button= (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataTocalculate= solution_tv.getText().toString();




        if(buttonText.equals("AC")){
            solution_tv.setText("");
            result_tv.setText("0");
            return;
        }

        if (buttonText.equals("=")){
            solution_tv.setText(result_tv.getText());
            return;
        }

        if (buttonText.equals("c")){
            dataTocalculate = dataTocalculate.substring(0,dataTocalculate.length()-1);
        }else {
            dataTocalculate = dataTocalculate+buttonText;
        }

        solution_tv.setText(dataTocalculate);

        String finalResult = getResult(dataTocalculate);

        if(!finalResult.equals("err")){
            result_tv.setText(finalResult);
        }
    }

    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"javascript",1,null).toString();
            if(finalResult.endsWith("0")){
                finalResult =finalResult.replace(".0","");
            }
            return finalResult;

        }catch (Exception e){
            return "Err";
        }
    }
}