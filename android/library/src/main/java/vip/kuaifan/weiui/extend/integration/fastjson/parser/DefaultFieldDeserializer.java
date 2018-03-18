package vip.kuaifan.weiui.extend.integration.fastjson.parser;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map;

import vip.kuaifan.weiui.extend.integration.fastjson.parser.DefaultJSONParser.ResolveTask;
import vip.kuaifan.weiui.extend.integration.fastjson.parser.deserializer.FieldDeserializer;
import vip.kuaifan.weiui.extend.integration.fastjson.parser.deserializer.ObjectDeserializer;
import vip.kuaifan.weiui.extend.integration.fastjson.serializer.DateCodec;
import vip.kuaifan.weiui.extend.integration.fastjson.util.FieldInfo;
import vip.kuaifan.weiui.extend.integration.fastjson.util.ParameterizedTypeImpl;
import vip.kuaifan.weiui.extend.integration.fastjson.util.TypeUtils;

public class DefaultFieldDeserializer extends FieldDeserializer {

    protected ObjectDeserializer fieldValueDeserilizer;

    public DefaultFieldDeserializer(ParserConfig mapping, Class<?> clazz, FieldInfo fieldInfo){
        super(clazz, fieldInfo, JSONToken.LITERAL_INT);
    }
    
    public ObjectDeserializer getFieldValueDeserilizer(ParserConfig config) {
        if (fieldValueDeserilizer == null) {
            fieldValueDeserilizer = config.getDeserializer(fieldInfo.fieldClass, fieldInfo.fieldType);
        }

        return fieldValueDeserilizer;
    }

    @Override
    public void parseField(DefaultJSONParser parser, Object object, Type objectType, Map<String, Object> fieldValues) {
        if (this.fieldValueDeserilizer == null) {
            this.fieldValueDeserilizer = parser.config.getDeserializer(fieldInfo.fieldClass, fieldInfo.fieldType);
        }

        Type fieldType = fieldInfo.fieldType;
        if (objectType instanceof ParameterizedType) {
            ParseContext objContext = parser.contex;

            if (objContext != null) {
                objContext.type = objectType;
            }

            fieldType = FieldInfo.getFieldType(this.clazz, objectType, fieldType);
            fieldValueDeserilizer = parser.config.getDeserializer(fieldType);
        }

        if (fieldType instanceof ParameterizedType
                && objectType instanceof ParameterizedType) {
            ParameterizedType fieldParamType = (ParameterizedType) fieldType;
            ParameterizedType objectParamType = (ParameterizedType) objectType;

            Type[] args = fieldParamType.getActualTypeArguments();


            Type rawType = objectParamType.getRawType();
            if (rawType instanceof Class) {
                Class<?> rawClass = (Class) rawType;
                TypeVariable[] typeParameters = rawClass.getTypeParameters();
                boolean changed = TypeUtils.getArgument(args, typeParameters, objectParamType.getActualTypeArguments());
                if (changed) {
                    fieldType = new ParameterizedTypeImpl(args, fieldParamType.getOwnerType(), fieldParamType.getRawType());
                }
            }
        }
        String format = fieldInfo.format;
        Object value;
        if (format != null && fieldValueDeserilizer instanceof DateCodec) {
            value = ((DateCodec) fieldValueDeserilizer).deserialze(parser, fieldType, fieldInfo.name, format);
        } else {
            value = fieldValueDeserilizer.deserialze(parser, fieldType, fieldInfo.name);
        }
        if (parser.resolveStatus == DefaultJSONParser.NeedToResolve) {
            ResolveTask task = parser.getLastResolveTask();
            task.fieldDeserializer = this;
            task.ownerContext = parser.contex;
            parser.resolveStatus = DefaultJSONParser.NONE;
        } else {
            if (object == null) {
                fieldValues.put(fieldInfo.name, value);
            } else {
                if (value == null) {
                    Class<?> fieldClass = fieldInfo.fieldClass;
                    if (fieldClass == byte.class // 
                            || fieldClass == short.class //
                            || fieldClass == float.class //
                            || fieldClass == double.class) {
                        return;
                    }
                }
                
                setValue(object, value);
            }
        }
    }
}
