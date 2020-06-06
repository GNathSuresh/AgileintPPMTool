package io.agileintelligent.ppmtool.repository;

import io.agileintelligent.ppmtool.domain.ProjectItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectItemRepository extends CrudRepository<ProjectItem, Long> {
}
