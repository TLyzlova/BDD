package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import lombok.val;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    private final SelenideElement firstCard =
            $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] .button");
    private final SelenideElement secondCard =
            $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] .button");
    private final String balanceBegin = "баланс: ";
    private final String balanceEnd = " р.";


    public MoneyTransferPage firstCardRefill(){
        firstCard.click();
        return new MoneyTransferPage();
    }

    public MoneyTransferPage secondCardRefill(){
        secondCard.click();
        return new MoneyTransferPage();
    }

    public int getBalance(String currentBalance) {
        val theString = currentBalance.split(",");
        val arraysLength = theString[theString.length - 1];
        val value = arraysLength.replace("баланс:", "").replace("р.","").replace("Пополнить", "").strip();
        return Integer.parseInt(value);
    }


    public int getFirstCardBalance(){
        val currentBalance = $(".list__item [data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']").getText();
        return getBalance(currentBalance);

    }

    public int getSecondCardBalance(){
        val currentBalance = $(".list__item [data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']").getText();
        return getBalance(currentBalance);
    }
}
