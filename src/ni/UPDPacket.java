/*
 * Semafor - MIT License
 *
 * A peer-to-peer chat system
 *
 * Copyright 2015 Gautier Delorme and Arthur Papailhau.
 *
 */
package ni;

import org.json.*;

/**
 *
 * @author gautier
 */
public class UPDPacket {

    public enum typeMessage {HELLO, BYE, MESSAGE};
    private final typeMessage type;
    private final String nickname;
    private final boolean reqReply;

    public UPDPacket(typeMessage type, String nickname, boolean reqReply) {
        this.type = type;
        this.nickname = nickname;
        this.reqReply = reqReply;
    }
    
    public UPDPacket(typeMessage type, boolean reqReply) {
        this.type = type;
        this.nickname = "";
        this.reqReply = true;
    }
    
    public UPDPacket(String json) {
        JSONObject obj = new JSONObject(json);
        this.type = typeMessage.values()[obj.getInt("type")];
        this.nickname = obj.getString("nickname");
        this.reqReply = obj.getBoolean("reqReply");
    }

    public typeMessage getType() {
        return type;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isReqReply() {
        return reqReply;
    }
    
    private JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("type", type.ordinal());
        obj.put("nickname", nickname);
        obj.put("reqReply", reqReply);
        return obj;
    }

    @Override
    public String toString() {
        return this.toJSON().toString();
    }
}
