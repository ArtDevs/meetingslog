package org.artdevs.meetingslog.core.model;

/**
 * Created by Slava on 13.01.2015.
 */
public class Message {
    protected int id;
    protected boolean readonly;
    protected String msg_text;
    protected int ownerId;

    public Message(int id, boolean readonly, String msg_text) {
        this.id=id;
        this.readonly=readonly;
        this.msg_text=msg_text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    public String getMsg_text() {
        return msg_text;
    }

    public void setMsg_text(String msg_text) {
        this.msg_text = msg_text;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return msg_text;
    }
}
