package com.mmacedoaraujo.conversor.service;

import org.apache.commons.math3.util.Precision;

public class ConversorTemperaturaService {

    public static Double convertCelsiusToFarenheit(Double celsiusValue) {
        return Precision.round((celsiusValue * 9 / 5) + 32, 2);
    }

    public static Double convertCelsiusToKelvin(Double celsiusValue) {
        return Precision.round(celsiusValue + 273.15, 2);
    }


    public static Double convertFarenheitToCelsius(Double farenheitValue) {
        return Precision.round((farenheitValue - 32) * 5 / 9, 2);
    }

    public static Double convertFarenheitToKelvin(Double farenheitValue) {
        return Precision.round((farenheitValue - 32) * 5 / 9 + 273.15, 2);
    }

    public static Double convertKelvinToCelsius(Double kelvinValue) {
        return Precision.round(kelvinValue - 273.15, 2);
    }

    public static Double convertKelvinToFarenheit(Double kelvinValue) {
        return Precision.round((kelvinValue - 273.15) * 9 / 5 + 32, 2);
    }

    public static Double executeCalculation(String formatoEntrada, String formatoSaida, Double valueToConvert) {
        String chosenOption = formatoEntrada + formatoSaida;
        Double result = null;
        switch (chosenOption) {
            case "ºCºF":
                result = convertCelsiusToFarenheit(valueToConvert);
                break;
            case "ºCK ":
                result = convertCelsiusToKelvin(valueToConvert);
                break;
            case "ºFºC":
                result = convertFarenheitToCelsius(valueToConvert);
                break;
            case "ºFK ":
                result = convertFarenheitToKelvin(valueToConvert);
                break;
            case "K ºC":
                result = convertKelvinToCelsius(valueToConvert);
                break;
            case "K ºF":
                result = convertKelvinToFarenheit(valueToConvert);
                break;
        }
        return result;
    }

}
