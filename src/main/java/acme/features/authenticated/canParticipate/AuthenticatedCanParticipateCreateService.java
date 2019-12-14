
package acme.features.authenticated.canParticipate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThreads.CanParticipate;
import acme.entities.messageThreads.MessageThread;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedCanParticipateCreateService implements AbstractCreateService<Authenticated, CanParticipate> {

	@Autowired
	AuthenticatedCanParticipateRepository repository;


	@Override
	public boolean authorise(final Request<CanParticipate> request) {

		return true;
	}

	@Override
	public void bind(final Request<CanParticipate> request, final CanParticipate entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<CanParticipate> request, final CanParticipate entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "authenticated.id", "messageThread.id");

	}

	@Override
	public CanParticipate instantiate(final Request<CanParticipate> request) {
		CanParticipate canParticipate;

		canParticipate = new CanParticipate();

		String query[] = request.getServletRequest().getQueryString().split("&");

		String authenticatedIdParam[] = query[0].split("=");
		int authenticatedId = Integer.parseInt(authenticatedIdParam[1]);

		String messageThreadIdParam[] = query[0].split("=");
		int messageThreadId = Integer.parseInt(messageThreadIdParam[1]);

		Authenticated authenticated = this.repository.findOneAuthenticatedById(authenticatedId);
		MessageThread messageThread = this.repository.findOneMessageThreadById(messageThreadId);

		canParticipate.setAuthenticated(authenticated);
		canParticipate.setMessageThread(messageThread);

		return canParticipate;
	}

	@Override
	public void validate(final Request<CanParticipate> request, final CanParticipate entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<CanParticipate> request, final CanParticipate entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
