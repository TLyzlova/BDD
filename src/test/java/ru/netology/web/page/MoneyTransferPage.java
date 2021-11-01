package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MoneyTransferPage {

    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement refillButton = $("[data-test-id=action-transfer");
    private SelenideElement errorNotification = $("[data-test-id='error-notification']");

    public DashboardPage moneyTransferCard(DataHelper.CardInfo CardInfo, int amount){
        amountField.setValue(String.valueOf(amount));
        fromField.setValue(CardInfo.getCardNumber());
        refillButton.click();
        return new DashboardPage();

    }

    public void errorTransfer() {
        errorNotification.shouldBe(visible).shouldHave(text("Ошибка!"));
    }
}
