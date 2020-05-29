package com.beinglee.nettystudy.protocol.response;

import com.beinglee.nettystudy.protocol.Command;
import com.beinglee.nettystudy.protocol.Packet;
import com.beinglee.nettystudy.server.Session;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author zhanglu
 * @date 2020/5/29 11:42
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JoinGroupResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessions;

    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_RESPONSE;
    }
}
