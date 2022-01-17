package com.planning.concurrent.notify;

public class SetThread implements Runnable{

	private Student s;
	
	public SetThread(Student s){
		this.s = s;
	}
	
	private int x = 0;
	
	@Override
	public void run() {
		while(true){
			synchronized(s){
				if(s.flag){
					try {
						s.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if(x % 2 == 0){
					s.name = "zhangsan";
					s.age = 24;
				}else{
					s.name = "lisi";
					s.age = 23;
				}
				x++;
				
				//修改值
				s.flag = true;
				s.notify();
			}
		}
	}
}
