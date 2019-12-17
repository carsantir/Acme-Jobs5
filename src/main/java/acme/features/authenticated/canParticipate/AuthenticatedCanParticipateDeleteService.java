
package acme.features.authenticated.canParticipate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThreads.CanParticipate;
import acme.entities.messageThreads.MessageThread;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedCanParticipateDeleteService implements AbstractDeleteService<Authenticated, CanParticipate> {

	@Autowired
	private AuthenticatedCanParticipateRepository repository;


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

		request.unbind(entity, model, "messageThread.id", "authenticated.userAccount.username");

	}

	@Override
	public CanParticipate findOne(final Request<CanParticipate> request) {
		assert request != null;

		CanParticipate result;
		String username;
		int messageThreadId;

		if (request.getServletRequest().getQueryString() == null) {
			messageThreadId = request.getModel().getInteger("messageThread.id");
			username = request.getModel().getString("authenticated.userAccount.username");
			result = this.repository.findOneCanParticipateByMessageThreadIdAndUsername(username, messageThreadId);
		}

		/*
		 * String url = request.getServletRequest().getQueryString();
		 * String[] aux = url.split("=");
		 * id = Integer.parseInt(aux[1]);
		 *
		 * username = request.getModel().getString("authenticated.userAccount.username");
		 * result = this.repository.findOneCanParticipateByMessageThreadIdAndUsername(username, id);
		 */
		return result;
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
		canParticipate = this.repository.findOneCanParticipateByMessageThreadIdAndUsername(username, mtId);
		isNew = canParticipate != null;

		errors.state(request, isNew, "authenticated.userAccount.username", "canParticipate.message.error.isNew");
	}

	@Override
	public void delete(final Request<CanParticipate> request, final CanParticipate entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

	}

}
