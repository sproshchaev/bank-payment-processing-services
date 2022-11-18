package com.prosoft.salespoint.util.shell;

import com.prosoft.salespoint.model.vo.PaymentValueObject;
import com.prosoft.salespoint.service.PaymentAuthorizationService;
import org.h2.tools.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.sql.SQLException;


@ShellComponent
public class AppEventsCommands {

    private final PaymentAuthorizationService paymentAuthorizationService;

    @Autowired
    public AppEventsCommands(PaymentAuthorizationService paymentAuthorizationService) {
        this.paymentAuthorizationService = paymentAuthorizationService;
    }

    @ShellMethod(value = "Start console H2", key = {"c", "console"})
    public void startConsoleH2() {
        try {
            Console.main();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @ShellMethod(value = "Make authorization", key = {"ma", "makeauthorization"})
    public String paymentAuthorizationService(@ShellOption(defaultValue = "000000001") String tid,
                                                          @ShellOption(defaultValue = "2022-10-26") String date,
                                                          @ShellOption(defaultValue = "4123450000000019") String card,
                                                          @ShellOption(defaultValue = "1225") String expdate,
                                                          @ShellOption(defaultValue = "500.55") String sum,
                                                          @ShellOption(defaultValue = "RUB") String curr) {
        PaymentValueObject result = paymentAuthorizationService.makeAuthorization(new PaymentValueObject(tid, date, card,
                expdate, sum, curr));
        return "\nРезультат: "
                + (result.getErrorCode().equals("00") ? " Завершено успешно, код авторизации " + result.getAuthorizationCode()
                : "errorCode " + result.getErrorCode() + " Отказ");
    }

    /** todo - получить список всех операций (за дату - по умолчанию за сегодня)
     *  list of operations - получить список всех операций (за дату - по умолчанию за сегодня)
     *  @return: "1) 4123***4724 500.55 RUB к.а.: 123456
     *            2) 3123***0000 155.95 RUB к.а.: 123457
     *            Итого: 656.5 RUB
     */

}
