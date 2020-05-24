package com.beinglee.nettystudy.protocol.packet;

import com.beinglee.nettystudy.protocol.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginResponsePacket extends Packet {

    private Boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
