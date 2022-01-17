package com.planning.concurrent.practise.accountDemo;

/**
 * 银行账户
 * @Author: planning
 * @Date: 2018/12/18 11:05
 */
public class BankAccount {

    // 初始金额：100元
    private int account = 100;

    public void withDraw(int amount){
        account -= amount;
    }


    public int getAccount() {
        return account;
    }

}