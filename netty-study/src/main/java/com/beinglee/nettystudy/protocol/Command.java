package com.beinglee.nettystudy.protocol;

public abstract class Command {

    public static final byte LOGIN_REQUEST = 1;

    public static final byte LOGIN_RESPONSE = 2;

    public static final byte MSG_REQUEST = 3;

    public static final byte MSG_RESPONSE = 4;

    public static final byte LOGOUT_REQUEST = 5;

    public static final byte LOGOUT_RESPONSE = 6;

    public static final byte CREATE_GROUP_REQUEST = 7;

    public static final byte CREATE_GROUP_RESPONSE = 8;

    public static final byte LIST_GROUP_REQUEST = 9;

    public static final byte LIST_GROUP_RESPONSE = 10;

    public static final byte JOIN_GROUP_REQUEST = 11;

    public static final byte JOIN_GROUP_RESPONSE = 12;

    public static final byte QUIT_GROUP_REQUEST = 13;

    public static final byte QUIT_GROUP_RESPONSE = 14;

    public static final byte SEND_TO_GROUP_REQUEST = 15;

    public static final byte SEND_TO_GROUP_RESPONSE = 16;

}
