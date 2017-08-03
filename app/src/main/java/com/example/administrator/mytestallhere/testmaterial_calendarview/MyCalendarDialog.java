package com.example.administrator.mytestallhere.testmaterial_calendarview;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.mytestallhere.R;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
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
    private PositiveBtnListener positiveBtnListener;
    private NegativeListener negativeListener;
    View contentView;
    @BindView(R.id.material_calendarview)
    MaterialCalendarView materialCalendarView;
    @BindView(R.id.tv_calendartime)
    TextView title;
    @BindView(R.id.btn_negative)
    Button negative;
    @BindView(R.id.btn_positive)
    Button positive;
    @BindView(R.id.view)
    View divideView;
    @BindView(R.id.line)
    LinearLayout divideLine;

    public interface PositiveBtnListener{
        void  onClick(Dialog dialog);
    }
    public interface NegativeListener{
        void onClick(Dialog dialog);
    }

    public MyCalendarDialog(Context context) {
        this.context = context;
        builder = new AlertDialog.Builder(context);
        contentView = LayoutInflater.from(context).inflate(R.layout.layout_dialag_material_calendar, null);
        ButterKnife.bind(this, contentView);
        builder.setView(contentView);
        dialog=builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0x0));

        title.setText(CalendarDay.today().getYear()+"年" + (CalendarDay.today().getMonth()+1)+"月" );
        RxView.clicks(negative).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (negativeListener!=null)
                    negativeListener.onClick(dialog);
            }
        });

        RxView.clicks(positive).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (positiveBtnListener!=null)
                    positiveBtnListener.onClick(dialog);
            }
        });


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

    public void setPositiveBtnListener(PositiveBtnListener positiveBtnListener){
        this.positiveBtnListener=positiveBtnListener;
    }
    public void setNegativeListener(NegativeListener negativeListener){
        this.negativeListener=negativeListener;
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
//        if (negativeListener!=null)
//            negative.setVisibility(View.VISIBLE);
//        if (positiveBtnListener!=null)
//            positive.setVisibility(View.VISIBLE);
//        if (!(negativeListener==null && positiveBtnListener==null)) {
//            divideView.setVisibility(View.VISIBLE);
//            divideLine.setVisibility(View.VISIBLE);
//        }

        dialog.show();
    }

    public void dismiss(){
        dialog.dismiss();
    }

    public interface MyMonthChangeListener{
        void onMonthChanged(MaterialCalendarView var1, CalendarDay var2,TextView tv);
    }





}
