package com.vspkg;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.util.Iterator;

public class JsonParser {
    public JSONArray parseJson(String json) {
        try {
            JSONArray object = new JSONArray(json);
            JSONObject libs = object.getJSONObject(0);
            Iterator<String> x = libs.keys();
            JSONArray jsonArray = new JSONArray();
        /*
        Due to reasons unknown (Probably cause the json output from vspkg is experimental)
        The format of json not allows for quick array creation
        So we need to recreate it ourselves.
         */
            while (x.hasNext()) {
                String key = x.next();
                JSONObject jsonObject = libs.getJSONObject(key);
                jsonObject.put("name", key);
                jsonArray.put(jsonObject);
            }
            return jsonArray;
        }
        catch (Exception e){
            noticeUser(e.getMessage());
            return null;
        }
    }
    public  void noticeUser(String message){
        JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                JOptionPane.ERROR_MESSAGE);
    }
}
