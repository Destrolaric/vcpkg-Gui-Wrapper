package com.vspkg;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

public class JsonParser {
    public static JSONArray parseJson(String json){
        JSONArray object = new JSONArray(json);
        JSONObject libs = object.getJSONObject(0);
        Iterator x = libs.keys();
        JSONArray jsonArray = new JSONArray();

        while (x.hasNext()){
            String key = (String) x.next();
            JSONObject jsonObject = libs.getJSONObject(key);
            jsonObject.put("name", key);
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }
}
