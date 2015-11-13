/*
 * Semafor - MIT License
 *
 * A peer-to-peer chat system
 *
 * Copyright 2015 Gautier Delorme and Arthur Papailhau.
 *
 */
package netview;
import java.io.File;
import java.net.InetAddress;

/**
 *
 * @author gautier
 */
public interface FromToRmtApp {
    public void hello(InetAddress ip, String nickname, boolean reqReply);
    public void bye(InetAddress ip);
    public void message(InetAddress ip, String message);
    public void file(InetAddress ip, File file);
}
