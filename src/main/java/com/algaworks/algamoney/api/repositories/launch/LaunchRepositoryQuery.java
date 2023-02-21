package com.algaworks.algamoney.api.repositories.launch;

import com.algaworks.algamoney.api.models.Launch;
import com.algaworks.algamoney.api.repositories.filters.LaunchFilter;
import com.algaworks.algamoney.api.repositories.projection.LaunchSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LaunchRepositoryQuery {

    public Page<Launch> filter(LaunchFilter launchFilter, Pageable pageable);
    public Page<LaunchSummary> summary(LaunchFilter launchFilter, Pageable pageable);

}
