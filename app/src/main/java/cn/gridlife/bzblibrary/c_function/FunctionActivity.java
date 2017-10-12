package cn.gridlife.bzblibrary.c_function;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.gridlife.bzblibrary.R;

/**
 * Created by BZB on 2017/10/9.
 */

public class FunctionActivity extends Activity {
    private EditText etOneEquationOne;
    private EditText etOneEquationNotation;
    private EditText etOneEquationTwo;
    private EditText etOneEquationResult;
    private LinearLayout llOne;
    private EditText etTwoEquationOne;
    private EditText etTwoEquationNotation;
    private EditText etTwoEquationTwo;
    private EditText etTwoEquationResult;
    private LinearLayout llTwo;
    private TextView tvA;
    private TextView tvB;
    private TextView tvC;
    private TextView tvD;
    private TextView tvE;
    private TextView tvF;
    private TextView tvG;
    private TextView tvH;
    private TextView tvP;
    private LinearLayout llResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_activity_function);

        calculate();
    }

    private void initView() {
        etOneEquationOne = (EditText) findViewById(R.id.et_one_equation_one);
        etOneEquationNotation = (EditText) findViewById(R.id.et_one_equation_notation);
        etOneEquationTwo = (EditText) findViewById(R.id.et_one_equation_two);
        etOneEquationResult = (EditText) findViewById(R.id.et_one_equation_result);
        llOne = (LinearLayout) findViewById(R.id.ll_one);
        etTwoEquationOne = (EditText) findViewById(R.id.et_two_equation_one);
        etTwoEquationNotation = (EditText) findViewById(R.id.et_two_equation_notation);
        etTwoEquationTwo = (EditText) findViewById(R.id.et_two_equation_two);
        etTwoEquationResult = (EditText) findViewById(R.id.et_two_equation_result);
        llTwo = (LinearLayout) findViewById(R.id.ll_two);
        tvA = (TextView) findViewById(R.id.tv_A);
        tvB = (TextView) findViewById(R.id.tv_B);
        tvC = (TextView) findViewById(R.id.tv_C);
        tvD = (TextView) findViewById(R.id.tv_D);
        tvE = (TextView) findViewById(R.id.tv_E);
        tvF = (TextView) findViewById(R.id.tv_F);
        tvG = (TextView) findViewById(R.id.tv_G);
        tvH = (TextView) findViewById(R.id.tv_H);
        tvP = (TextView) findViewById(R.id.tv_P);
        llResult = (LinearLayout) findViewById(R.id.ll_result);
    }
    public void calculate(){
        initView();
        for(int a=1;a<=9;a++){
            for(int b=0;b<=9;b++){
                for(int c=1;c<=9;c++){
                    for(int d=0;d<=9;d++){
                        for(int e=1;e<=9;e++){
                            for(int f=0;f<=9;f++){
                                for(int g=1;g<=9;g++){
                                    for(int h=0;h<=9;h++){
                                        for(int p=1;p<=9;p++){
                                            if(((a*10+b)-(c*10+d))==(e*10+f)&&(e*10+f)+(g*10+h)==p*111){
                                                tvA.setText("A = "+a);
                                                tvB.setText("B = "+b);
                                                tvC.setText("C = "+c);
                                                tvD.setText("D = "+d);
                                                tvE.setText("E = "+e);
                                                tvF.setText("F = "+f);
                                                tvG.setText("G = "+g);
                                                tvH.setText("H = "+h);
                                                tvP.setText("P = "+p);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
