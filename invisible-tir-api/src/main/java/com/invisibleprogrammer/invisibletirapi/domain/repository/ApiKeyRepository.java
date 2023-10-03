package com.invisibleprogrammer.invisibletirapi.domain.repository;

import com.invisibleprogrammer.invisibletirapi.domain.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {
}
