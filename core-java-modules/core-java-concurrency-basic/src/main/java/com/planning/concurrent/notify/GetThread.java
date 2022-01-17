package com.planning.concurrent.notify;

public class GetThread implements Runnable {

	private Student s;

	public GetThread(Student s) {
		this.s = s;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (s) {
				if (!s.flag) {
					try {
						s.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(s.name + "---" + s.age);
				
				//修改标记
				s.flag = false;
				s.notify();  //唤醒其他的线程
			}
		}

	}
}
