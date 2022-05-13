package fi.utu.tech.threadrunner2.assignment;

import fi.utu.tech.threadrunner2.mediator.ControlSet;
import fi.utu.tech.threadrunner2.mediator.Mediator;
import fi.utu.tech.threadrunner2.works.Work;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;


public class Task1UsingThreadDistributor implements Distributor, Runnable{
		private Mediator mediator;
		private BlockingQueue<Work> workList;
		private ControlSet control;
		public List<Thread> ab = new ArrayList<Thread>();

	public Task1UsingThreadDistributor(Mediator mediator, ControlSet control) {
		this.mediator = mediator;
		this.control = control;

		//mediator.registerThread(this.hashCode(), "Thread");

		for (int i = 0; i< control.getThreadCount(); i++) {
//			Task1UsingThreadDistributor t = new Task1UsingThreadDistributor (mediator,control);
			ab.add(new Thread(new Thread(this)));
		}
			//Thread  t = new Task1UsingThreadDistributor (mediTator,control);
				
			//mediator.setRunStatus("Created", t.hashCode());
			for ( Thread thread : ab) {
				mediator.registerThread(thread.hashCode(), "Thread");
			}
		//}

	}


	public void execute() {

//		for (int i = 0; i< control.getThreadCount(); i++) {
////			Task1UsingThreadDistributor t = new Task1UsingThreadDistributor (mediator,control);
//			//  t = new Task1UsingThreadDistributor (mediator,control);
//			//t.start();
//			//mediator.setRunStatus("Created", t.hashCode());
//		}
		for (Thread thread: ab) {
			thread.start();
			mediator.setRunStatus("Created", thread.hashCode());
		}

	}

	public void run() {
		try {
			mediator.setRunStatus("Running", Thread.currentThread().hashCode());
			while (!((workList = mediator.getWorkSlice(this.control.getBlockSize())).isEmpty())) {

				for (Work item : workList) {
					mediator.setWorkStatus("Calculating", item);
					item.work();
					mediator.setWorkStatus("Done", item);
					mediator.increaseCalculated(Thread.currentThread().hashCode());
				}

			}
			mediator.setRunStatus("Ended",Thread.currentThread().hashCode());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
