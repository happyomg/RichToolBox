package io.yuck.richtoolbox.property.comparator;

/**
 * Created by wuxw on 2015/12/31.
 */
public class SimpleFieldComparator<T extends Comparable<T>> implements IFieldComparator<T> {

    @Override
    public T[] compareModefied(T o1, T o2) {
        int result = compare(o1, o2);
        if (result == 0) {
            return null;
        }
        //noinspection unchecked
        return (T[]) new Object[]{o1, o2};
    }

    @Override
    public int compare(T o1, T o2) {
        Integer compareResult = commonCompare(o1, o2);
        if (compareResult != null) {
            return compareResult;
        }
        return o1.compareTo(o2);
    }

    public Integer commonCompare(T o1, T o2) {
        if (o1 == null && o2 == null) {
            return 0;
        } else if (o1 == null) {
            return -1;
        } else if (o2 == null) {
            return 1;
        }
        return null;
    }

}
