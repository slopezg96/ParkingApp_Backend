package com.example.parqueadero.util;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {
	private Calendar calendarIngreso = Calendar.getInstance();
    private Calendar calendarSalida = Calendar.getInstance();
    private long milisegundosIngreso;
    private long milisegundosSalida;
    private long milisegundoDiferencia;
    public final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public DateHelper(String fechaIngreso, String fechaSalida) {
    	Date fechaIngresoDate = convertirStringADate(fechaIngreso);
    	Date fechaSalidaDate = convertirStringADate(fechaSalida);
    	
        calendarIngreso.setTime(fechaIngresoDate);
        calendarSalida.setTime(fechaSalidaDate);
        milisegundosIngreso = calendarIngreso.getTimeInMillis();
        milisegundosSalida = calendarSalida.getTimeInMillis();
        milisegundoDiferencia = milisegundosSalida - milisegundosIngreso;
    }

    public long diferenciaDias(){
        return milisegundoDiferencia / (24 * 60 * 60 * 1000);
    }

    public long diferenciaHoras(){
        return milisegundoDiferencia / (60 * 60 * 1000);
    }

    public static Date convertirStringADate(String fecha){
    	SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        try {
            return format.parse(fecha);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String convertirDateAString(Date fechaAConvertir){
        Format formato = new SimpleDateFormat(DATE_FORMAT);
        return formato.format(fechaAConvertir);
    }
}
