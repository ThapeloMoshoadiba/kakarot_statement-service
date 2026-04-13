package com.capsule.corp.domain.persistance;

import com.capsule.corp.infrastructure.http.resources.Statement;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatementRepository extends JpaRepository<Statement, UUID> {

  Optional<Statement> findByExtension(String extension);
}
