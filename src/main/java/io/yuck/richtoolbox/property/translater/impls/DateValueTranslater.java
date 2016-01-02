package io.yuck.richtoolbox.property.translater.impls;

import io.yuck.richtoolbox.property.translater.IValueTranslater;

import java.text.SimpleDateFormat;

/**
 * Created by yuck on 2015/12/29.
 */
public class DateValueTranslater implements IValueTranslater {
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final SimpleDateFormat simpleDateFormat;

    public DateValueTranslater(String dateFormat) {
        if (dateFormat == null) {
            this.simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        } else {
            this.simpleDateFormat = new SimpleDateFormat(dateFormat);
        }
    }

    @Override
    public Object translateValue(String fieldName, Object value) {
        if (value == null) {
            return null;
        }
        return simpleDateFormat.format(value);
    }
}
