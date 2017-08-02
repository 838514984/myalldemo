package com.example.administrator.mytestallhere.testmaterial_calendarview;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.mytestallhere.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.format.WeekDayFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/2 0002.
 */

public class MyCalendarDialog {
    private AlertDialog dialog;
    private AlertDialog.Builder builder;
    private Context context;
    View contentView;
    @BindView(R.id.material_calendarview)
    MaterialCalendarView materialCalendarView;
    @BindView(R.id.tv_calendartime)
    TextView title;

    public MyCalendarDialog(Context context, DialogInterface.OnClickListener positive, DialogInterface.OnClickListener nagavie) {
        this.context = context;
        builder = new AlertDialog.Builder(context);
        builder.setPositiveButton("ok", positive);
        builder.setNegativeButton("cancle", nagavie);
        contentView = LayoutInflater.from(context).inflate(R.layout.layout_dialag_material_calendar, null);
        ButterKnife.bind(this, contentView);
        builder.setView(contentView);
        dialog=builder.create();

        title.setText(CalendarDay.today().getYear()+"年" + (CalendarDay.today().getMonth()+1)+"月" );

        materialCalendarView.setTopbarVisible(false);
        materialCalendarView.addDecorator(new TodayDector(context));
        materialCalendarView.invalidateDecorators();
        materialCalendarView.setSelectionColor(0xaa0000ff);

//        materialCalendarView.addDecorator(new DotDector());
//        materialCalendarView.invalidateDecorators();
        dynamicAddDot();

        materialCalendarView.addDecorator(new DisEnanleDector());
        materialCalendarView.invalidateDecorators();


    }

    private void dynamicAddDot() {
        Observable.timer(2000, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Calendar calendar=Calendar.getInstance();
                        ArrayList<CalendarDay> dates=new ArrayList<CalendarDay>();
                        for (int i=0;i<50;i++){
                            dates.add(CalendarDay.from(calendar));
                            calendar.add(Calendar.DAY_OF_MONTH,i%5);
                        }
                        materialCalendarView.addDecorator(new DotDector(dates));
                        materialCalendarView.invalidateDecorators();

                    }
                });


    }

    public void setOnDateChangedListener(OnDateSelectedListener onDateChangedListener){
        materialCalendarView.setOnDateChangedListener(onDateChangedListener);

    }

    public void setOnMyMonthChangedListener(final MyMonthChangeListener listener){

        materialCalendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView materialCalendarView, CalendarDay calendarDay) {

                    listener.onMonthChanged(materialCalendarView,calendarDay,title);

            }
        });
    }


    public void show(){
        dialog.show();
    }

    public void dismiss(){
        dialog.dismiss();
    }

    public interface MyMonthChangeListener{
        void onMonthChanged(MaterialCalendarView var1, CalendarDay var2,TextView tv);
    }

}
