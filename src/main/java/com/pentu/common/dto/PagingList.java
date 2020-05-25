package com.pentu.common.dto;

import java.util.List;

public class PagingList<T> {
    private long size;
    private List<T> data;

    public PagingList(){}

    public PagingList(long size, List<T> data){
        this.size = size;
        this.data = data;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
