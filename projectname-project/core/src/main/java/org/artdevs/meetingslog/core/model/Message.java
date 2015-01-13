package org.artdevs.meetingslog.core.model;

/**
 * Created by Slava on 13.01.2015.
 */
public class Message {
    Integer id;
    Boolean readonly;
    String msg_text;

    public Message(int id, boolean readonly, String msg_text) {
        this.id=id;
        this.readonly=readonly;
        this.msg_text=msg_text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getReadonly() {
        return readonly;
    }

    public void setReadonly(Boolean readonly) {
        this.readonly = readonly;
    }

    public String getMsg_text() {
        return msg_text;
    }

    public void setMsg_text(String msg_text) {
        this.msg_text = msg_text;
    }

    @Override
    public String toString() {
        return msg_text;
    }
}
