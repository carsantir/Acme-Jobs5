
package acme.features.authenticated.authenticated;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAuthenticatedRepository extends AbstractRepository {

	@Query("select a from Authenticated a where a.id in (select m.authenticated.id from Message m where m.messageThread.id = ?1)")
	Collection<Authenticated> findMessageThreadByUserId(int id);

}
