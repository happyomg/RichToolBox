package io.yuck.richtoolbox.property;

import java.util.Map;

/**
 * Created by yuck on 2015/12/29.
 */
public class Example {
    public static void main(String[] args) {
        PropertyHelper.Builder builder = new PropertyHelper.Builder();
        PropertyHelper<Integer> build = builder.<Integer>build();
        Map<String, String[]> modifiedProperties = build.getModifiedProperties(1, 2);
        System.out.println(modifiedProperties);
    }
}
