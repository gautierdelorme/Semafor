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
 *  UDP Packet representing a bye message
 * 
 * @author gautier
 */
class ByePacket extends UDPPacket {

    protected ByePacket() {
        super(typeMessage.BYE);
    }
    
}
