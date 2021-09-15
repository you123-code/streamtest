package com.you;

import org.junit.Test;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author youwei
 * @version 1.0
 * @date 2021/9/13 17:55
 */
public class CornTest {
    private static final Map<String, String> map = new LinkedHashMap<String, String>() {{
        this.put("001", "抵押");
        this.put("002", "质押");
        this.put("003", "保证");
        this.put("004", "信用");
        this.put("005", "综合担保");
    }};
    @Test
    public void xqTest(){
        Collection<String> maoValue = map.values();
        System.out.println("map中数据为：");
        for(String s : maoValue){
            System.out.println(s);
        }
        String str = "001,002,003,004";
        //String str = "";
        if(Objects.nonNull(str)){

        }
          if(!str.isEmpty()){
              String[] arr = str.split(",");
              //System.out.println("字符串转化为数组后的第一个值"+arr[1]);
              String res = "";
              for(String s : arr){
                  String s1 = map.get(s);
                 if(!Objects.isNull(s1)){
                     if(res.isEmpty()){
                         res = s1;
                     }else{
                         res = res + "," + s1;
                     }
                 }
              }
              System.out.println("最后结果："+res);
          }else{
              System.out.println("字符串不能为空！");
          }
    }
    @Test
    public boolean containsDuplicate(){
        int[] nums = {1,2,2,3};
        for(int i = 0;i < nums.length;i++){
            for(int j = i + 1;j < nums.length;j++){
                if(nums[i] == nums[j]){
                    return true;
                }
            }
        }
        return false;
    }
}
