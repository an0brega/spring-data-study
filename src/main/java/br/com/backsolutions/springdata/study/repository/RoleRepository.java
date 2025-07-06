package br.com.backsolutions.springdata.study.repository;

import br.com.backsolutions.springdata.study.orm.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{

}
