package com.fielamigo.app.FielAmigo.dto;

import java.util.List;

public class PaginatedDto<T extends List<?>> {
    private T data;
    private int count;
    private int offset;
    private int limit;
    private int total;

    public PaginatedDto(T data, int count, int offset, int limit, int total) {
        this.data = data;
        this.count = count;
        this.offset = offset;
        this.limit = limit;
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PaginatedDto [data=" + data + ", count=" + count + ", offset=" + offset + ", limit=" + limit
                + ", total=" + total + "]";
    }

}
