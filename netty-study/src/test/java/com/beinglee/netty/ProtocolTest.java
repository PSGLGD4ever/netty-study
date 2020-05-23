package com.beinglee.netty;

import com.beinglee.nettystudy.protocol.JsonSerializer;
import com.beinglee.nettystudy.protocol.LoginRequestPacket;
import com.beinglee.nettystudy.protocol.PacketCodeC;
import com.beinglee.nettystudy.protocol.Serializer;
import io.netty.buffer.ByteBuf;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;


public class ProtocolTest {

    @Test
    public void encode() {
        LoginRequestPacket loginPacket = new LoginRequestPacket();
        String userId = UUID.randomUUID().toString();
        loginPacket.setVersion((byte) 1);
        loginPacket.setUserId(userId);
        loginPacket.setUserName("beingLee");
        loginPacket.setPassword("p@ssword");

        ByteBuf encodePacket = PacketCodeC.getInstance().encode(loginPacket);
        LoginRequestPacket decodedPacket = (LoginRequestPacket) PacketCodeC.getInstance().decode(encodePacket);

        Assert.assertEquals(loginPacket.getUserId(), decodedPacket.getUserId());
        Assert.assertEquals(loginPacket.getUserName(), decodedPacket.getUserName());
        Assert.assertEquals(loginPacket.getPassword(), decodedPacket.getPassword());
        Assert.assertEquals(loginPacket.getVersion(), decodedPacket.getVersion());

        Serializer serializer = new JsonSerializer();
        Assert.assertArrayEquals(serializer.serialize(loginPacket), serializer.serialize(decodedPacket));
    }

}
