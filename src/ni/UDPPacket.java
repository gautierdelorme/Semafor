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
public class UDPPacket {

    public enum typeMessage {HELLO, BYE, MESSAGE, FILE_REQUEST, FILE_REQUEST_RESPONSE};
    private final typeMessage type;

    public UDPPacket(typeMessage type) {
        this.type = type;
    }
    
    public UDPPacket(JSONObject json) {
        this.type = typeMessage.values()[json.getInt("type")];
    }

    public typeMessage getType() {
        return type;
    }

    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("type", type.ordinal());
        return obj;
    }

    @Override
    public String toString() {
        return this.toJSON().toString();
    }
}
