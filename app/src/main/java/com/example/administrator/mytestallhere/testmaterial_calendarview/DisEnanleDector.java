package com.example.administrator.mytestallhere.testmaterial_calendarview;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

/**
 * Created by Administrator on 2017/8/2 0002.
 */

public class DisEnanleDector implements DayViewDecorator {
    @Override
    public boolean shouldDecorate(CalendarDay calendarDay) {

        return calendarDay.getDay()%3==0;
    }

    @Override
    public void decorate(DayViewFacade dayViewFacade) {
        dayViewFacade.setDaysDisabled(true);
    }
}
