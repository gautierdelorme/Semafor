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
public class FileRequestPacket extends UDPPacket {
    private final String name;

    public FileRequestPacket(String name) {
        super(typeMessage.FILE_REQUEST);
        this.name = name;
    }
    
    public FileRequestPacket(JSONObject json) {
        super(json);
        this.name = json.getString("name");
    }
    
    @Override
    public JSONObject toJSON() {
        JSONObject obj = super.toJSON();
        obj.put("name", name);
        return obj;
    }
}
