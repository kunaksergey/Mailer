package ua.shield.service.repository;

import org.springframework.data.repository.CrudRepository;
import ua.shield.entity.Role;
import ua.shield.entity.User;

import java.util.Set;

/**
 * Created by sa on 03.09.17.
 */
public interface RoleRepository extends CrudRepository<Role,Integer> {

}
