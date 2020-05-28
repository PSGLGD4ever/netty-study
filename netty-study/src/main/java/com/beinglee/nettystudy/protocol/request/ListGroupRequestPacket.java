package com.beinglee.nettystudy.protocol.request;

import com.beinglee.nettystudy.protocol.Command;
import com.beinglee.nettystudy.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhanglu
 * @date 2020/5/28 20:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ListGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_REQUEST;
    }
}
