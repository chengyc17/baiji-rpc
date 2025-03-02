package com.baiji.gen.java;

import com.baiji.common.dtype.BaijiIDLDataType;

import java.util.HashMap;
import java.util.Map;

public class BaijiJavaDataTypeMap {

    private static Map<String, String> D_MAP = new HashMap<>() {{
        put(BaijiIDLDataType.Baiji_String, "String");
        put(BaijiIDLDataType.Baiji_Int16, "Short");
        put(BaijiIDLDataType.Baiji_Int32, "Integer");
        put(BaijiIDLDataType.Baiji_Float, "Float");
        put(BaijiIDLDataType.Baiji_Double, "Double");
        put(BaijiIDLDataType.Baiji_Boolean, "Boolean");
        put(BaijiIDLDataType.Baiji_Map, "Map");
        put(BaijiIDLDataType.Baiji_List, "List");
    }};

    public static String getMapType(String baijiType) {
        if (D_MAP.containsKey(baijiType)) {
            return D_MAP.get(baijiType);
        }
        return baijiType;
        //throw new IllegalArgumentException("Undefined Type");
    }

}
