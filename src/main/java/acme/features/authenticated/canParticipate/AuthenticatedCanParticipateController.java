
package acme.features.authenticated.canParticipate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.messageThreads.CanParticipate;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/can-participate/")
public class AuthenticatedCanParticipateController extends AbstractController<Authenticated, CanParticipate> {

	@Autowired
	private AuthenticatedCanParticipateCreateService	createService;

	@Autowired
	private AuthenticatedCanParticipateDeleteService	deleteService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}
