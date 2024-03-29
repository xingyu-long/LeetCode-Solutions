package com.leetcode.trie;

import java.util.*;

/**
 * @Date: 10/24/2020
 * @Description: Trie
 **/
public class _588_Design_In_Memory_File_System {
    private static class File {
        Map<String, File> child;
        String content;
        boolean isFile;

        public File() {
            child = new HashMap<>();
            content = "";
            isFile = false;
        }
    }


    private File root;

    public _588_Design_In_Memory_File_System() {
        root = new File();
    }

    // 按照层次遍历
    public List<String> ls(String path) {
        String[] dirs = path.split("/");
        File node = root;
        List<String> res = new ArrayList<>();
        String name = "";
        for (String dir : dirs) {
            if (dir.length() == 0) continue;
            if (!node.child.containsKey(dir)) {
                return res;
            }
            node = node.child.get(dir);
            name = dir;
        }

        if (node.isFile) {
            res.add(name);
        } else {
            for (String key : node.child.keySet()) {
                res.add(key);
            }
        }
        Collections.sort(res);
        return res;
    }

    public void mkdir(String path) {
        String[] dirs = path.split("/");
        File node = root;
        for (String dir : dirs) {
            if (dir.length() == 0) continue;
            if (!node.child.containsKey(dir)) {
                File file = new File();
                node.child.put(dir, file);
            }
            node = node.child.get(dir);
        }
    }

    public void addContentToFile(String filePath, String content) {
        String[] dirs = filePath.split("/");
        File node = root;
        for (String dir : dirs) {
            if (dir.length() == 0) continue;
            if (!node.child.containsKey(dir)) {
                File file = new File();
                node.child.put(dir, file);
            }
            node = node.child.get(dir);
        }
        node.isFile = true;
        node.content += content;
    }

    public String readContentFromFile(String filePath) {
        String[] dirs = filePath.split("/");
        File node = root;
        for (String dir : dirs) {
            if (dir.length() == 0) continue;
            if (!node.child.containsKey(dir)) return "";
            node = node.child.get(dir);
        }
        return node.content;
    }
}
