
package acme.features.authenticated.canParticipate;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messageThreads.CanParticipate;
import acme.entities.messageThreads.MessageThread;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedCanParticipateRepository extends AbstractRepository {

	@Query("select a from Authenticated a where a.id=?1")
	Authenticated findOneAuthenticatedById(int id);

	@Query("select a from Authenticated a where a.userAccount.username=?1")
	Authenticated findOneAuthenticatedByUsername(String username);

	@Query("select mt from MessageThread mt where mt.id=?1")
	MessageThread findOneMessageThreadById(int id);

	@Query("select c from CanParticipate c where (c.authenticated.userAccount.username=?1 and c.messageThread.id=?2)")
	CanParticipate findOneCanParticipatebyMessageThreadIdAndUsername(String username, int id);

	@Query("select a from Authenticated a where a.id not in (select cp.authenticated.id from CanParticipate cp where cp.messageThread.id = ?1)")
	Collection<Authenticated> findAuthenticatedNotInvolved(int messageThreadId);
}
