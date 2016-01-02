package io.yuck.richtoolbox.property.translater;

/**
 * value translater
 * Created by yuck on 2015/12/29.
 */
public interface IValueTranslater {
    Object translateValue(String fieldName, Object value);
}
