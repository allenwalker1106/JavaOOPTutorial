package com.example.CarParkApi.Model;

public class Criteria {
    private String field;
    private String data;
    private String orderBy;
    private String order;
    private int page;
    private int limit;

    public Criteria() {
    }

    public Criteria(String field, String data, String orderBy, String order, int page, int limit) {
        this.field = field;
        this.data = data;
        this.orderBy = orderBy;
        this.order = order;
        this.page = page;
        this.limit = limit;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "field='" + field + '\'' +
                ", data='" + data + '\'' +
                ", orderBy='" + orderBy + '\'' +
                ", order='" + order + '\'' +
                ", page=" + page +
                ", limit=" + limit +
                '}';
    }
}
