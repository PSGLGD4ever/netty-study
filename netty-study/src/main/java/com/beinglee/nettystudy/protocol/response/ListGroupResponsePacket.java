package com.beinglee.nettystudy.protocol.response;

import com.beinglee.nettystudy.protocol.Command;
import com.beinglee.nettystudy.protocol.Packet;
import com.beinglee.nettystudy.server.Session;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author zhanglu
 * @date 2020/5/28 20:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ListGroupResponsePacket extends Packet {

    private List<Session> sessionList;

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_RESPONSE;
    }

}
