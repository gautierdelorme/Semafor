/*
 * Semafor - MIT License
 *
 * A peer-to-peer chat system
 *
 * Copyright 2015 Gautier Delorme and Arthur Papailhau.
 *
 */
package ni;

/**
 *
 * @author gautier
 */
public class ByePacket extends UDPPacket {

    public ByePacket() {
        super(typeMessage.BYE);
    }
    
}
