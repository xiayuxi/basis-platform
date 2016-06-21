package com.ync365.seed.dto.user;

public class UserBankInfoDTO {
    private Integer id;

    private String userNum;

    private String bankName;

    private String openingBankAddress;

    private String bankAccount;

    private String accountName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum == null ? null : userNum.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getOpeningBankAddress() {
        return openingBankAddress;
    }

    public void setOpeningBankAddress(String openingBankAddress) {
        this.openingBankAddress = openingBankAddress == null ? null : openingBankAddress.trim();
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }
}
