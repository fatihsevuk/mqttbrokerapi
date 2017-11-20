package com.iotrack.mqttbroker;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class PublishData implements Serializable{

    private static final long serialVersionUID = 1L;

    private int zoneId;
    private String tagName;
    private int xCoord;
    private int yCoord;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private Date time;
    private int timeDiff;
    private String sensorInfo;

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getTimeDiff() {
        return timeDiff;
    }

    public void setTimeDiff(int timeDiff) {
        this.timeDiff = timeDiff;
    }

    public String getSensorInfo() {
        return sensorInfo;
    }

    public void setSensorInfo(String sensorInfo) {
        this.sensorInfo = sensorInfo;
    }

    @Override
    public String toString() {
        return "PublishData{" +
                "zoneId=" + zoneId +
                ", tagName='" + tagName + '\'' +
                ", xCoord=" + xCoord +
                ", yCoord=" + yCoord +
                ", time=" + time +
                ", timeDiff=" + timeDiff +
                ", sensorInfo='" + sensorInfo + '\'' +
                '}';
    }
}
