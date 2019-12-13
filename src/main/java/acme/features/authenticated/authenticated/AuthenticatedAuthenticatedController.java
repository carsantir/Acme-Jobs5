
package acme.features.authenticated.authenticated;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/authenticated/")
public class AuthenticatedAuthenticatedController extends AbstractController<Authenticated, Authenticated> {

	@Autowired
	private AuthenticatedAuthenticatedListInvolvedService		listService;

	@Autowired
	private AuthenticatedAuthenticatedListNotInvolvedService	listNotInvolved;

	@Autowired
	private AuthenticatedAuthenticatedShowService				showService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_INVOLVED, BasicCommand.LIST, this.listService);
		super.addCustomCommand(CustomCommand.LIST_NOT_INVOLVED, BasicCommand.LIST, this.listNotInvolved);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
