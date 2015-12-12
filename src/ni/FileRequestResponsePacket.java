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
class FileRequestResponsePacket extends UDPPacket {
    private final boolean ok;
    private final int timestamp;
    
    protected FileRequestResponsePacket(boolean ok, int timestamp) {
        super(typeMessage.FILE_REQUEST_RESPONSE);
        this.ok = ok;
        this.timestamp = timestamp;
    }
    
    protected FileRequestResponsePacket(JSONObject json) {
        super(json);
        this.ok = json.getBoolean("ok");
        this.timestamp = json.getInt("timestamp");
    }
    
    protected boolean getOk() {
        return this.ok;
    }
    
    protected int getTimestamp() {
        return timestamp;
    }
    
    @Override
    protected JSONObject toJSON() {
        JSONObject obj = super.toJSON();
        obj.put("ok", ok);
        obj.put("timestamp", timestamp);
        return obj;
    }
}
