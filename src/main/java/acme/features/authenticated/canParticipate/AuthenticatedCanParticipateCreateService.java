
package acme.features.authenticated.canParticipate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThreads.CanParticipate;
import acme.entities.messageThreads.MessageThread;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
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

		String[] aux = request.getServletRequest().getQueryString().trim().split("mtId=");
		int id = Integer.parseInt(aux[1]);
		request.getModel().setAttribute("users", this.repository.findAuthenticatedNotInvolved(id));
		request.transfer(model, "users");
		request.unbind(entity, model);
		request.getModel().setAttribute("mtId", id);
		request.transfer(model, "mtId");
	}

	@Override
	public CanParticipate instantiate(final Request<CanParticipate> request) {

		CanParticipate cp = new CanParticipate();

		if (!request.isMethod(HttpMethod.GET)) {
			Authenticated au = this.repository.findOneAuthenticatedById(request.getModel().getInteger("users"));
			cp.setAuthenticated(au);
		}
		MessageThread mt = this.repository.findOneMessageThreadById(request.getModel().getInteger("mtId"));

		cp.setMessageThread(mt);

		return cp;
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
