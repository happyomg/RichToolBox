package io.yuck.richtoolbox.property;

import io.yuck.richtoolbox.property.annotation.DisplayName;
import io.yuck.richtoolbox.property.comparator.IFieldComparator;
import io.yuck.richtoolbox.property.comparator.impls.SimpleFieldComparator;
import io.yuck.richtoolbox.property.translater.IValueTranslater;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * field -->  filterIgnoreField --> valueTranslater --> fieldComparator
 * Created by yuck on 2015/12/29.
 */
public class PropertyCompareHelper<T> {
    private final SimpleFieldComparator<Object> simpleFieldComparator;
    private final Map<String, IValueTranslater> valueTranslaterMap;
    private final Map<String, IFieldComparator> fieldComparatorMap;
    private final Set<String> ignoreFields;

    private PropertyCompareHelper(Set<String> ignoreFields, Map<String, IValueTranslater> valueTranslaterMap, Map<String, IFieldComparator> fieldComparatorMap) {
        this.ignoreFields = ignoreFields;
        this.valueTranslaterMap = valueTranslaterMap;
        this.fieldComparatorMap = fieldComparatorMap;
        this.simpleFieldComparator = SimpleFieldComparator.defaultInstance();
    }

    public Map<String, Object[]> getModifiedProperties(T oldObj, T newObj) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Map<String, Object[]> modifiedProperties = new HashMap<>();
        if (oldObj == null && newObj == null) {
            return modifiedProperties;
        }
        Class<?> aClass = oldObj == null ? newObj.getClass() : oldObj.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        String fieldName;
        String displayName;
        for (Field field : declaredFields) {
            fieldName = field.getName();
            displayName = fieldName;
            if ("class".equals(fieldName)) {
                continue;
            }
            if (ignoreFields.contains(fieldName)) {
                continue;
            }
            field.setAccessible(true);
            Object oldValue = field.get(oldObj);
            Object newValue = field.get(newObj);

            if (valueTranslaterMap.containsKey(fieldName)) {
                IValueTranslater valueTranslater = valueTranslaterMap.get(fieldName);
                oldValue = valueTranslater.translateValue(fieldName, oldValue);
                newValue = valueTranslater.translateValue(fieldName, newValue);
            }

            Object[] modified;
            if (fieldComparatorMap.containsKey(fieldName)) {
                modified = fieldComparatorMap.get(fieldName).compareModefied(oldValue, newValue);
            } else {
                modified = simpleFieldComparator.compareModefied(oldValue, newValue);
            }

            if (modified != null) {
                if (field.isAnnotationPresent(DisplayName.class)) {
                    displayName = field.getAnnotation(DisplayName.class).value();
                }
                modifiedProperties.put(displayName, modified);
            }
        }
        return modifiedProperties;
    }


    public static class Builder {
        private Map<String, IValueTranslater> valueTranslaterMap;
        private Map<String, IFieldComparator> fieldComparatorMap;
        private Set<String> ignoreFields;

        public Builder() {
            valueTranslaterMap = new HashMap<>();
            fieldComparatorMap = new HashMap<>();
            ignoreFields = new HashSet<>();
        }

        public Builder valueTranslater(IValueTranslater valueTranslater, String... fieldNames) {
            assert valueTranslater != null;
            for (String fieldName : fieldNames) {
                valueTranslaterMap.put(fieldName, valueTranslater);
            }
            return this;
        }

        public Builder fieldComparator(IFieldComparator iFieldComparator, String... fieldNames) {
            assert iFieldComparator != null;
            for (String fieldName : fieldNames) {
                fieldComparatorMap.put(fieldName, iFieldComparator);
            }
            return this;
        }

        public Builder ignoreFields(String... fieldNames) {
            for (String fieldName : fieldNames) {
                ignoreFields.add(fieldName);
            }
            return this;
        }


        public <N> PropertyCompareHelper build() {
            PropertyCompareHelper<N> propertyCompareHelper = new PropertyCompareHelper<>(ignoreFields, valueTranslaterMap, fieldComparatorMap);
            return propertyCompareHelper;
        }
    }
}
