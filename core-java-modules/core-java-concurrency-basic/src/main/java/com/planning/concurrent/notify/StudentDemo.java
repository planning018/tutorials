package com.planning.concurrent.notify;

/**
 * 本例讲述了多线程的等待唤醒机制
 *    notify()
 *    wait()
 *    这两个方法的调用应该通过锁对象去使用
 */
public class StudentDemo {

	public static void main(String[] args) {
		Student s = new Student();
		
		SetThread st = new SetThread(s);
		GetThread gt = new GetThread(s);
		
		Thread thread1 = new Thread(st);
		Thread thread2 = new Thread(gt);
		
		thread1.start();
		thread2.start();
	}
}
