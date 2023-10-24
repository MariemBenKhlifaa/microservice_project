package tn.esprit.springjasser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.springjasser.entities.ERole;
import tn.esprit.springjasser.entities.Role;
import tn.esprit.springjasser.entities.User;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(ERole name);

    @Query("select rl from User us join us.role rl where rl.name=:name")
    Set<Role> getBynaame(@Param("name") ERole name);
}
