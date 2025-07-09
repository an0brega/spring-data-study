package br.com.backsolutions.springdata.study.repository;

import br.com.backsolutions.springdata.study.orm.WorkUnit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkUnitRepository extends CrudRepository<WorkUnit, Integer>{
}
