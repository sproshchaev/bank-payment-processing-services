package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.Client;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransliterationServiceImpl implements TransliterationService {
    private final static Character[] RUSSIAN_ALPHABET_UPPERCASE_LETTERS = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З',
            'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь',
            'Э', 'Ю', 'Я', ' ', '.', '-'};
    private final static String[] TRANSLITERATION_UPPERCASE_LETTERS = {"A", "B", "V", "G", "D", "E", "E", "ZH", "Z",
            "I", "I", "K", "L", "M", "N", "O", "P", "R", "S", "T", "U", "F", "KH", "TS", "CH", "SH", "SHCH", "", "Y",
            "", "E", "IU", "IA", " ", ".", "-"};
    private final Map<Character, String> transliterationRusToEng;

    public TransliterationServiceImpl() {
        transliterationRusToEng = new HashMap<>();
        int countChar = 0;
        for (char rusLetter : RUSSIAN_ALPHABET_UPPERCASE_LETTERS) {
            transliterationRusToEng.put(rusLetter, TRANSLITERATION_UPPERCASE_LETTERS[countChar]);
            countChar++;
        }
    }

    @Override
    public String getTransliterationName(Client client) {
        return getFullName(client).chars().mapToObj(c -> transliterationRusToEng.get((char) c))
                .collect(Collectors.joining());
    }

    private String getFullName(Client client) {
        return client.getFirstName().toUpperCase(Locale.ROOT) + " "
                + client.getMiddleName().substring(0, 1).toUpperCase(Locale.ROOT) + ". "
                + client.getLastName().toUpperCase(Locale.ROOT);
    }
}
