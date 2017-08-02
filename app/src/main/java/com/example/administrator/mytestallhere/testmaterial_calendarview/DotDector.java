package com.example.administrator.mytestallhere.testmaterial_calendarview;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/2 0002.
 */

public class DotDector implements DayViewDecorator{
    ArrayList<CalendarDay> dates;
    public DotDector(ArrayList<CalendarDay> dates){
        this.dates=dates;
    }

    @Override
    public boolean shouldDecorate(CalendarDay calendarDay) {
        return dates.contains(calendarDay);
    }

    @Override
    public void decorate(DayViewFacade dayViewFacade) {
        dayViewFacade.addSpan(new DotSpan(6,0xffff0000));
    }
}
