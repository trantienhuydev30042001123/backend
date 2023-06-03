package com.huy.webdoan.dto;

import com.huy.webdoan.model.Product;

import java.util.ArrayList;
import java.util.List;

public class Output {
    private int page;
    private int totalPage;
    private List<productDTO> listResult = new ArrayList<>();

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<productDTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<productDTO> listResult) {
        this.listResult = listResult;
    }
}
