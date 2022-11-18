package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountNumGeneratorServiceImpl implements AccountNumGeneratorService {
    private final static String BALANCE_ACCOUNT = "40817";
    private final static String DIVISION_CODE = "0000"; // todo код подразделения банка из настроек
    private final static String INTERNAL_PERSONAL_ACCOUNT_NUMBER = "000000";
    private final static String WEIGHTING_COEFFICIENTS = "71371371371371371371371";
    private final BankSettingService bankSettingService;

    @Autowired
    public AccountNumGeneratorServiceImpl(BankSettingService bankSettingService) {
        this.bankSettingService = bankSettingService;
    }

    @Override
    public String genAccountNumber(long accountId, Currency currency) {
        String bic = bankSettingService.getBySetting("bic").get().getCurrentValue();
        String controlKey = "0";
        String accountNumber = BALANCE_ACCOUNT + currency.getCurrencyDigitalCodeAccount() + controlKey + DIVISION_CODE
                + INTERNAL_PERSONAL_ACCOUNT_NUMBER.substring(String.valueOf(accountId).length() - 1) + accountId;
        String bicAndAccount = bic.substring(bic.length() - 3) + accountNumber;
        int checkSum = 0, count = 0;
        for (char ch : bicAndAccount.toCharArray()) {
            checkSum = checkSum + Character.getNumericValue(ch)
                    * Integer.parseInt(WEIGHTING_COEFFICIENTS.substring(count, count + 1));
            count++;
        }
        String controlNumber = String.valueOf((checkSum % 10) * 3);
        controlKey = controlNumber.substring(controlNumber.length() - 1);
        return accountNumber.substring(0, 8) + controlKey + accountNumber.substring(9);
    }

}
