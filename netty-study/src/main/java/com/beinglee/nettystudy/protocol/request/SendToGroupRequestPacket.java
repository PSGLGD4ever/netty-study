package com.beinglee.nettystudy.protocol.request;

import com.beinglee.nettystudy.protocol.Command;
import com.beinglee.nettystudy.protocol.Packet;
import lombok.Data;

@Data
public class SendToGroupRequestPacket extends Packet {

    private String groupId;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.SEND_TO_GROUP_REQUEST;
    }
}
