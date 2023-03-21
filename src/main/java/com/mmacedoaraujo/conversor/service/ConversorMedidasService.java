package com.mmacedoaraujo.conversor.service;

import java.math.BigDecimal;
import java.util.List;

public class ConversorMedidasService {

    public static BigDecimal getPosition(List<String> listOfValues, String firstValue, String secondValue, String valueToConvert) {
        int firstElementIndex = listOfValues.indexOf(firstValue);
        int secondElementIndex = listOfValues.indexOf(secondValue);
        int differenceBetweenIndexes = 0;

        differenceBetweenIndexes = firstElementIndex - secondElementIndex;
        double result = 0;

        if (differenceBetweenIndexes < 0) {
            int positiveNumber = differenceBetweenIndexes * -1;
            double divisionValue = Math.pow(10, positiveNumber);
            result = Double.parseDouble(valueToConvert) * divisionValue;
        } else if (differenceBetweenIndexes > 0) {
            double divisionValue = Math.pow(10, differenceBetweenIndexes);
            result = Double.parseDouble(valueToConvert) / divisionValue;
        }


        return BigDecimal.valueOf(result);
    }
}
