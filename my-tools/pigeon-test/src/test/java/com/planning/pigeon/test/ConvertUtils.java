package com.planning.pigeon.test;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yxc
 * @date 2021/12/15 3:26 下午
 */
public class ConvertUtils {

    static ObjectMapper mapper = new ObjectMapper();

    public static String serializeObject(Object obj) throws Exception {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    }

    public static <T> T deserializeCollection(String content, Class<?> collectionClass, Class<?>... componentType)
            throws Exception {
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(collectionClass, componentType);
            return (T) mapper.readValue(content, javaType);
        } catch (Throwable e) {
            throw new Exception(e);
        }
    }

    public static <T> T deserializeObject(Class<T> objType, String content) throws Exception {
        try {
            return convertObject(mapper.readValue(content, objType));
        } catch (Throwable e) {
            throw new Exception(e);
        }
    }

    public static <T> T convertObject(T obj) throws Exception {
        if (obj == null) {
            return null;
        }
        if (obj instanceof LinkedHashMap) {
            LinkedHashMap map = (LinkedHashMap) obj;
            return (T) convertMap(map);
        } else if (obj instanceof Collection) {
            Collection list = (Collection) obj;
            Collection newList = (Collection) Class.forName(obj.getClass().getName()).newInstance();
            if (!list.isEmpty()) {
                int i = 0;
                String componentType = null;
                for (Iterator ir = list.iterator(); ir.hasNext(); ) {
                    Object o = ir.next();
                    if (o instanceof LinkedHashMap) {
                        String cls = (String) ((Map) o).get("@class");
                        if (StringUtils.isNotBlank(cls)) {
                            componentType = cls;
                        } else if (componentType != null) {
                            ((Map) o).put("@class", componentType);
                        }
                    }
                    newList.add(convertObject(o));
                    i++;
                }
                return (T) newList;
            }
        } else if (obj.getClass().isArray()) {
            int len = Array.getLength(obj);
            for (int i = 0; i < len; i++) {
                Object o = Array.get(obj, i);
                String componentType = null;
                if (o instanceof LinkedHashMap) {
                    String cls = (String) ((Map) o).get("@class");
                    if (StringUtils.isNotBlank(cls)) {
                        componentType = cls;
                    } else if (componentType != null) {
                        ((Map) o).put("@class", componentType);
                    }
                }
                Array.set(obj, i, convertObject(o));
            }
            return obj;
        } else if (obj instanceof Map) {
            Map map = (Map) obj;
            if (!map.isEmpty()) {
                Map finalMap = new HashMap(map.size());
                for (Iterator ir = map.keySet().iterator(); ir.hasNext(); ) {
                    Object k = ir.next();
                    Object v = map.get(k);
                    Object finalKey = convertObject(k);
                    Object finalValue = convertObject(v);
                    finalMap.put(finalKey, finalValue);
                }
                return (T) finalMap;
            }
        }
        return obj;
    }


    public static Object convertMap(LinkedHashMap map) throws Exception {
        String type = (String) map.get("@class");
        if (StringUtils.isNotBlank(type)) {
            Class clazz = Class.forName(type);
            Object obj = clazz.newInstance();
            for (Iterator ir = map.keySet().iterator(); ir.hasNext(); ) {
                String key = (String) ir.next();
                Object value = map.get(key);
                if (!key.equals("@class")) {
                    Field field = ReflectUtils.getDeclaredField(clazz, key, true);
                    if (field != null) {
                        setFieldValue(obj, field.getType(), key, value);
                    }
                }
            }
            return obj;
        }
        return map;
    }

    private static void setFieldValue(Object obj, Class<?> fieldType, String fieldName, Object fieldValue)
            throws Exception {
        try {
            ReflectUtils.writeDeclaredField(obj, fieldName, convertObject(fieldValue), true);
        } catch (IllegalArgumentException e) {
            ReflectUtils.writeDeclaredField(obj, fieldName,
                    mapper.readValue(String.valueOf(fieldValue), fieldType), true);
        }
    }

    private static Object[] formParameters(Class<?>[] types, String[] values) throws Exception,
            ClassNotFoundException {
        if (types == null || types.length == 0) {
            return new Object[0];
        }
        Object[] valueObjs = new Object[types.length];
        ;
        if (values == null) {
            valueObjs = new Object[0];
        } else {
            for (int i = 0; i < values.length; i++) {
                valueObjs[i] = toObject(types[i], values[i]);
            }
        }
        return valueObjs;
    }


    public static Object toObject(Class<?> type, String value) throws Exception, ClassNotFoundException {
        if (value == null) {
            return null;
        }
        String value_;
        if (value.length() == 0) {
            value_ = "0";
        } else {
            value_ = value;
        }
        Object valueObj = value_;
        if (type == int.class || type == Integer.class) {
            valueObj = Integer.parseInt(value_);
        } else if (type == short.class || type == Short.class) {
            valueObj = Short.parseShort(value_);
        } else if (type == byte.class || type == Byte.class) {
            valueObj = Byte.parseByte(value_);
        } else if (type == char.class) {
            valueObj = value_;
        } else if (type == long.class || type == Long.class) {
            valueObj = Long.parseLong(value_);
        } else if (type == float.class || type == Float.class) {
            valueObj = Float.parseFloat(value_);
        } else if (type == double.class || type == Double.class) {
            valueObj = Double.parseDouble(value_);
        } else if (type == String.class) {
            valueObj = String.valueOf(value);
        } else {
            if (value == null || value.length() == 0) {
                valueObj = null;
            } else {
                valueObj = deserializeObject(type, value);
                if (valueObj instanceof Collection) {
                    Collection valueObjList = (Collection) valueObj;
                    if (!valueObjList.isEmpty()) {
                        Object first = valueObjList.iterator().next();
                        if (first instanceof Map) {
                            Map valueMap = (Map) first;
                            String valueClass = (String) valueMap.get("@class");
                            if (valueClass != null) {
                                valueObj = deserializeCollection(value, type, Class.forName(valueClass));
                            }
                        }
                    }
                } else if (valueObj instanceof Map) {
                    Map valueObjList = (Map) valueObj;
                    if (!valueObjList.isEmpty()) {
                        Map finalMap = new HashMap(valueObjList.size());
                        valueObj = finalMap;
                        String keyClass = null;
                        String valueClass = null;
                        try {
                            for (Iterator ir = valueObjList.keySet().iterator(); ir.hasNext(); ) {
                                Object k = ir.next();
                                Object v = valueObjList.get(k);
                                Object finalKey = k;
                                Object finalValue = v;
                                if (k instanceof String) {
                                    try {
                                        finalKey = deserializeObject(Map.class, (String) k);
                                    } catch (Throwable t) {
                                        if (keyClass == null) {
                                            Map firstValueMap = deserializeObject(Map.class, (String) k);
                                            if (firstValueMap != null) {
                                                keyClass = (String) firstValueMap.get("@class");
                                            }
                                        }
                                        if (keyClass != null) {
                                            finalKey = deserializeObject(Class.forName(keyClass), (String) k);
                                        }
                                    }
                                }
                                if (v instanceof String) {
                                    try {
                                        finalValue = deserializeObject(Map.class, (String) v);
                                    } catch (Throwable t) {
                                        if (valueClass == null) {
                                            Map firstValueMap = deserializeObject(Map.class, (String) v);
                                            if (firstValueMap != null) {
                                                valueClass = (String) firstValueMap.get("@class");
                                            }
                                        }
                                        if (valueClass != null) {
                                            finalValue = deserializeObject(Class.forName(valueClass), (String) v);
                                        }
                                    }
                                }
                                finalMap.put(finalKey, finalValue);
                            }
                        } catch (Throwable t) {
                            valueObj = valueObjList;
                        }
                    }
                }
            }
        }
        return valueObj;
    }


}
