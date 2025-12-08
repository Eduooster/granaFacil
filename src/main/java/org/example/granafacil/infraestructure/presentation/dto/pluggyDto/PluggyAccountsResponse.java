package org.example.granafacil.infraestructure.presentation.dto.pluggyDto;


import org.example.granafacil.core.application.dtos.PluggyItemAccounts;

import java.util.List;

public class PluggyAccountsResponse {
    private List<PluggyItemAccounts> results;

    public List<PluggyItemAccounts> getResults() {
        return results;
    }

    public void setResults(List<PluggyItemAccounts> results) {
        this.results = results;
    }
}