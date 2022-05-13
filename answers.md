# Question 1: Program structure
The point of the structure is that it creates a well generated Thread Pool. It uses LinkedBlockingQueue for generating the working list and it is being execute either by Single Thread, Multi-Threads or Executor Service. They are known as "workers". The MediatorService class is playing the main role in the structure. The main concept of the structure is implemented there, like the Thread-safe data structure "Blocking queue", registration of Threads, setting the suitable status of the Thread, adding Work and basically everything we see in the UI. In the ControlSet class we only have 2 methods which are to get the inputs of the Block Size and the Number of Threads we want to execute. 

# Question 2: More threads than cores 
More threads than processors will slow you down. Having a thread on each available processor will speed you up (comapred to having one thread, and rest of processors idle). 

# Question 3: workQueue

The workQueue object is an instance of BlockingQueue data structure. BlockingQueue is a thread-safe data structure that is
used mostly in Thread pools.A blocking queue is a queue that blocks when you try to dequeue from it and the queue is empty, or if you try to enqueue items to it and the queue is already full, which is very useful for multithreading.  In our case, it is of type LinkedBlockingQueue. All the methods are atomic in nature and use internal locks or other forms of concurrency control. 
While on the other hand, ArrayList is not a Thread-safe data structure. That means the multiple threads can access the same ArrayList object or instance simultaneously. Therefore, it cannot be used in the multi-threading environment without explicit synchronization. 
