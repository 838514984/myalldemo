package com.example.administrator.mytestallhere.time_data_orotherpicker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.administrator.mytestallhere.R;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestPickActivity extends AppCompatActivity {
    @BindView(R.id.np)
    NumberPicker numberPicker;
    @BindView(R.id.dp)
    DatePicker datePicker;
    @BindView(R.id.tp)
    TimePicker timePicker;
    @BindView(R.id.btn_1)
    Button btn_timedialog;
    @BindView(R.id.btn_2)
    Button btn_datedialog;
    Date date;
    Calendar calendar=Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_pick);
        ButterKnife.bind(this);
        date=new Date();
        initNumberPicker();
        initTimePicker();
    }


    @OnClick(R.id.btn_1)
    public void showDatePickerDialog(){

        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Toast.makeText(TestPickActivity.this,"year: "+year+"\nmonth: "+month+1+"\ndayofmonth: "+dayOfMonth,0).show();
            }
        }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    @OnClick(R.id.btn_2)
    public void showTimePickerDialog(){
        TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(TestPickActivity.this,"hourOfDay: "+hourOfDay+"\nminute: "+minute,0).show();
            }
        },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true);
        timePickerDialog.show();
    }









    private void initDatePicker() {

    }

    private void initTimePicker() {
        timePicker.setIs24HourView(true);
    }

    private void initNumberPicker() {


        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker.setDisplayedValues(new String[]{"嘻嘻","haha ","lla","2332","treasure"});
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(4);

    }


}
