package com.beinglee.nettystudy.protocol;

import com.beinglee.nettystudy.protocol.request.LoginRequestPacket;
import com.beinglee.nettystudy.protocol.request.MsgRequestPacket;
import com.beinglee.nettystudy.protocol.response.LoginResponsePacket;
import com.beinglee.nettystudy.protocol.response.MsgResponsePacket;
import com.beinglee.nettystudy.protocol.serializer.JsonSerializer;
import com.beinglee.nettystudy.protocol.serializer.Serializer;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Luz
 */
public class PacketCodeC {

    public static final int MAGIC_NUMBER = 0x12345678;

    private static final Map<Byte, Class<? extends Packet>> packetTypes;

    private static final Map<Byte, Serializer> serializerTypes;

    private static final PacketCodeC INSTANCE = new PacketCodeC();

    static {
        packetTypes = new HashMap<>();
        packetTypes.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypes.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypes.put(Command.MSG_REQUEST, MsgRequestPacket.class);
        packetTypes.put(Command.MSG_RESPONSE, MsgResponsePacket.class);

        serializerTypes = new HashMap<>();
        Serializer serializer = new JsonSerializer();
        serializerTypes.put(serializer.getSerializerAlgorithm(), serializer);
    }

    public void encode(ByteBuf buffer, Packet packet) {
        // 序列化
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        // 魔法数字
        buffer.writeInt(MAGIC_NUMBER);
        // 版本号
        buffer.writeByte(packet.getVersion());
        // 序列化算法
        buffer.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        // 指令
        buffer.writeByte(packet.getCommand());
        // 数据长度
        buffer.writeInt(bytes.length);
        // 数据
        buffer.writeBytes(bytes);
    }

    public Packet decode(ByteBuf byteBuf) {
        // 跳过魔法数字 int占用四个字节
        byteBuf.skipBytes(4);
        // 跳过版本号
        byteBuf.skipBytes(1);
        // 算法
        byte algorithm = byteBuf.readByte();
        // 指令
        byte command = byteBuf.readByte();
        // 数据长度
        int dataLength = byteBuf.readInt();
        // 读取数据
        byte[] bytes = new byte[dataLength];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> packetType = this.getPacketType(command);
        Serializer serializer = this.getSerializer(algorithm);
        if (packetType != null && serializer != null) {
            return serializer.deserialize(bytes, packetType);
        }
        return null;
    }

    public static PacketCodeC getInstance() {
        return INSTANCE;
    }

    private Class<? extends Packet> getPacketType(byte command) {
        return packetTypes.get(command);
    }

    private Serializer getSerializer(byte algorithm) {
        return serializerTypes.get(algorithm);
    }

}
