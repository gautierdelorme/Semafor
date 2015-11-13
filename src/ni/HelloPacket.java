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
 *
 * @author gautier
 */
public class HelloPacket extends UDPPacket{
    private final boolean reqReply;
    private final String nickname;
    
    public HelloPacket(boolean reqReply, String nickname) {
        super(typeMessage.HELLO);
        this.reqReply = reqReply;
        this.nickname = nickname;
    }
    
    public HelloPacket(JSONObject json) {
        super(json);
        this.nickname = json.getString("nickname");
        this.reqReply = json.getBoolean("reqReply");
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isReqReply() {
        return reqReply;
    }
    
    @Override
    public JSONObject toJSON() {
        JSONObject obj = super.toJSON();
        obj.put("reqReply", reqReply);
        obj.put("nickname", nickname);
        return obj;
    }
}
