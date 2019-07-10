package com.ujiuye.tool.parseParamMap;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: lvwei
 * @Date: 2019/4/10 19:12
 * @project: ppms
 * @Description:模糊查询解析参数map集合工具类
 */
@Component
public class ParseParamMapToMybatisMap {
    public Map<String,Object> parseParamMapToMybatisMap(Map<String,Object> map){
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        Map<String, Object> resultMap = new HashMap<>();
        for (Map.Entry<String,Object> entry:entries) {
            String key=entry.getKey();
            String value=(String)entry.getValue();
            if (key.contains("like_")){
                key=key.substring(5);
                value="%"+value+"%";
            }
            resultMap.put(key,value);
        }
        return resultMap;
    }
    public String parseParamMapToString(Map<String,Object> map){
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        for (Map.Entry<String,Object> entry:entries) {
            String key=entry.getKey();
            String value=(String)entry.getValue();
            sb.append("&").append("search_").append(key).append("=").append(value);

        }
        return sb.toString();
    }
}
