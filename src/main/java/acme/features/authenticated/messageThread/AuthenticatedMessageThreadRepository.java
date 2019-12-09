
package acme.features.authenticated.messageThread;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messageThreads.MessageThread;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageThreadRepository extends AbstractRepository {

	@Query("select m from MessageThread m where m.id=?1")
	MessageThread findOneMessageThreadById(int id);

	@Query("select mt from MessageThread mt where mt.id in (select m.messageThread.id from Message m where m.authenticated.userAccount.id = ?1)")
	Collection<MessageThread> findMessageThreadByUserId(int id);

}
