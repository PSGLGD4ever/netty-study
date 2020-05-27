package com.beinglee.nettystudy.protocol.response;

import com.beinglee.nettystudy.protocol.Command;
import com.beinglee.nettystudy.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MsgResponsePacket extends Packet {

    private String message;

    private String fromUserId;

    private String fromUserName;

    @Override
    public Byte getCommand() {
        return Command.MSG_RESPONSE;
    }
}
