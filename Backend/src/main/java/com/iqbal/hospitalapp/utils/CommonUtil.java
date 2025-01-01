package com.iqbal.hospitalapp.utils;

import javax.persistence.Tuple;
import javax.persistence.TupleElement;
import java.util.HashMap;
import java.util.Map;

public class CommonUtil {
    public static Map<String, Object> tupleToMap(Tuple tuple) {
        Map<String, Object> map = new HashMap<>();
        for(TupleElement tupleElement: tuple.getElements()) {
            String alias = tupleElement.getAlias();
            map.put(alias, tuple.get(alias));
        }
        return map;
    }
}
