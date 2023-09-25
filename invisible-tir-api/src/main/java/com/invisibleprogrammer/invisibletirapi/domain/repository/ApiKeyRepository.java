package com.invisibleprogrammer.invisibletirapi.domain.repository;

import com.invisibleprogrammer.invisibletirapi.domain.ApiKey;
import org.springframework.data.repository.CrudRepository;

public interface ApiKeyRepository extends CrudRepository<ApiKey, Long> {
}
