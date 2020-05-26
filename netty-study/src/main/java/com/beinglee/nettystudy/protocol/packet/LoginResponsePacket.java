package com.beinglee.nettystudy.protocol.packet;

import com.beinglee.nettystudy.protocol.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Luz
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginResponsePacket extends Packet {

    private Boolean success;

    private String reason;

    private String userId;

    private String userName;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
