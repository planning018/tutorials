package com.planning.concurrent.practise.accountDemo;

/**
 * Tom 和 Jack 两个人针对账户进行消费
 * @Author: planning
 * @Date: 2018/12/18 11:11
 */
public class TomAndJackJob implements Runnable{

    private BankAccount bk = new BankAccount();

    private void makeAndWithdrawal(int amount) {
        synchronized (this){
            if (bk.getAccount() - amount > 0) {
                System.out.println(Thread.currentThread().getName() + "要开始消费了");
                try {
                    System.out.println(Thread.currentThread().getName() + "有点困，先小睡一会儿");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "睡醒了");
                bk.withDraw(amount);
                System.out.println(Thread.currentThread().getName() + "消费完成");
            } else {
                System.out.println(Thread.currentThread().getName() + "，抱歉，余额不足了");
            }
        }
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            makeAndWithdrawal(10);
            if(bk.getAccount() < 0 ){
                System.out.println("账户没钱了");
            }
        }
    }


    public static void main(String[] args) {
        TomAndJackJob theJob = new TomAndJackJob();
        Thread tom = new Thread(theJob);
        Thread jack = new Thread(theJob);
        tom.setName("Tom");
        jack.setName("Jack");
        tom.start();
        jack.start();
    }
}