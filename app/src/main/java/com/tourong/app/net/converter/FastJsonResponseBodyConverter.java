package com.tourong.app.net.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class FastJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private static final Feature[] EMPTY_SERIALIZER_FEATURES = new Feature[0];

    private Type mType;

    private ParserConfig config;
    private int featureValues;
    private Feature[] features;

    FastJsonResponseBodyConverter(Type type, ParserConfig config, int featureValues,
                                  Feature... features) {
        mType = type;
        this.config = config;
        this.featureValues = featureValues;
        this.features = features;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            T data = JSON.parseObject(value.string(), mType, config, featureValues,
                    features != null ? features : EMPTY_SERIALIZER_FEATURES);
            // 可以根据自身业务做处理
            return data;
        } catch (JSONException e) {
            e.printStackTrace();
            throw new JSONException("数据格式异常");
        } finally {
            value.close();
        }
    }
}