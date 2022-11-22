package com.fielamigo.app.FielAmigo.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static String getDateString(Date date) {
        System.out.println(date.toString());
        return date.toString();
    }

    public static int getAge(Date date) {
        Calendar dob = Calendar.getInstance();
        dob.setTime(date);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        return age;
    }

    public static String getSpanishDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        String monthString = "";
        switch(month) {
            case 0:
                monthString = "Enero";
                break;
            case 1:
                monthString = "Febrero";
                break;
            case 2:
                monthString = "Marzo";
                break;
            case 3:
                monthString = "Abril";
                break;
            case 4:
                monthString = "Mayo";
                break;
            case 5:
                monthString = "Junio";
                break;
            case 6:
                monthString = "Julio";
                break;
            case 7:
                monthString = "Agosto";
                break;
            case 8:
                monthString = "Septiembre";
                break;
            case 9:
                monthString = "Octubre";
                break;
            case 10:
                monthString = "Noviembre";
                break;
            case 11:
                monthString = "Diciembre";
                break;
            default:
                monthString = "Error";
        }
        return monthString + " " + cal.get(Calendar.DAY_OF_MONTH) + ", " + cal.get(Calendar.YEAR);
    }

    public static int getNights(Date checkIn, Date checkOut) {
        long diff = checkOut.getTime() - checkIn.getTime();
        return (int) (diff / (1000 * 60 * 60 * 24));
    }

    public static String getSpanishDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        switch(dayOfWeek) {
            case 1:
                return "Domingo";
            case 2:
                return "Lunes";
            case 3:
                return "Martes";
            case 4:
                return "Miércoles";
            case 5:
                return "Jueves";
            case 6:
                return "Viernes";
            case 7:
                return "Sábado";
            default:
                return "Error";
        }
    }
}
