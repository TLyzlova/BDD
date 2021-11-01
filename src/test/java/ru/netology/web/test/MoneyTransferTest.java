package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {
    DashboardPage dashboardPage;

  @BeforeEach

  void setUp() {

      val loginPage = open("http://localhost:9999", LoginPage.class);
      val verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
      val verificationCode = DataHelper.getVerificationCode();
      dashboardPage = verificationPage.validVerify(verificationCode);

  }

    @Test

   void shouldTransferMoneyFromFirstToSecond (){

      val amount = 2000;
      val firstCardBalanceBefore = dashboardPage.getFirstCardBalance();
      val secondCardBalanceBefore = dashboardPage.getSecondCardBalance();
      val moneyTransferPage = dashboardPage.secondCardRefill();
      val cardInfo = DataHelper.getFirstCardInfo();
      moneyTransferPage.moneyTransferCard(cardInfo, amount);
      val firstCardBalanceProcess = dashboardPage.getFirstCardBalance();
      val secondCardBalanceProcess = dashboardPage.getSecondCardBalance();
      val firstCardBalanceAfter = DataHelper.getFirstCardBalanceAfter(firstCardBalanceBefore, amount);
      val secondCardBalanceAfter = DataHelper.getSecondCardBalanceAfter(secondCardBalanceBefore, amount);
      assertEquals(firstCardBalanceAfter, firstCardBalanceProcess);
      assertEquals(secondCardBalanceAfter, secondCardBalanceProcess);

    }

    @Test

    void shouldTransferMoneyFromSecondToFirst (){

        val amount = 3000;
        val firstCardBalanceBefore = dashboardPage.getFirstCardBalance();
        val secondCardBalanceBefore = dashboardPage.getSecondCardBalance();
        val moneyTransferPage = dashboardPage.firstCardRefill();
        val cardInfo = DataHelper.getSecondCardInfo();
        moneyTransferPage.moneyTransferCard(cardInfo, amount);
        val firstCardBalanceProcess = dashboardPage.getFirstCardBalance();
        val secondCardBalanceProcess = dashboardPage.getSecondCardBalance();
        val firstCardBalanceAfter = DataHelper.getFirstCardBalanceAfter(secondCardBalanceBefore, amount);
        val secondCardBalanceAfter = DataHelper.getSecondCardBalanceAfter(firstCardBalanceBefore, amount);
        assertEquals(firstCardBalanceAfter, secondCardBalanceProcess);
        assertEquals(secondCardBalanceAfter, firstCardBalanceProcess);

    }

    @Test

    void shouldTransferMoneyOverLimit (){
        val amount = 15_000;
        val moneyTransferPage = dashboardPage.firstCardRefill();
        val cardInfo = DataHelper.getSecondCardInfo();
        moneyTransferPage.moneyTransferCard(cardInfo, amount);
        moneyTransferPage.errorTransfer();

    }
}
