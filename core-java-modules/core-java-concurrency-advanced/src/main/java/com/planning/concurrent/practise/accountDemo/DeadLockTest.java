package com.planning.concurrent.practise.accountDemo;

import org.junit.Test;

/**
 * 05 死锁
 *
 * @author yxc
 * @since 2020-05-06 18:16
 **/
public class DeadLockTest {

    @Test
    public void testAmountNumber(){
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                doTransfer();
            }).start();
        }
    }

    private void doTransfer() {
        Account a = new Account();
        Account b = new Account();

        a.transfer(b, 100);

        System.out.println("A balance is " + a.balance);
        System.out.println("B balance is " + b.balance);

    }

    class Account {
        private int balance = 500;

        void transfer(Account target, int amt) {
            // 锁定转出账户
            synchronized (this) {
                // 锁定转入账户
                synchronized (target) {
                    if (this.balance > amt) {
                        this.balance -= amt;
                        target.balance += amt;
                    }
                }
            }
        }
    }
}