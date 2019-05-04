/*
 * Copyright 2012 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package hello.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Map;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * JSON Mapper
 * 
 * @author jade (originally)
 * @author zhe.yangz imported.
 */
public class JsonMapper {

    public static final String        DATE_FORMAT = "yyyyMMddHHmmssSSSZ";
    private static final JsonFactory jf          = new JsonFactory();
    static {
        jf.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        jf.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }
    private static final ObjectMapper m           = new ObjectMapper(jf);
//    static {
//        SerializationConfig sf = m.getSerializationConfig();
//        m.setSerializationConfig(sf.with(Feature.USE_ANNOTATIONS).withDateFormat(new SimpleDateFormat(
//                                                                                                                          DATE_FORMAT)));
//        DeserializationConfig df = m.getDeserializationConfig();
//        m.setDeserializationConfig(df.with(DeserializationConfig.Feature.USE_ANNOTATIONS).withDateFormat(new SimpleDateFormat(
//                                                                                                                              DATE_FORMAT)));
//    }

    public static <T> T json2pojo(String jsonAsString, Class<T> pojoClass) throws JsonMappingException,
            JsonParseException, IOException {
        return m.readValue(jsonAsString, pojoClass);
    }

    public static Map<?, ?> json2map(String jsonAsString) throws JsonMappingException, JsonParseException, IOException {
        return m.readValue(jsonAsString, Map.class);
    }

    public static Map<?, ?> json2map(InputStream istream) throws JsonMappingException, JsonParseException, IOException {
        return m.readValue(istream, Map.class);
    }

    public static JsonNode json2node(String jsonAsString) throws JsonProcessingException, IOException {
        return m.readTree(jsonAsString);
    }

    public static JsonNode json2node(InputStream istream) throws JsonProcessingException, IOException {
        return m.readTree(istream);
    }

    public static JsonNode json2node(Reader reader) throws JsonProcessingException, IOException {
        return m.readTree(reader);
    }

    public static <T> T json2value(Reader reader, Class<T> type) throws IOException, JsonParseException,
                                                                JsonMappingException {
        return m.readValue(reader, type);
    }

    public static Map<?, ?> node2map(JsonNode json) throws JsonProcessingException, IOException {
        if (json == null) {
            return null;
        }
        JsonParser jp = null;
        try {
            jp = json.traverse();
            return m.readValue(jp, Map.class);
        } finally {
            if (jp != null) {
                try {
                    jp.close();
                } catch (IOException ioe) {
                }
            }
        }
    }

    public static <T> T node2pojo(JsonNode json, Class<T> pojoClass) throws JsonProcessingException, IOException {
        if (json == null) {
            return null;
        }
        JsonParser jp = null;
        try {
            jp = json.traverse();
            return m.readValue(jp, pojoClass);
        } finally {
            if (jp != null) {
                try {
                    jp.close();
                } catch (IOException ioe) {
                }
            }
        }
    }

    public static void pojo2Json(Object pojo, Writer w) throws JsonGenerationException, JsonMappingException,
                                                       IOException {
        JsonGenerator jg = null;
        try {
            jg = jf.createJsonGenerator(w);
            m.writeValue(jg, pojo);
        } finally {
            if (jg != null) {
                try {
                    jg.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    public static String pojo2json(Object pojo) throws JsonGenerationException, JsonMappingException, IOException {
        final StringWriter sw = new StringWriter();
        JsonGenerator jg = null;
        try {
            jg = jf.createJsonGenerator(sw);
            m.writeValue(jg, pojo);
            return sw.toString();
        } finally {
            if (jg != null) {
                try {
                    jg.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    public static String node2json(JsonNode node) throws JsonProcessingException, IOException {
        final StringWriter sw = new StringWriter();
        JsonGenerator jg = null;
        try {
            jg = jf.createJsonGenerator(sw);
            m.writeTree(jg, node);
            return sw.toString();
        } finally {
            if (jg != null) {
                try {
                    jg.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    public static void node2json(JsonNode node, Writer w) throws JsonGenerationException, JsonMappingException,
                                                         IOException {
        JsonGenerator jg = null;
        try {
            jg = jf.createJsonGenerator(w);
            m.writeTree(jg, node);
        } finally {
            if (jg != null) {
                try {
                    jg.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    public static ObjectNode createObjectNode() {
        return m.createObjectNode();
    }

    public static ArrayNode createArrayNode() {
        return m.createArrayNode();
    }

    public static JsonNode parser2node(JsonParser jp) throws JsonProcessingException, IOException {
        return m.readTree(jp);
    }

}
