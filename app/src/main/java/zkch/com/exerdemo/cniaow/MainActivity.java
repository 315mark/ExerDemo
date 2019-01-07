package zkch.com.exerdemo.cniaow;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import zkch.com.exerdemo.R;

public class MainActivity extends AppCompatActivity {

    private MyServiceConnection conn=null;   //获取service
    private boolean isAppExit=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //点击监听
    public void startService(View view){
        //执行服务
        startService(new Intent(this,MainActivity.class));
    }

    //手动解绑执行两次会发生异常
    // Activity销毁时执行,因此可以把解绑的功能添加到此方法中
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (conn !=null){
            unbindService(conn);
            conn=null;
        }
        stopService(new Intent(this,MainActivity.class));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 如果是Back则重写(用户需要在2秒钟按两次才关闭)
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            appExit();
            return  false;
        }else{
            return super.onKeyDown(keyCode, event);
        }
    }

    //远程绑定服务  Aidl  调用系统服务后者第三方app的服务

    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what ==1){
                isAppExit=false;
            }
            return false;
        }
    });


    private void appExit() {
       // 第一次按了back键则修改状态值,如果两秒未重复按则还原
        if (!isAppExit){
            isAppExit=true;
            //两秒未重复按则还原
            Message msg=handler.obtainMessage();
            // 设置一个状态,主线程的handler对象根据状态来执行操作
            msg.what = 1;
            handler.sendMessageDelayed(msg,2000);
        }else{
            finish();
        }
    }

    private class MyServiceConnection  implements ServiceConnection{
        @Override  //建立连接执行
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

        }

        @Override  //系统解除绑定时执行  非用户手动关闭conn时调用
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }
}
