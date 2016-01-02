package io.yuck.richtoolbox.property.comparator;

/**
 * Created by yuck on 2015/12/29.
 */
public interface IFieldComparator<T> {
    /**
     * @param o1 oldValue
     * @param o2 newValue
     * @return an array with oldVal and newVal ,or null if not changed
     */
    T[] compareModefied(T o1, T o2);
}
