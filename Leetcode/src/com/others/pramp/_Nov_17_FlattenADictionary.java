package com.pramp;

import java.util.HashMap;

public class _Nov_17_FlattenADictionary {
    HashMap<String, String> flattenDictionary(HashMap<String, Object> dict) {
        // your code goes here
        HashMap<String, String> res = new HashMap<>();
        dfs(dict, "", res);
        return res;
    }

    public static void dfs(HashMap<String, Object> dict, String path, HashMap<String, String> res) {
        for (String key : dict.keySet()) {
            if (dict.get(key) instanceof Object) {
                continue;
            } else {
                res.put(key, (String) dict.get(key));
            }
        }
    }

    public static void main(String[] args) {
        Object object = new Object();
        String s =  "s";
        System.out.println(object instanceof String);
    }
}
