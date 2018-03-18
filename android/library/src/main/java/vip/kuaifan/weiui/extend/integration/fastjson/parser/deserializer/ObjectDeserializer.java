package vip.kuaifan.weiui.extend.integration.fastjson.parser.deserializer;

import java.lang.reflect.Type;

import vip.kuaifan.weiui.extend.integration.fastjson.parser.DefaultJSONParser;

public interface ObjectDeserializer {
    <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName);
}
