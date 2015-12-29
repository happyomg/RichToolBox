package io.yuck.richtoolbox.property.comparator.impls;

import io.yuck.richtoolbox.property.comparator.IFieldComparator;

import java.util.Date;

/**
 * Created by yuck on 2015/12/29.
 */
public class BooleanFieldComparator implements IFieldComparator<Boolean> {
    @Override
    public int compare(Boolean o1, Boolean o2) {
        if (o1 == null && o2 == null) {
            return 0;
        } else if (o1 == null) {
            return -1;
        } else if (o2 == null) {
            return 1;
        }
        return o1.compareTo(o2);
    }


}
