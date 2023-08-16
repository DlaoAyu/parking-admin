package com.laoayu.parking.common.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.baidu.aip.ocr.AipOcr;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: LaoAyu
 * @date: 2023/04/02
 **/
@Component
@Configuration
public class ApiOcrUtil {
    //设置APPID/AK/SK
    private static String APP_ID = "31xxxx98";
    private static String API_KEY ="jHLYRKxxxxxXTI19AFD";
    private static String SECRET_KEY = "EyyzcVYAyxxxxxxxxxxxxxhzrxFxGrsqQ";

    public static void main(String[] args) {

        // 初始化一个AipOcr
        AipOcr aipOcr = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        //设置本地图片地址
//        String path = "D:\\项目模板\\car-OCR-master\\car-OCR-master\\res\\image\\baidu_image\\test1.jpg";
        String path = "D:\\ParkingManagementSystem\\Parking\\park_pic\\d208c933c9744152a4dbbfb4c90b9b44test19.jpg";
        //通用文字识别
        licencePlateNumber(aipOcr,path);

    }

    /**
     * 测试静态图片
     * 车牌号识别
     */
    public static String licencePlateNumber(AipOcr aipOcr,String path){
        try {
            // 传入可选参数调用接口
            HashMap<String, String> options = new HashMap<String, String>();
            /**
             * 是否检测多张车牌，默认为false
             * 当置为true的时候可以对一张图片内的多张车牌进行识别
             */
            options.put("multi_detect", "false");//是否检测多张图片，默认false
            //本地图片识别，返回JSON对象
            JSONObject jsonObject = aipOcr.plateLicense(path, options);
            //获取需要的信息
            JSONObject result = jsonObject.getJSONObject("words_result");
            System.out.print(result+"\n");
            System.out.println("车牌颜色:"+result.getString("color")+"\n"+"车牌号:"+result.getString("number"));
            Object number = result.get("number");
            return number.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }
    /**
     * 参数为本地图片路径
     */
    public Map<String, Object> plateLicense(String image) {
        try {
            AipOcr client = new AipOcr("31913498", "jHLYRK68f3g5H8IeXTI19AFD", "EyyzcVYAyMsXZykm20z5xhzrxFxGrsqQ");
            client.setConnectionTimeoutInMillis(2000);
            client.setSocketTimeoutInMillis(60000);
            HashMap<String, String> options = new HashMap<>();
            /**
             * 是否检测多张车牌，默认为false
             * 当置为true的时候可以对一张图片内的多张车牌进行识别
             */
            options.put("multi_detect", "true");
            JSONObject res = client.plateLicense(image, options);
            System.out.println("扫描结果：" + res.toString());
            Object result = res.get("words_result");
            JSONArray array = JSON.parseArray(result.toString());
            com.alibaba.fastjson2.JSONObject object = JSON.parseObject(array.get(0).toString());
            Object number = object.get("number");
            Object color = object.get("color");
            Map<String, Object> map = new HashMap<>();
            map.put("number",number);
            map.put("color",color);
            System.out.println("map中的值" + map);

            return map;
            //  return map.toString();
        }catch (Exception e){
            e.printStackTrace();
            //  return "";
            return null;
        }
    }

}
