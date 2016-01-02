package io.yuck.richtoolbox.property.comparator.impls;

import io.yuck.richtoolbox.property.comparator.IFieldComparator;

/**
 * Created by wuxw on 2015/12/31.
 */
public class SimpleFieldComparator<T> implements IFieldComparator<T> {

    private static class LasyHolder {
        public static SimpleFieldComparator<Object> simpleFieldComparator = new SimpleFieldComparator<>();
    }

    public static SimpleFieldComparator defaultInstance() {
        return LasyHolder.simpleFieldComparator;
    }


    @Override
    public T[] compareModefied(T o1, T o2) {
        if (equal(o1, o2)) {
            return null;
        }
        //noinspection unchecked
        return (T[]) new Object[]{o1, o2};
    }

    protected boolean equal(T o1, T o2) {
        if (o1 == null && o2 == null) {
            return true;
        } else if (o1 != null && o2 != null) {
            return o1.equals(o2);
        }
        return false;
    }
}
