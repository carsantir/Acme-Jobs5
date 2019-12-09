
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messageThreads.Message;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select m from Message m where m.id=?1")
	Message findOneMessageThreadById(int id);

	@Query("select m from Message m where m.messageThread.id = ?1")
	Collection<Message> findMessagesByThreadId(int id);

	@Query("select a from Authenticated a where a.id=?1")
	Authenticated findAuthenticated(int id);

}
