
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
		assert request != null;

		boolean result;
		int id;
		MessageThread messageThread;

		String url = request.getServletRequest().getQueryString();

		if (url != null) {
			String[] aux = url.split("=");
			id = Integer.parseInt(aux[1]);
			messageThread = this.repository.findOneMessageThreadById(id);
		} else {
			messageThread = this.repository.findOneMessageThreadById(request.getModel().getInteger("messageThread.id"));
		}

		result = request.getPrincipal().getActiveRoleId() == messageThread.getAuthenticated().getId();

		return result;
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
		boolean exists;

		String username = request.getModel().getString("authenticated.userAccount.username");
		int mtId = request.getModel().getInteger("messageThread.id");
		canParticipate = this.repository.findOneCanParticipateByMessageThreadIdAndUsername(username, mtId);
		exists = canParticipate != null;

		errors.state(request, exists, "authenticated.userAccount.username", "canParticipate.message.error.exists");

	}

	@Override
	public void create(final Request<CanParticipate> request, final CanParticipate entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
