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
class MessagePacket extends UDPPacket {
    private final String message;

    protected MessagePacket(String message) {
        super(typeMessage.MESSAGE);
        this.message = message;
    }
    
    protected MessagePacket(JSONObject json) {
        super(json);
        this.message = json.getString("message");
    }

    protected String getMessage() {
        return message;
    }
    
    @Override
    protected JSONObject toJSON() {
        JSONObject obj = super.toJSON();
        obj.put("message", message);
        return obj;
    }
}
