
package com.pentu.common.dto;

import java.util.List;

public class PagingList<T> {

    private long total;
    private List<T> data;

    public PagingList() {
    }

    public PagingList(long totalNumbers, List<T> data) {
        this.total = total;
        this.data = data;
    }

    public long getTotalNumbers() {
        return total;
    }

    public void setTotalNumbers(long totalNumbers) {
        this.total = totalNumbers;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
