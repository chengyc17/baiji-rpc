package com.baiji.gen.java;

import com.baiji.common.dtype.BaijiIDLDataType;

import java.util.HashMap;
import java.util.Map;

public interface BaijiJavaDataTypeMap {

    Map<String, String> D_MAP = new HashMap<>() {{
        put(BaijiIDLDataType.Baiji_String, "String");
        put(BaijiIDLDataType.Baiji_Int16, "Short");
        put(BaijiIDLDataType.Baiji_Int32, "Integer");
        put(BaijiIDLDataType.Baiji_Float, "Float");
        put(BaijiIDLDataType.Baiji_Double, "Double");
        put(BaijiIDLDataType.Baiji_Boolean, "Boolean");
        put(BaijiIDLDataType.Baiji_Map, "Map");
        put(BaijiIDLDataType.Baiji_List, "List");
    }};
}
