package com.baiji.ecust;

import java.util.Map;
import java.util.List;
import java.util.Objects;

/**
* Hello2Response 实体类
*/
public class Hello2Response {
        private String name;
        private Short myInt16;
        private Integer myInt32;
        private Float myFloat;
        private Double myDouble;
        private Boolean myBoolean;
        private Map<Short,Double> myMap;
        private List<Integer> myList;
        private Hello2Request req;

        /**
        * 获取 name
        * @return name
        */
        public String getName() {
        return name;
        }

        /**
        * 设置 name
        * @param name name
        */
        public void setName(String name) {
        this.name = name;
        }
        /**
        * 获取 myInt16
        * @return myInt16
        */
        public Short getMyInt16() {
        return myInt16;
        }

        /**
        * 设置 myInt16
        * @param myInt16 myInt16
        */
        public void setMyInt16(Short myInt16) {
        this.myInt16 = myInt16;
        }
        /**
        * 获取 myInt32
        * @return myInt32
        */
        public Integer getMyInt32() {
        return myInt32;
        }

        /**
        * 设置 myInt32
        * @param myInt32 myInt32
        */
        public void setMyInt32(Integer myInt32) {
        this.myInt32 = myInt32;
        }
        /**
        * 获取 myFloat
        * @return myFloat
        */
        public Float getMyFloat() {
        return myFloat;
        }

        /**
        * 设置 myFloat
        * @param myFloat myFloat
        */
        public void setMyFloat(Float myFloat) {
        this.myFloat = myFloat;
        }
        /**
        * 获取 myDouble
        * @return myDouble
        */
        public Double getMyDouble() {
        return myDouble;
        }

        /**
        * 设置 myDouble
        * @param myDouble myDouble
        */
        public void setMyDouble(Double myDouble) {
        this.myDouble = myDouble;
        }
        /**
        * 获取 myBoolean
        * @return myBoolean
        */
        public Boolean getMyBoolean() {
        return myBoolean;
        }

        /**
        * 设置 myBoolean
        * @param myBoolean myBoolean
        */
        public void setMyBoolean(Boolean myBoolean) {
        this.myBoolean = myBoolean;
        }
        /**
        * 获取 myMap
        * @return myMap
        */
        public Map<Short,Double> getMyMap() {
        return myMap;
        }

        /**
        * 设置 myMap
        * @param myMap myMap
        */
        public void setMyMap(Map<Short,Double> myMap) {
        this.myMap = myMap;
        }
        /**
        * 获取 myList
        * @return myList
        */
        public List<Integer> getMyList() {
        return myList;
        }

        /**
        * 设置 myList
        * @param myList myList
        */
        public void setMyList(List<Integer> myList) {
        this.myList = myList;
        }
        /**
        * 获取 req
        * @return req
        */
        public Hello2Request getReq() {
        return req;
        }

        /**
        * 设置 req
        * @param req req
        */
        public void setReq(Hello2Request req) {
        this.req = req;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hello2Response that = (Hello2Response) o;
        return
            Objects.equals(name, that.name)
            &&            Objects.equals(myInt16, that.myInt16)
            &&            Objects.equals(myInt32, that.myInt32)
            &&            Objects.equals(myFloat, that.myFloat)
            &&            Objects.equals(myDouble, that.myDouble)
            &&            Objects.equals(myBoolean, that.myBoolean)
            &&            Objects.equals(myMap, that.myMap)
            &&            Objects.equals(myList, that.myList)
            &&            Objects.equals(req, that.req);
    }

    @Override
    public int hashCode() {
            return Objects.hash(name, myInt16, myInt32, myFloat, myDouble, myBoolean, myMap, myList, req);
    }

    @Override
    public String toString() {
        return "Hello2Response{" +
            "name=" + name +
            ", " +            "myInt16=" + myInt16 +
            ", " +            "myInt32=" + myInt32 +
            ", " +            "myFloat=" + myFloat +
            ", " +            "myDouble=" + myDouble +
            ", " +            "myBoolean=" + myBoolean +
            ", " +            "myMap=" + myMap +
            ", " +            "myList=" + myList +
            ", " +            "req=" + req +
            '}'
        ;
    }
}
