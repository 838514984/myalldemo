package com.example.administrator.mytestallhere.testmaterial_calendarview;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.mytestallhere.BaseActivity;
import com.example.administrator.mytestallhere.R;
import com.example.administrator.mytestallhere.util.Logger;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MaterialCalendarViewActivity extends BaseActivity implements OnDateSelectedListener, MyCalendarDialog.MyMonthChangeListener {
    @BindView(R.id.btn)
    Button btn;
    MyCalendarDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_calendar_view);
        ButterKnife.bind(this);
        dialog = new MyCalendarDialog(this);
        dialog.setOnDateChangedListener(this);
        dialog.setOnMyMonthChangedListener(this);

        dialog.setPositiveBtnListener(new MyCalendarDialog.PositiveBtnListener() {
            @Override
            public void onClick(Dialog dialog) {
                dialog.dismiss();
            }
        });

        dialog.setNegativeListener(new MyCalendarDialog.NegativeListener() {
            @Override
            public void onClick(Dialog dialog) {
                dialog.dismiss();
            }
        });


    }

    @OnClick(R.id.btn)
    public void onBtnCLick() {
        dialog.show();
    }


    @Override
    public void onDateSelected(@NonNull MaterialCalendarView materialCalendarView, @NonNull CalendarDay calendarDay, boolean b) {
        Logger.Erroe("onDateSelected: "+calendarDay.getYear()+"---"+(calendarDay.getMonth()+1)+"----"+calendarDay.getDay()+"-----boolean--"+b);
    }

    @Override
    public void onMonthChanged(MaterialCalendarView materialCalendarView, CalendarDay calendarDay, TextView textView) {
        Logger.Erroe("onMonthChanged: "+calendarDay.getYear()+"---"+(calendarDay.getMonth()+1)+"----"+calendarDay.getDay());
        textView.setText(calendarDay.getYear()+"年" + (calendarDay.getMonth()+1)+"月" );
    }


}
