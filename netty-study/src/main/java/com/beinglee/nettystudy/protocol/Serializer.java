package com.beinglee.nettystudy.protocol;

public interface Serializer {

    Serializer DEFAULT = new JsonSerializer();

    byte getSerializerAlgorithm();

    byte[] serialize(Object obj);

    <T> T deserialize(byte[] bytes, Class<T> clazz);

}
