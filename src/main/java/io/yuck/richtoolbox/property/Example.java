package io.yuck.richtoolbox.property;

import io.yuck.richtoolbox.property.comparator.SimpleFieldComparator;
import io.yuck.richtoolbox.property.comparator.impls.StringFieldComparator;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

/**
 * Created by yuck on 2015/12/29.
 */
public class Example {
    public static void main(String[] args) {
//        PropertyCompareHelper.Builder builder = new PropertyCompareHelper.Builder();
//        PropertyCompareHelper<String> build = builder.<String>build();
//        Map<String, String[]> modifiedProperties = null;
//        try {
//            modifiedProperties = build.getModifiedProperties("1", "2");
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        System.out.println(modifiedProperties);
        SimpleFieldComparator<String> stringSC = new SimpleFieldComparator<String>();
        StringFieldComparator stringFieldComparator = new StringFieldComparator();
        int compare = stringFieldComparator.compare("11111", "11111");
        int compare1= stringSC.compare("11111", "11111");
        System.out.println(compare);
        System.out.println(compare1);
    }

    public static class DemoPOJO {
        public int a;
        public Integer A;
        public double b;
        public Double B;
        public boolean c;
        public Boolean C;
        public Date date;
    }

}
