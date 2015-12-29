package io.yuck.richtoolbox.property;

import io.yuck.richtoolbox.property.translater.IValueTranslater;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.map.HashedMap;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by yuck on 2015/12/29.
 */
public class PropertyCompareHelper<T> {
    private final Map<String, IValueTranslater> valueTranslaterMap;
    private final Set<String> ignoreFields;

    private PropertyCompareHelper(Set<String> ignoreFields, Map<String, IValueTranslater> valueTranslaterMap) {
        this.ignoreFields = ignoreFields;
        this.valueTranslaterMap = valueTranslaterMap;
    }

    //TODO
    public Map<String, String[]> getModifiedProperties(T oldObj, T newObj) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(oldObj.getClass());
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            String name = descriptor.getName();
            if ("class".equals(name)) {
                continue;
            }
            if (ignoreFields.contains(name)) {
                continue;
            }
            Object oldValue = PropertyUtils.getProperty(oldObj, name);
            Object newValue = PropertyUtils.getProperty(newObj, name);
            if (valueTranslaterMap.containsKey(name)) {
                IValueTranslater valueTranslater = valueTranslaterMap.get(name);
                oldValue = valueTranslater.translateValue(oldValue);
                newValue = valueTranslater.translateValue(newValue);
            }
        }
        return null;
    }


    public static class Builder {
        private Map<String, IValueTranslater> valueTranslaterMap;
        private Set<String> ignoreFields;

        public Builder() {
            valueTranslaterMap = new HashedMap();
            ignoreFields = new HashSet<>();
        }

        public Builder valueTranslater(IValueTranslater valueTranslater, String... fieldNames) {
            for (String fieldName : fieldNames) {
                valueTranslaterMap.put(fieldName, valueTranslater);
            }
            return this;
        }

        public Builder valueTranslater(String... fieldNames) {
            for (String fieldName : fieldNames) {
                ignoreFields.add(fieldName);
            }
            return this;
        }

        public <N> PropertyCompareHelper build() {
            PropertyCompareHelper<N> propertyCompareHelper = new PropertyCompareHelper<>(ignoreFields, valueTranslaterMap);
            return propertyCompareHelper;
        }
    }
}
