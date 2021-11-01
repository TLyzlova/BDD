package ru.netology.web.data;

import lombok.Value;

public class DataHelper {

    private DataHelper(){}

    @Value
    public static class AuthInfo{
        private String login;
        private String password;
    }
    public static AuthInfo getAuthInfo(){
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode{
        private String code;
    }

    public static VerificationCode getVerificationCode(){
        return new VerificationCode("12345");
    }

    @Value
    public static class CardInfo {
        private String cardNumber;
        private String cardBalance;
    }

    public static CardInfo getFirstCardInfo(){
        return new CardInfo("5559_0000_0000_0001", "10_000");
    }

    public static CardInfo getSecondCardInfo(){
        return new CardInfo("5559_0000_0000_0002", "10_000");
    }

    public static int getFirstCardBalanceAfter (int balance, int amount){
        int newBalance = balance - amount;
        return newBalance;
    }

    public static int getSecondCardBalanceAfter (int balance, int amount){
        int newBalance = balance + amount;
        return newBalance;
    }
}
