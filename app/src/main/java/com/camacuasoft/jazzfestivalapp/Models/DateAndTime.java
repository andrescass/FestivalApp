package com.camacuasoft.jazzfestivalapp.Models;

/**
 * Created by nosotros on 19/03/2017.
 */

public class DateAndTime {

    public static String GetMonth(int pos) {
        switch (pos) {
            case 0:
                return "Enero";
            case 1:
                return "Febrero";
            case 2:
                return "Marzo";
            case 3:
                return "Abril";
            case 4:
                return "Mayo";
            case 5:
                return "Junio";
            case 6:
                return "Julio";
            case 7:
                return "Agosto";
            case 8:
                return "Septiembre";
            case 9:
                return "Octubre";
            case 10:
                return "Noviembre";
            case 11:
                return "Diciembre";
            default:
                return "Enero";
        }
    }

    public static String GetMonthShort(int pos) {
        switch (pos) {
            case 0:
                return "Ene";
            case 1:
                return "Feb";
            case 2:
                return "Mar";
            case 3:
                return "Abr";
            case 4:
                return "May";
            case 5:
                return "Jun";
            case 6:
                return "Jul";
            case 7:
                return "Ago";
            case 8:
                return "Sep";
            case 9:
                return "Oct";
            case 10:
                return "Nov";
            case 11:
                return "Dic";
            default:
                return "Ene";
        }
    }

    public static String GetDay(int pos) {

        switch (pos) {
            case 0:
                return "Domingo";
            case 1:
                return "Lunes";
            case 2:
                return "Martes";
            case 3:
                return "Miércoles";
            case 4:
                return "Jueves";
            case 5:
                return "Viernes";
            case 6:
                return "Sábado";
            default:
                return "Lunes";
        }
    }

    public static String GetDayResume(int pos) {
        switch (pos) {
            case 0:
                return "Dom";
            case 1:
                return "Lun";
            case 2:
                return "Mar";
            case 3:
                return "Mie";
            case 4:
                return "Jue";
            case 5:
                return "Vie";
            case 6:
                return "Sab";
            default:
                return "Lun";
        }
    }

    public static String GetDayInit(int pos) {
        switch (pos) {
            case 0:
                return "D";
            case 1:
                return "L";
            case 2:
                return "M";
            case 3:
                return "X";
            case 4:
                return "J";
            case 5:
                return "V";
            case 6:
                return "S";
            default:
                return "L";
        }
    }
}
