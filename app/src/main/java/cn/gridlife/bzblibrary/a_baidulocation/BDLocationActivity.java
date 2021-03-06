package cn.gridlife.bzblibrary.a_baidulocation;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.gridlife.bzblibrary.R;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by BZB on 2017/9/7.
 */
@RuntimePermissions
public class BDLocationActivity extends Activity implements LocationHelper.LocationCallBack {

    private Button btnLocation;
    private Button btnLocationTimer;
    private EditText etTime;
    private TextView tvOnceLocation;
    private TextView tvTimerLocation;
    private LocationHelper helper;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startLocationTimer();
        }
    };

   @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_activity_main);
        initView();
        showRationaleForCamera(new PermissionRequest() {
            @Override
            public void proceed() {
                startAppSettings();
            }

            @Override
            public void cancel() {
                Toast.makeText(BDLocationActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
            }
        });
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvOnceLocation.setVisibility(View.VISIBLE);
                tvOnceLocation.setText("Test");
                startLocationOnce();


            }
        });
        btnLocationTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvTimerLocation.setVisibility(View.VISIBLE);


                new Thread(new MyThread()).start();

            }
        });


    }

    public class MyThread implements Runnable {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (true) {
                try {
                    Thread.sleep(1000*60*10-1000);// 线程暂停10秒，单位毫秒
                    Message message = new Message();
                    message.what = 1;
                    handler.sendEmptyMessage(message.what);// 发送消息
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 在Android 6.0系统中，需要动态获取的权限涉及到：
     * 1、获取手机状态：
     * Manifest.permission.READ_PHONE_STATE
     * <p>
     * 2、获取位置信息：
     * Manifest.permission.ACCESS_COARSE_LOCATION
     * Manifest.permission.ACCESS_FINE_LOCATION
     * <p>
     * 3、读写SD卡：
     * Manifest.permission.READ_EXTERNAL_STORAGE
     * Manifest.permission.WRITE_EXTERNAL_STORAGE
     */
    private static final int LOCTION_ONCE = 1;
    private static final int LOCTION_TIMER = 2;

    @NeedsPermission({Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.LOCATION_HARDWARE,Manifest.permission.READ_PHONE_STATE})
    public void startLocationOnce() {
//        if (helper!=null){
//            helper.stop();
//        }
        helper = LocationHelper.getInstance();
        helper.setRequestCode(LOCTION_ONCE);
        helper.setCallBack(this);
        helper.start();
    }

    @NeedsPermission({Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.LOCATION_HARDWARE,Manifest.permission.READ_PHONE_STATE})
    public void startLocationTimer() {
//        if (helper!=null){
//            helper.stop();
//        }
        helper = LocationHelper.getInstance();
        helper.setRequestCode(LOCTION_TIMER);
        helper.setCallBack(this);
        helper.start();
    }
    @OnShowRationale({Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION})
    public void showRationaleForCamera(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage("定位权限")
                .setPositiveButton("yes", (dialog, button) -> request.proceed())
                .setNegativeButton("no", (dialog, button) -> request.cancel())
                .show();
    }
    /**
     * 权限方案
     */
    public static final String PACKAGE_URL_SCHEME = "package:";
    /**
     * 打开系统应用设置(ACTION_APPLICATION_DETAILS_SETTINGS:系统设置权限)
     */
    public void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse(PACKAGE_URL_SCHEME + getPackageName()));
        startActivity(intent);
    }
    private void initView() {
        btnLocation = (Button) findViewById(R.id.btn_getLocation);
        btnLocationTimer = (Button) findViewById(R.id.btn_location_timer);
        etTime = (EditText) findViewById(R.id.et_timer);
        tvOnceLocation = (TextView) findViewById(R.id.tv_once_location);
        tvTimerLocation = (TextView) findViewById(R.id.tv_timer_location);

    }

    /**
     * @param requestCode  请求码
     * @param addr         地址信息
     * @param streetnumber 获取街道码
     * @param lat          纬度
     * @param lng          经度
     * @param district     区县信息
     * @param street       街道信息
     * @param city         城市信息
     * @param locType      定位类型
     */
    @Override
    public void callBack(int requestCode, String addr, String streetnumber, double lat, double lng, String district, String street, String city, int locType) {
        switch (requestCode) {
            case LOCTION_ONCE:
                tvOnceLocation.setText("Latitude:" + lat + "  " + "locType" + locType);
                break;
            case LOCTION_TIMER:
                tvTimerLocation.setText(tvTimerLocation.getText() == null ? "" : tvTimerLocation.getText() + "\n" + "Latitude:" + lat + "  " + "locType" + locType);
                break;
        }
    }
}
