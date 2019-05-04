package hello.utils;

import java.util.Map;

import org.apache.log4j.Logger;


public class JsonUtils {

    private static String TAG = "JsonUtils";

    /**
     * 转换JSON字符串为JAVA对象
     *
     * @param retString
     * @param returnType
     * @return
     */
    public static Object jsonToObject(String retString, Class<?> returnType) {
        try {
            if (String.class.equals(returnType)) { // the result return raw string
                return retString;
            } else { // return object using json decode
                return JsonMapper.json2pojo(retString, returnType);
            }

        } catch (Exception e) {
            Logger.getInstance(JsonUtils.TAG).debug(e.toString());
            return "";
        }
    }

}
