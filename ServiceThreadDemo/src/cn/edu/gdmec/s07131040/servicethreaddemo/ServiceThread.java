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
				//����������ĸ��·��������������ȥ
				MainActivity.UpdateGUI(randomDouble);
				//�߳�˯��1000����
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
	};
	//�����̶߳���
	private Thread workThread;
	
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Toast.makeText(this, "���񴴽��� ", 1000).show();
		//ʹ��runnable�����߳�
		workThread= new Thread(null, backgroundWork, "WorkThread");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Toast.makeText(this, "�����ж���", 1000).show();
		workThread.interrupt();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "����������",1000).show();
		//����߳�û�д���alive״̬���������߳�
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
