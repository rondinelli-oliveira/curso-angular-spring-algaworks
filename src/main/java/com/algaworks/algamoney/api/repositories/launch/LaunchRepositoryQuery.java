package com.algaworks.algamoney.api.repositories.launch;

import com.algaworks.algamoney.api.models.Launch;
import com.algaworks.algamoney.api.repositories.filters.LaunchFilter;

import java.util.List;

public interface LaunchRepositoryQuery {

    public List<Launch> filter(LaunchFilter launchFilter);

}
