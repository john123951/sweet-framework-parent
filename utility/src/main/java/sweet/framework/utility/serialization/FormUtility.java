package sweet.framework.utility.serialization;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;

public final class FormUtility {
    private static final String EMPTY_STRING = "";

    public static <T> String Serialize(T obj, Class clazz) {
        String valueSplit = "=";
        String parmSplit = "&";

        return Serialize(obj, clazz, valueSplit, parmSplit, true, true, true);
    }

    /**
     * 序列化为Form表单形式
     *
     * @param obj
     * @param clazz
     * @param valueSplit
     * @param parmSplit
     * @param ignoreNull 忽略空值
     * @param enableSort 启用排序
     * @param asc        升序
     * @param <T>
     * @return
     * @throws IllegalAccessException
     */
    public static <T> String Serialize(T obj, Class clazz, String valueSplit, String parmSplit, boolean ignoreNull, boolean enableSort, boolean asc) {
        if (obj == null) {
            return null;
        }
        if (valueSplit == null) {
            valueSplit = EMPTY_STRING;
        }
        if (parmSplit == null) {
            parmSplit = EMPTY_STRING;
        }

        StringBuilder sbForm = new StringBuilder();

        Field[] fields = clazz.getFields();

        if (enableSort) {
            if (asc) {
                Arrays.sort(fields);
            } else {
                Arrays.sort(fields, Collections.reverseOrder());
            }
        }

        for (Field f : fields) {
            String key = f.getName();
            Object value = null;
            try {
                value = f.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            if (value == null) {
                value = EMPTY_STRING;
            }

            if (ignoreNull && value.toString().length() <= 0) {
                continue;
            }

            sbForm.append(key);
            sbForm.append(valueSplit);
            sbForm.append(value);
            sbForm.append(parmSplit);
        }

        return sbForm.substring(0, sbForm.length() - parmSplit.length());
    }
}
