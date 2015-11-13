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

    public FileRequestResponsePacket(boolean ok) {
        super(typeMessage.FILE_REQUEST_RESPONSE);
        this.ok = ok;
    }
    
    @Override
    public JSONObject toJSON() {
        JSONObject obj = super.toJSON();
        obj.put("ok", ok);
        return obj;
    }
}
