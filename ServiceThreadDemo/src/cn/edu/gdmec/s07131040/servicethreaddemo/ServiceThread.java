package cn.edu.gdmec.s07131040.servicethreaddemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class ServiceThread extends Service {
	private Runnable backgroundWork = new Runnable(){

		@Override
		public void run() {
			while(!Thread.interrupted()){
				double randomDouble=Math.random();
				//调用主界面的更新方法把随机数传过去
				MainActivity.UpdateGUI(randomDouble);
				//线程睡眠1000毫秒
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
	};
	//声明线程对象
	private Thread workThread;
	
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Toast.makeText(this, "服务创建了 ", 1000).show();
		//使用runnable创建线程
		workThread= new Thread(null, backgroundWork, "WorkThread");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Toast.makeText(this, "服务被中断了", 1000).show();
		workThread.interrupt();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "服务启动了",1000).show();
		//如果线程没有处于alive状态，则启动线程
		if (!workThread.isAlive()){
			workThread.start();
		}
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
