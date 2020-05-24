package com.beinglee.nettystudy.protocol.packet;

import com.beinglee.nettystudy.protocol.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginRequestPacket extends Packet {

    private String userId;

    private String userName;

    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
