
package acme.features.authenticated.authenticated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedAuthenticatedShowService implements AbstractShowService<Authenticated, Authenticated> {

	@Autowired
	AuthenticatedAuthenticatedRepository repository;


	@Override
	public boolean authorise(final Request<Authenticated> request) {
		assert request != null;
		return true;
	}
	@Override
	public void unbind(final Request<Authenticated> request, final Authenticated entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "userAccount.username");

	}
	@Override
	public Authenticated findOne(final Request<Authenticated> request) {
		assert request != null;

		Authenticated result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneAuthenticated(id);
		return result;
	}
}
