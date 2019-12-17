
package acme.features.authenticated.authenticated;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAuthenticatedRepository extends AbstractRepository {

	@Query("select a from Authenticated a where a.id in (select cp.authenticated.id from CanParticipate cp where cp.messageThread.id=?1)")
	Collection<Authenticated> findAuthenticatedInvolved(int id);

	@Query("select a from Authenticated a where a.id = (select mt.authenticated.id from MessageThread mt where mt.id = ?1)")
	Authenticated findAuthorMessageThread(int id);

	@Query("select a from Authenticated a where a.id not in (select cp.authenticated.id from CanParticipate cp where cp.messageThread.id=?1)")
	Collection<Authenticated> findAuthenticatedNotInvolved(int id);

	@Query("select a from Authenticated a where a.id=?1")
	Authenticated findOneAuthenticated(int id);

}
