package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.Client;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;

@Service
public class TransliterationServiceImpl implements TransliterationService {

    private final static String RUSSIAN_ALPHABET_UPPERCASE_LETTERS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"; // todo сформировать метод перевода
    private final static String TRANSLITERATION_UPPERCASE_LETTERS = "ABVGDEEZHZIIKLMNOPRSTUFKHTSCHSHSHCHIEYEIUIA"; // http://translit-online.ru/pasport.html
    //private final static HashMap<String, String> TRANSLITERATION; // todo заполнить HashMap

    @Override
    public String getTransliterationName(Client client) {
        return client.getFirstName().toUpperCase(Locale.ROOT) + " "
                + client.getMiddleName().substring(0, 1).toUpperCase(Locale.ROOT) + ". "
                + client.getLastName().toUpperCase(Locale.ROOT);
    }
}
