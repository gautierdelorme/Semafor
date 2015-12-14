/*
 * Semafor - MIT License
 *
 * A peer-to-peer chat system
 *
 * Copyright 2015 Gautier Delorme and Arthur Papailhau.
 *
 */
package ni;

import org.json.JSONObject;

/**
 * UDP Packet representing a hello message
 *
 * @author gautier
 */
class HelloPacket extends UDPPacket{
    private final boolean reqReply;
    private final String nickname;
    
    protected HelloPacket(boolean reqReply, String nickname) {
        super(typeMessage.HELLO);
        this.reqReply = reqReply;
        this.nickname = nickname;
    }
    
    protected HelloPacket(JSONObject json) {
        super(json);
        this.nickname = json.getString("nickname");
        this.reqReply = json.getBoolean("reqReply");
    }

    protected String getNickname() {
        return nickname;
    }

    protected boolean isReqReply() {
        return reqReply;
    }
    
    @Override
    protected JSONObject toJSON() {
        JSONObject obj = super.toJSON();
        obj.put("reqReply", reqReply);
        obj.put("nickname", nickname);
        return obj;
    }
}
