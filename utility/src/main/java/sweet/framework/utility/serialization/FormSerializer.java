package sweet.framework.utility.serialization;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FormSerializer {

    private final static String EMPTY_STRING = "";

    public static <T> String Serialize(T obj) {
        return Serialize(obj, "=", "&", true, true, true);
    }

    public static <T> String Serialize(T obj, String valueSplit, String parmSplit, boolean ignoreNull, boolean enableSort, boolean asc) {
        if (obj == null) {
            return EMPTY_STRING;
        }
        if (valueSplit == null) {
            valueSplit = EMPTY_STRING;
        }
        if (parmSplit == null) {
            parmSplit = EMPTY_STRING;
        }
        Field[] fields = obj.getClass().getDeclaredFields();

        if (fields.length <= 0) {
            return EMPTY_STRING;
        }

        Stream<Field> propsLabmda = Arrays.stream(fields);

        if (enableSort) {
            propsLabmda = asc
                    ? propsLabmda.sorted(Comparator.comparing(Field::getName))
                    : propsLabmda.sorted((n1, n2) -> n2.getName().compareTo(n1.getName()));
        }

        List<Field> props = propsLabmda.collect(Collectors.toList());

        StringBuilder sbForm = new StringBuilder();
        for (Field item : props) {
            Object value = null;
            try {
                item.setAccessible(true);
                value = item.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (value == null) {
                value = EMPTY_STRING;
            }

            if (ignoreNull && value.toString().length() <= 0) {
                continue;
            }

            sbForm.append(item.getName());
            sbForm.append(valueSplit);
            sbForm.append(value);
            sbForm.append(parmSplit);
        }

        return sbForm.substring(0, sbForm.length() - parmSplit.length());
    }

}
