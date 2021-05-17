package com.id20048076.l04_reservation;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button confirm;
    Button reset;
    EditText name;
    EditText mobileNumber;
    EditText sizeOfGroup;
    CheckBox smoking;
    DatePicker dp;
    TimePicker tp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        confirm = findViewById(R.id.buttonConfirm);
        reset = findViewById(R.id.buttonReset);
        name = findViewById(R.id.editTextTextPersonName);
        mobileNumber = findViewById(R.id.editTextNumberMobile);
        sizeOfGroup = findViewById(R.id.editTextNumberSizeOfGroup);
        smoking = findViewById(R.id.checkBoxSmoking);
        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg;
                if(name.getText().toString().matches("")||mobileNumber.getText().toString().matches("")||sizeOfGroup.getText().toString().matches("")){
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, "error", duration);
                    toast.show();
                    return;
                }
                msg = "ORDER DETAILS:";
                int year = dp.getYear();
                int month = dp.getMonth();
                int day = dp.getDayOfMonth();
                msg += "\nDate: " + year + "/" + month + "/" + day;
                int hour = tp.getCurrentHour();
                int min = tp.getCurrentMinute();
                msg += "\nTime: " + hour + ":" + String.format("%02d",min);
                //String.format("%02d",min)
                msg += "\nName: " + name.getText().toString();
                msg += "\nMobile Number: " + mobileNumber.getText().toString();
                msg += "\nSize Of Group: " + sizeOfGroup.getText().toString();
                if(smoking.isChecked()){
                    msg += "\nSmoking ";
                }else{
                    msg += "\nNo Smoking ";
                }
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, msg, duration);
                toast.show();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                mobileNumber.setText("");
                sizeOfGroup.setText("");
                tp.setCurrentHour(0);
                tp.setCurrentMinute(0);
                dp.updateDate (2020,
                        1,
                        1);
            }
        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                // limit time between 8AM and 8PM
                if(hourOfDay<8||hourOfDay>20){
                    tp.setCurrentHour(8);
                    tp.setCurrentMinute(0);
                }
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dp.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Calendar c = Calendar.getInstance();
                    int day = c.get(Calendar.DAY_OF_MONTH);
                    int month = c.get(Calendar.MONTH);
                    int year1 = c.get(Calendar.YEAR);
                    if(year1!=year||month!=monthOfYear||day!=dayOfMonth){
                        dp.updateDate (year1,
                                month,
                                day);
                    }
                }
            });
        }
    }
}