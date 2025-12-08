package org.example.granafacil.core.application.dtos;

import java.util.List;

public class TransactionsResponse {

    private Integer total;
    private Integer totalPages;
    private Integer page;
    private List<TransactionDTO> results;

    @Override
    public String toString() {
        return "TransactionsResponse{" +
                "total=" + total +
                ", totalPages=" + totalPages +
                ", page=" + page +
                ", results=" + results +
                '}';
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<TransactionDTO> getResults() {
        return results;
    }

    public void setResults(List<TransactionDTO> results) {
        this.results = results;
    }


    // getters e setters
}
