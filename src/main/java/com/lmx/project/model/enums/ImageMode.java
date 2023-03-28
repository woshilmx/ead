package com.lmx.project.model.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public enum ImageMode {
    //     cartoon ="cartoon";   //：卡通画风格
    cartoon("卡通画风格", "cartoon"),
    pencil("铅笔风格", "pencil"),
    color_pencil("彩色铅笔画风格", "color_pencil"),
    warm("彩色糖块油画风格", "warm"),
    wave("神奈川冲浪里油画风格", "wave"),
    lavender("薰衣草油画风格", "lavender"),
    mononoke("奇异油画风格", "mononoke"),
    scream("呐喊油画风格", "scream"),
    gothic("哥特油画风格", "gothic");


    private final String text;

    private final String value;

    ImageMode(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }

    public static ImageMode getItem(String value) {
        ImageMode[] values = values();
        for (ImageMode imageMode : values) {
            if (imageMode.getValue().equals(value)) {
                return imageMode;
            }
        }
        return null;
    }
    //    String pencil = "pencil";   //：铅笔风格
//    String color_pencil = "color_pencil";   //        ：彩色铅笔画风格
//    String warm = "warm";   //：彩色糖块油画风格
//    String wave = "wave";   //：神奈川冲浪里油画风格
//    String lavender = "lavender";   //    ：薰衣草油画风格
//    String mononoke = "mononoke";   //    ：奇异油画风格
//    String scream = "scream";   //：呐喊油画风格
//    String gothic = "gothic";   //：哥特油画风格

    public static HashMap<String, String> getValues() {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        ImageMode[] values = values();
        for (ImageMode imageMode : values) {
            String text = imageMode.getText();
            String value = imageMode.getValue();
            stringStringHashMap.put(text, value);
        }
        return stringStringHashMap;


    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static void main(String[] args) {
        System.out.println(getValues());
    }
}
