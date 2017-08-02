package com.example.administrator.mytestallhere.testmaterial_calendarview;

import android.content.Context;

import com.example.administrator.mytestallhere.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Date;

/**
 * Created by Administrator on 2017/8/2 0002.
 */

public class TodayDector implements DayViewDecorator {
    Date toDay;
    Context context;
    public TodayDector(Context context){
        this.context=context;
    }

    @Override
    public boolean shouldDecorate(CalendarDay calendarDay) {
        return calendarDay.equals(CalendarDay.today());
    }

    @Override
    public void decorate(DayViewFacade dayViewFacade) {
        dayViewFacade.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.dector_today));
    }
}
