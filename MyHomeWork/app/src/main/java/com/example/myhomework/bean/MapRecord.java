package com.example.myhomework.bean;

public class MapRecord {
    public enum RecordType{
        Bottle, Board
    }
    private RecordType recordType;
    private String address;
    private float x;
    private float y;

    public MapRecord(RecordType recordType, String address, float x, float y) {
        this.recordType = recordType;
        this.address = address;
        this.x = x;
        this.y = y;
    }

    public RecordType getRecordType() {
        return recordType;
    }

    public void setRecordType(RecordType recordType) {
        this.recordType = recordType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
