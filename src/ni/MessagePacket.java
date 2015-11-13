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
public class MessagePacket extends UDPPacket {
    private final String message;

    public MessagePacket(String message) {
        super(typeMessage.MESSAGE);
        this.message = message;
    }
    
    public MessagePacket(JSONObject json) {
        super(json);
        this.message = json.getString("message");
    }

    public String getMessage() {
        return message;
    }
    
    @Override
    public JSONObject toJSON() {
        JSONObject obj = super.toJSON();
        obj.put("message", message);
        return obj;
    }
}
