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
class FileRequestPacket extends UDPPacket {
    private final String name;
    private final int timestamp;

    protected FileRequestPacket(String name) {
        super(typeMessage.FILE_REQUEST);
        this.name = name;
        this.timestamp = (int)System.currentTimeMillis();
    }
    
    protected FileRequestPacket(JSONObject json) {
        super(json);
        this.name = json.getString("name");
        this.timestamp = json.getInt("timestamp");
    }
    
    protected String getName(){
        return this.name;
    }

    protected int getTimestamp() {
        return timestamp;
    }
    
    @Override
    protected JSONObject toJSON() {
        JSONObject obj = super.toJSON();
        obj.put("name", name);
        obj.put("timestamp", timestamp);
        return obj;
    }
}
