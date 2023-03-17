package com.mmacedoaraujo.conversor.service;

import org.apache.commons.math3.util.Precision;

public class ConversorMoedasService {

    public static Double convert(String textFieldValue, Double bid) {
        double valueToBeConverted = Double.parseDouble(textFieldValue);
        return Precision.round(valueToBeConverted * bid, 2);
    }

}
