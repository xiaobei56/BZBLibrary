package cn.gridlife.bzblibrary.a_baidulocation;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import cn.gridlife.bzblibrary.BZBLibraryAPP;

import static cn.gridlife.bzblibrary.BZBLibraryAPP.getContextObject;

/**
 * Created by BZB on 2017/9/8.
 */

public class LocationHelper {
    private LocationCallBack callBack;
    private static LocationHelper helper;

    private LocationClient locationClient;
    private BDAbstractLocationListener locationListener = new MyBDLocationListener();
    int requestCode;
    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    private LocationHelper() {
        //第一步实例化定位核心类
        locationClient = new LocationClient(getContextObject(), getLocOption());
        //第二步设置位置变化回调监听
        locationClient.registerLocationListener(locationListener);
    }

    public static LocationHelper getInstance() {
        if (helper == null) {
            helper = new LocationHelper();
        }
        return helper;
    }

    public void start() {
//        第三步开始定位
        locationClient.start();
    }

    //一般会在Activity的OnDestroy方法调用
    public void stop() {
        if (locationClient != null) {
            locationClient.unRegisterLocationListener(locationListener);
            locationClient.stop();
            locationClient = null;
        }
    }

    private LocationClientOption getLocOption() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span=1000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

//        option.setIgnoreCacheException(false);
//        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

//        option.setWifiValidTime(5*60*1000);
//        //可选，7.2版本新增能力，如果您设置了这个接口，首次启动定位时，会先判断当前WiFi是否超出有效期，超出有效期的话，会先重新扫描WiFi，然后再定位

//        mLocationClient.setLocOption(option);
        return option;
    }

    private class MyBDLocationListener extends BDAbstractLocationListener

    {



        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (callBack != null && bdLocation != null) {
                callBack.callBack(getRequestCode(),bdLocation.getAddrStr(),
                        bdLocation.getStreetNumber(), bdLocation.getLatitude(), bdLocation.getLongitude(),
                        bdLocation.getDistrict(), bdLocation.getStreet(), bdLocation.getCity(),bdLocation.getLocType());
            }
            //多次定位必须要调用stop方法
            locationClient.stop();
        }
    }

    public interface LocationCallBack
    {
        /**
         *
         * @param requestCode 请求码
         * @param addr 地址信息
         * @param streetnumber 获取街道码
         * @param lat 纬度
         * @param lng 经度
         * @param district 区县信息
         * @param street 街道信息
         * @param city 城市信息
         * @param locType 定位类型
         */
        void callBack(int requestCode,String addr, String streetnumber, double lat, double lng, String
                district, String street, String city,int locType);
    }

    public LocationCallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(LocationCallBack callBack) {
        this.callBack = callBack;
    }

}
