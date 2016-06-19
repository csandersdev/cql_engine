package org.cqframework.cql.elm.execution;

import org.cqframework.cql.execution.Context;
// import org.joda.time.format.DateTimeFormat;
// import org.joda.time.format.DateTimeFormatter;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Created by Bryn on 5/25/2016.
 * Edited by Chris Schuler on 6/18/2016
 */
public class DateTimeEvaluator extends DateTime {

  @Override
  public Object evaluate(Context context) {
    Integer year = (Integer)this.getYear().evaluate(context);
    if (((year - 1000) < 0) && year != null) {
      throw new IllegalArgumentException("Must use 4 digits for year.");
    }
    Integer month = (this.getMonth() == null) ? null : (Integer)this.getMonth().evaluate(context);
    Integer day = (this.getDay() == null) ? null : (Integer)this.getDay().evaluate(context);
    Integer hour = (this.getHour() == null) ? null : (Integer)this.getHour().evaluate(context);
    Integer minute = (this.getMinute() == null) ? null : (Integer)this.getMinute().evaluate(context);
    Integer second = (this.getSecond() == null) ? null : (Integer)this.getSecond().evaluate(context);
    Integer millisecond = (this.getMillisecond() == null) ? null : (Integer)this.getMillisecond().evaluate(context);
    BigDecimal timezoneOffset = (this.getTimezoneOffset() == null) ? null :  (BigDecimal)this.getTimezoneOffset().evaluate(context);
    if (org.cqframework.cql.runtime.DateTime.formatCheck(new ArrayList<Object>(Arrays.asList(year, month, day, hour, minute, second, millisecond, timezoneOffset)))) {
      return new org.cqframework.cql.runtime.DateTime().withYear(year).withMonth(month).withDay(day).withHour(hour).withMinute(minute).withSecond(second).withMillisecond(millisecond).withTimezoneOffset(timezoneOffset);
    }
    else {
      throw new IllegalArgumentException("DateTime format is invalid");
    }
  }

    // TODO: Fix this, it should be using a Partial, not a DateTime
//    @Override
//    public Object evaluate(Context context) {
//        Expression field = null;
//        String year = (field = (Expression) this.getYear()) == null ? null : ((Integer) field.evaluate(context)).toString();
//        if (year == null) return null;
//        if (year.length() < 4) {
//            throw new IllegalArgumentException("Must use 4 digits for year.");
//        }
//
//        String month = (field = (Expression) this.getMonth()) == null ? null : ((Integer) field.evaluate(context)).toString();
//        String day = (field = (Expression) this.getDay()) == null ? null : ((Integer) field.evaluate(context)).toString();
//        String hour = (field = (Expression) this.getHour()) == null ? null : ((Integer) field.evaluate(context)).toString();
//        String minute = (field = (Expression) this.getMinute()) == null ? null : ((Integer) field.evaluate(context)).toString();
//        String second = (field = (Expression) this.getSecond()) == null ? null : ((Integer) field.evaluate(context)).toString();
//        String milliSecond = (field = (Expression) this.getMillisecond()) == null ? null : ((Integer) field.evaluate(context)).toString();
//        String tzOffset = (field = (Expression) this.getTimezoneOffset()) == null ? null : ((Integer) field.evaluate(context)).toString();
//
//        StringBuffer timeBuffer = new StringBuffer(year);
//        if (month != null) {
//            timeBuffer.append("-").append(String.format("%0"+ (2 - month.length() )+"d%s",0 ,month));
//        }
//
//        if (day != null) {
//            timeBuffer.append("-").append(String.format("%0"+ (2 - day.length() )+"d%s",0 ,day));
//        }
//
//        if (hour != null) {
//            timeBuffer.append("T").append(hour);
//        }
//
//        if (minute != null) {
//            timeBuffer.append(":").append(minute);
//        }
//
//        if (second != null) {
//            timeBuffer.append(":").append(second);
//        }
//
//        if (milliSecond != null) {
//            timeBuffer.append(".").append(milliSecond);
//        }
//
//        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
//        org.joda.time.DateTime newDate = formatter.parseDateTime(timeBuffer.toString());
//
//        return newDate.toDate();
//    }
}
