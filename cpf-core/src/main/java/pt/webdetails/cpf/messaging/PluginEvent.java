/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/. */

package pt.webdetails.cpf.messaging;

import org.json.JSONException;
import org.json.JSONObject;
import pt.webdetails.cpf.persistence.Persistable;

public class PluginEvent implements Persistable {

    public static class Fields {

        public static final String TIMESTAMP = "timestamp";
        public static final String EVENT_TYPE = "eventType";
        public static final String PLUGIN = "plugin";
        public static final String NAME = "name";
        public static final String KEY = "key";
//    public static final String EVENT = "event";
    }
    private long timeStamp;
    private String eventType;
    private String plugin;
    private String name;
//  private JSONObject event;
    private String key;

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    public PluginEvent(JSONObject json) throws JSONException {
        this.fromJSON(json);
    }

    public PluginEvent(String plugin, String eventType, String name) throws JSONException {
        this.timeStamp = System.currentTimeMillis();//TODO: global getTime!
        this.plugin = plugin;
        this.eventType = eventType;
        this.name = name;
//    this.event = event == null ? null : event.toJSON();
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getPlugin() {
        return plugin;
    }

    public void setPlugin(String plugin) {
        this.plugin = plugin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//  protected void setEvent(JSONObject eventInfo){
//    event = eventInfo;
//  }
//  
//  public JSONObject getEvent(){
//    return event;
//  }
    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject obj = new JSONObject();
//    obj.put(Fields.EVENT, getEvent());
        obj.put(Fields.EVENT_TYPE, getEventType());
        obj.put(Fields.PLUGIN, getPlugin());
        obj.put(Fields.NAME, getName());
        obj.put(Fields.TIMESTAMP, getTimeStamp());
        return obj;
    }

    @Override
    public String toString() {
        try {
            return toJSON().toString(2);
        } catch (JSONException e) {
            return "bad json: " + e.getMessage();
        }
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void fromJSON(JSONObject json) throws JSONException {
        setTimeStamp(json.getLong(Fields.TIMESTAMP));
        setPlugin(json.getString(Fields.PLUGIN));
        setName(json.getString(Fields.NAME));
        setEventType(json.getString(Fields.EVENT_TYPE));
        try {
            setKey(json.getString(Fields.KEY));
        } catch (JSONException jse) {
            setKey(null);
        }
    }
}
