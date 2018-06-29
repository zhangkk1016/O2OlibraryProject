package cn.hnust.basebiz.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;

/**
 * 蓝牙工具类
 */  
public class MacUtils {  
      
//
//  <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />  
//  <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />  
 /**
     * ��ȡ�豸��Ψһ��ʶ��deviceId
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = tm.getDeviceId();
        if (deviceId == null) {
            return "---";
        } else {
            return deviceId;
        }
    }

    /** 
     * ��ȡ�ֻ���MAC��ַ 
     *  
     * @return 
     */  
    public static String getMac() {  
        String str = "";  
        String macSerial = "";  
        try {  
            Process pp = Runtime.getRuntime().exec(  
                    "cat /sys/class/net/wlan0/address ");  
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
  
            for (; null != str;) {  
                str = input.readLine();  
                if (str != null) {  
                    macSerial = str.trim();// ȥ�ո�  
                    break;  
                }  
            }  
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
        if (macSerial == null || "".equals(macSerial)) {  
            try {  
                return loadFileAsString("/sys/class/net/eth0/address")  
                        .toUpperCase().substring(0, 17);  
            } catch (Exception e) {  
                e.printStackTrace();  
  
            }  
  
        }  
        return macSerial;  
    }  
      
    public static String loadFileAsString(String fileName) throws Exception {  
        FileReader reader = new FileReader(fileName);
        String text = loadReaderAsString(reader);  
        reader.close();  
        return text;  
    }  
  
    public static String loadReaderAsString(Reader reader) throws Exception {
        StringBuilder builder = new StringBuilder();  
        char[] buffer = new char[4096];  
        int readLength = reader.read(buffer);  
        while (readLength >= 0) {  
            builder.append(buffer, 0, readLength);  
            readLength = reader.read(buffer);  
        }  
        return builder.toString();  
    }     
  
}  