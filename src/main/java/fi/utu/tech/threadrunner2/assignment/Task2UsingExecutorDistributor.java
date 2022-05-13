package fi.utu.tech.threadrunner2.assignment;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fi.utu.tech.threadrunner2.mediator.ControlSet;
import fi.utu.tech.threadrunner2.mediator.Mediator;
import fi.utu.tech.threadrunner2.works.Work;

public class Task2UsingExecutorDistributor extends Thread implements Distributor {
	private Mediator mediator;
	private BlockingQueue<Work> workList;
	private ControlSet control;
	

	public Task2UsingExecutorDistributor(Mediator mediator, ControlSet control) {
		this.mediator = mediator;
		this.control = control;
		mediator.registerThread(this.hashCode(), "Executor");
		
	}

	public void execute() {
	
		ExecutorService executor =  Executors.newFixedThreadPool(control.getThreadCount());
		 for(int i = 0; i<control.getThreadCount(); i++) {	
				Runnable r = new Task2UsingExecutorDistributor(mediator,control);
				executor.execute(r);
				mediator.setRunStatus("Created", r.hashCode());
		 }
		 executor.shutdown();
		}
		
		
	

	public void run() {
				try {
					//Thread t = new Task1UsingThreadDistributor (mediator,control);
				mediator.setRunStatus("Running", this.hashCode());
				while (!((workList = mediator.getWorkSlice(this.control.getBlockSize())).isEmpty())) {
				
					for (Work item : workList) {
						mediator.setWorkStatus("Calculating", item);
						item.work();
					//	t.start();
						mediator.setWorkStatus("Done", item);
						mediator.increaseCalculated(this.hashCode());
						//Thread.sleep(1000);
					}
					
				}
				mediator.setRunStatus("Ended",this.hashCode());
			} catch (Exception e) {}
				
	}
	
}
