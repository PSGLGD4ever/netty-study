package com.beinglee.nettystudy.protocol.response;

import com.beinglee.nettystudy.protocol.Command;
import com.beinglee.nettystudy.protocol.Packet;
import lombok.Data;

@Data
public class SendToGroupResponsePacket extends Packet {

    private String message;

    private String sendUserId;

    private String sendUsrName;

    @Override
    public Byte getCommand() {
        return Command.SEND_TO_GROUP_RESPONSE;
    }

}
