package com.example.myhomework.bean;

import androidx.annotation.NonNull;

public class MapRecord {
    public enum RecordType{
        Bottle("Bottle"), Board("Board");
        private String name;

        RecordType(String name){
            this.name = name;
        }
        @NonNull
        @Override
        public String toString() {
            return this.name;
        }
    }
    private RecordType recordType;
    private String address;
    private double x;
    private double y;

    public MapRecord(RecordType recordType, String address, double x, double y) {
        this.recordType = recordType;
        this.address = address;
        this.x = x;
        this.y = y;
    }


    public MapRecord(String recordType, String address, double x, double y) {
        if(recordType.equals(RecordType.Bottle.name))
            this.recordType = RecordType.Bottle;
        else
            this.recordType = RecordType.Board;
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

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
