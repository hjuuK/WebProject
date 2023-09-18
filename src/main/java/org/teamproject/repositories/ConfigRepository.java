package org.teamproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.teamproject.entities.Configs;

public interface ConfigRepository extends JpaRepository<Configs, String> {
}
