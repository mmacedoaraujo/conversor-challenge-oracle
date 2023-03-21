package com.mmacedoaraujo.conversor.util;

import java.util.ArrayList;
import java.util.List;

public class Constants {

    public static final String[] CURRENCIES_ABBREVIATIONS = {
            "BRL - Real Brasileiro",
            "USD - Dólar Americano",
            "EUR - Euro",
            "GBP - Libra Esterlina",
            "ARS - Peso Argentino",
            "CLP - Peso Chileno"
    };

    public static final String[] TEMPERATURE = {
            "ºC - Celsius",
            "ºF - Farenheit",
            "K - Kelvin"
    };

    public static final List<String> METRIC = new ArrayList<>(List.of(
            "KM - Quilômetro",
            "HM - Hectômetro",
            "DAM - Decâmetro",
            " M - Metro",
            "DM - Decímetro",
            "CM - Centímetro",
            "MM - Milímetro"));

    public static final String URL_API = "https://economia.awesomeapi.com.br/json/last/";
}
