
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

		request.unbind(entity, model, "authenticated.userAccount.username", "messageThread.id");

	}

	@Override
	public CanParticipate instantiate(final Request<CanParticipate> request) {
		CanParticipate canParticipate;
		int messageThreadId;

		canParticipate = new CanParticipate();

		if (request.getServletRequest().getQueryString() != null) {
			String messageThreadIdParam[] = request.getServletRequest().getQueryString().split("=");
			messageThreadId = Integer.parseInt(messageThreadIdParam[1]);

		} else {
			messageThreadId = request.getModel().getInteger("messageThread.id");

			String username = (String) request.getModel().getAttribute("authenticated.userAccount.username");
			Authenticated authenticated = this.repository.findOneAuthenticatedByUsername(username);
			canParticipate.setAuthenticated(authenticated);
		}

		MessageThread messageThread = this.repository.findOneMessageThreadById(messageThreadId);
		canParticipate.setMessageThread(messageThread);

		return canParticipate;
	}

	@Override
	public void validate(final Request<CanParticipate> request, final CanParticipate entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		CanParticipate canParticipate;
		boolean isNew;

		String username = request.getModel().getString("authenticated.userAccount.username");
		int mtId = request.getModel().getInteger("messageThread.id");
		canParticipate = this.repository.findOneCanParticipatebyMessageThreadIdAndUsername(username, mtId);
		isNew = canParticipate == null;

		errors.state(request, isNew, "authenticated.userAccount.username", "canParticipate.message.error.isNew");

	}

	@Override
	public void create(final Request<CanParticipate> request, final CanParticipate entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
