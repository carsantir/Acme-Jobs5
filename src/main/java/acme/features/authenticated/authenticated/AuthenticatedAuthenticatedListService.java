
package acme.features.authenticated.authenticated;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedAuthenticatedListService implements AbstractListService<Authenticated, Authenticated> {

	@Autowired
	AuthenticatedAuthenticatedRepository repository;


	@Override
	public boolean authorise(final Request<Authenticated> request) {
		assert request != null;

		int id;

		String[] aux = request.getServletRequest().getQueryString().trim().split("mtId=");
		id = Integer.parseInt(aux[1]);

		Authenticated creator = this.repository.findAuthorMessageThread(id);

		return creator.getId() == request.getPrincipal().getActiveRoleId();
	}
	@Override
	public void unbind(final Request<Authenticated> request, final Authenticated entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "userAccount.username");
	}
	@Override
	public Collection<Authenticated> findMany(final Request<Authenticated> request) {
		assert request != null;
		Collection<Authenticated> result;
		int id;

		String[] aux = request.getServletRequest().getQueryString().trim().split("mtId=");
		id = Integer.parseInt(aux[1]);

		result = this.repository.findAuthenticatedInvolved(id);

		return result;
	}
}
