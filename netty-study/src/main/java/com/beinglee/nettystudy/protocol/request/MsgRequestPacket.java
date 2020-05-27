package com.beinglee.nettystudy.protocol.request;

import com.beinglee.nettystudy.protocol.Command;
import com.beinglee.nettystudy.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MsgRequestPacket extends Packet {

    private String message;

    private String toUserId;

    @Override
    public Byte getCommand() {
        return Command.MSG_REQUEST;
    }
}
