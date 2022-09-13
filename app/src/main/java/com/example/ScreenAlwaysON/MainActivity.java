/**                     
    * Project:  永久亮屏
    * Comments: 主界面类
    * JDK version used: <JDK1.8>
    * Author： Bunny     Github: https://github.com/bunny-chz/
    * Create Date：2022-06-17
    * Version: 1.0
    */

package com.example.ScreenAlwaysON;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String TIPS ="一直亮屏需要注意以下事项：\n" +
            "1.本软件要保持后台运行\n" +
            "2.加入应用白名单，锁定软件，防止被系统清除掉\n" +
            "3.关闭省电模式\n\n" +
            "" +
            "此操作很耗电，不用时请务必把软件关闭(最近使用的应用，把它上滑关闭)";
    TextView tv_tips;
    private PowerManager.WakeLock mWakeLock;

    @SuppressLint("InvalidWakeLockTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_tips = findViewById(R.id.tv_tips);
        tv_tips.setText(TIPS);
        PowerManager powerManager = (PowerManager)getSystemService(POWER_SERVICE);
        if (powerManager != null) {
            mWakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "WakeLock");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWakeLock != null) {
            mWakeLock.acquire();
            Log.d("Resume","Resume11111111");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWakeLock != null) {
            mWakeLock.acquire();
            Log.d("Pause","Pause11111111");
        }

    }

    /**
     再按一次退出主界面操作
     **/
    long exitTime = 0;
    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
            return;
        }
        finish();
    }
}