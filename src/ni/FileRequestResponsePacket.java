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
public class FileRequestResponsePacket extends UDPPacket {
    private final boolean ok;
    private final int timestamp;
    
    public FileRequestResponsePacket(boolean ok, int timestamp) {
        super(typeMessage.FILE_REQUEST_RESPONSE);
        this.ok = ok;
        this.timestamp = timestamp;
    }
    
    public FileRequestResponsePacket(JSONObject json) {
        super(json);
        this.ok = json.getBoolean("ok");
        this.timestamp = json.getInt("timestamp");
    }
    
    public boolean getOk() {
        return this.ok;
    }
    
    public int getTimestamp() {
        return timestamp;
    }
    
    @Override
    public JSONObject toJSON() {
        JSONObject obj = super.toJSON();
        obj.put("ok", ok);
        obj.put("timestamp", timestamp);
        return obj;
    }
}
