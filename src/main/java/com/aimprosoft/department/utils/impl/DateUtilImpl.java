package com.aimprosoft.department.utils.impl;

import com.aimprosoft.department.utils.DateUtil;
import org.springframework.stereotype.Component;

import java.text.DateFormatSymbols;
import java.util.*;

/**
 * Created by merovingien on 3/6/14.
 */
@Component("DateUtil")
public class DateUtilImpl implements DateUtil{
    @Override
    public List<String> getDayList() {
        List<String> dayList = new ArrayList<String>(31);
        for(Integer i = 1; i<= 31; i++) {
            dayList.add(i.toString());
        }
        return dayList;
    }

    @Override
    public Map<String, String> getMonthMap() {
        String[] months = new DateFormatSymbols().getMonths();
        Map<String,String> monthMap = new HashMap<String, String>(12);
        for(int i = 0; i < 12; i++) {
            monthMap.put(String.valueOf(1+i), months[i]);
        }
        return monthMap;
    }

    @Override
    public List<String> getYearList() {
        List<String> yearList = new ArrayList<String>();
        for(Integer i = 1930 ; i<= Calendar.getInstance().get(Calendar.YEAR); i++ ) {
            yearList.add(i.toString());
        }
        return yearList;
    }
}
