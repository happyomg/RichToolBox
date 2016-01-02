package io.yuck.richtoolbox.property.annotation;

import java.lang.annotation.*;

/**
 * Created by yuck on 2016/1/2.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DisplayName {
    public String value();
}
