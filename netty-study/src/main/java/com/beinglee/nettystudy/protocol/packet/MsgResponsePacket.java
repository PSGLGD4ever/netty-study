package com.beinglee.nettystudy.protocol.packet;

import com.beinglee.nettystudy.protocol.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MsgResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MSG_RESPONSE;
    }
}
