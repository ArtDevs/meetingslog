package org.artdevs.meetingslog.core.model;

/**
 * Created by Slava on 13.01.2015.
 */
public class Message {
    protected int id;
    protected boolean readonly;
    protected String msg_text;
    protected String msg_title;
    protected int ownerId;

    public Message(int ownerId){
        this.ownerId=ownerId;
    }

    public Message(int id, boolean readonly, String msg_title, String msg_text, int ownerId) {
        this.id=id;
        this.readonly=readonly;
        this.msg_title=msg_title;
        this.msg_text=msg_text;
        this.ownerId=ownerId;
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

    public String getMsg_title() {
        return msg_title;
    }

    public void setMsg_title(String msg_title) {
        this.msg_title = msg_title;
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
        return msg_title;
    }
}
