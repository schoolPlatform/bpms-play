package it.redhat.test;

import java.util.HashMap;

import org.jbpm.process.workitem.rest.RESTWorkItemHandler;

import it.redhat.demo.customtask.ChooseDeployStrategy;
import it.redhat.demo.customtask.CreateContainerSpec;
import it.redhat.demo.customtask.InputValidator;

public class UnifiedManagedDeployIT extends UnifiedManagedDeployTest {

	@Override
	public void before() {
		
		System.setProperty("org.kie.server.controller.user", "fabio");
		System.setProperty("org.kie.server.controller.pwd", "fabio$739");
		System.setProperty("org.kie.server.user", "fabio");
		System.setProperty("org.kie.server.pwd", "fabio$739");
		
		runtimeManager = createRuntimeManager("it/redhat/test/create-container.bpmn2", "it/redhat/test/update-container.bpmn2", "it/redhat/test/migration.bpmn2", "it/redhat/test/unified-managed-deploy.bpmn2");
		
		runtimeEngine = getRuntimeEngine();
		kieSession = runtimeEngine.getKieSession();
		
		kieSession.getWorkItemManager().registerWorkItemHandler("Rest", new RESTWorkItemHandler(System.getProperty("org.kie.server.controller.user"), System.getProperty("org.kie.server.controller.pwd"), this.getClass().getClassLoader()));
		kieSession.getWorkItemManager().registerWorkItemHandler("ProcessServerRest", new RESTWorkItemHandler(System.getProperty("org.kie.server.user"), System.getProperty("org.kie.server.pwd"), this.getClass().getClassLoader()));
		kieSession.getWorkItemManager().registerWorkItemHandler("ChooseDeployStrategy", new ChooseDeployStrategy());
		kieSession.getWorkItemManager().registerWorkItemHandler("CreateContainerSpec", new CreateContainerSpec());
		kieSession.getWorkItemManager().registerWorkItemHandler("InputValidator", new InputValidator());
		
	}
	
	@Override
	protected void testProcess(HashMap<String, Object> params) {
		kieSession.startProcess("it.redhat.test.unified-managed-deploy", params);
	}
	
}
