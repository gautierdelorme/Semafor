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

/**
 *
 * @author gautier
 */
public interface FromToRmtApp {
    public void hello(String ip, String nickname, boolean reqReply);
    public void bye(String ip);
    public void message(String ip, String message);
    public void file(String ip, File file);
}
