
package fr.hoc.dap.server.data;

import org.springframework.data.repository.CrudRepository;

/**
 * Gérer les acces aux dap user en BDD.
 * @author house
 *
 */
public interface DapUserRepository extends CrudRepository<DapUser, Long> {

    DapUser findByUserKey(String userKey);

}
