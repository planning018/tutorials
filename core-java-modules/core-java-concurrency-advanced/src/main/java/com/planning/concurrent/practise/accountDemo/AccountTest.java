package com.planning.concurrent.practise.accountDemo;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author planning
 * @create 2019-10-28 15:53
 **/
public class AccountTest {

    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(4, 4, 1000,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), new ThreadFactoryBuilder().setNameFormat("Accout-task-thread-pool-%d").build());

    /**
     * 采用 细粒度锁 这种方式 password 的值会存在并发问题
     * todo 是使用方式不对吗？留待后续观察?
     */
    @Test
    public void testAccount() {
        //Account a = new Account();
        Account2 a = new Account2();
        a.initBalance();

        for (int i = 0; i < 5; i++) {
            EXECUTOR.submit(() -> {
                a.withdraw(100);
                a.updatePassword("123");
            });

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            EXECUTOR.submit(() -> {
                a.withdraw(100);
                a.updatePassword("234");
            });
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end balance result is : " + a.getBalance());
        System.out.println("end balance password is : " + a.getPassword());
    }


    class Account {

        /**
         * 锁：保护账户余额
         */
        private final Object balLock = new Object();

        /**
         * 账户余额
         */
        private Integer balance;

        /**
         * 锁： 保护账户密码
         */
        private final Object pwLock = new Object();

        /**
         * 账户密码
         */
        private String password;

        /**
         * 取款
         *
         * @param amt
         */
        void withdraw(Integer amt) {
            synchronized (balLock) {
                if (this.balance > amt) {
                    this.balance -= amt;
                }
            }
        }

        void initBalance() {
            synchronized (balLock) {
                this.balance = 500;
            }
        }

        /**
         * 查看余额
         *
         * @return
         */
        Integer getBalance() {
            synchronized (balLock) {
                return this.balance;
            }
        }

        void updatePassword(String pw) {
            synchronized (pwLock) {
                this.password = pw;
            }
        }

        String getPassword() {
            synchronized (pwLock) {
                return password;
            }
        }
    }

    class Account2 {

        /**
         * 账户余额
         */
        private Integer balance;

        /**
         * 账户密码
         */
        private String password;

        /**
         * 取款
         *
         * @param amt
         */
        void withdraw(Integer amt) {
            Thread t = Thread.currentThread();
            synchronized (Account2.class) {
                if (this.balance > amt) {
                    this.balance -= amt;
                }
            }
            System.out.println(t.getName() + ">>> balance: " + balance);
        }

        void initBalance() {
            synchronized (Account2.class) {
                this.balance = 5000;
            }
        }

        /**
         * 查看余额
         *
         * @return
         */
        Integer getBalance() {
            synchronized (Account2.class) {
                return this.balance;
            }
        }

        void updatePassword(String pw) {
            Thread t = Thread.currentThread();
            synchronized (Account2.class) {
                this.password = pw;
            }
            System.out.println(t.getName() + ">>> password: " + password);
        }

        String getPassword() {
            synchronized (Account2.class) {
                return password;
            }
        }
    }
}

