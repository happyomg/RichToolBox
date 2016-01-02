package io.yuck.richtoolbox.property;

import io.yuck.richtoolbox.property.annotation.DisplayName;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

/**
 * Created by yuck on 2015/12/29.
 */
public class Example {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        PropertyCompareHelper.Builder builder = new PropertyCompareHelper.Builder();
        PropertyCompareHelper<Object> build = builder.<String>build();
        DemoPOJO oldP = new DemoPOJO(0);
        DemoPOJO newP = new DemoPOJO(0);
        newP.a = 1;
        newP.A = 2;
        oldP.date = new Date();
        newP.date = new Date();
        Map<String, Object[]> properties = build.getModifiedProperties(oldP, newP);
        for (Map.Entry<String, Object[]> stringEntry : properties.entrySet()) {
            System.out.println(stringEntry.getKey() + "::::" + stringEntry.getValue()[0] + "    " + stringEntry.getValue()[1]);

        }
//        SimpleFieldComparator<String> stringSC = new SimpleFieldComparator<String>();
//        StringFieldComparator stringFieldComparator = new StringFieldComparator();
//        int compare = stringFieldComparator.compare("11111", "11111");
//        int compare1= stringSC.compare("11111", "11111");
//        System.out.println(compare);
//        System.out.println(compare1);
    }

    public static class DemoPOJO {
        @DisplayName("aaaaa")
        private int a;
        public Integer A;
        public double b;
        public Double B;
        public boolean c;
        public Boolean C;
        public Date date;


        public DemoPOJO(int a) {
            this.a = a;
        }
    }

}
