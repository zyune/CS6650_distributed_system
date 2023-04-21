What is Consensus algorithms?

- Consensus algorithms are used to ensure that all nodes in the system agree on a common value or decision, even in the presence of faults, failures, or malicious behavior.

Why we need Consensus algorithms in distributed system?

- different nodes may have different views of the system state, and may receive and process different sets of messages. This can lead to inconsistencies and divergent behavior, which can compromise the reliability, consistency, and correctness of the system.

How we reach consensus in distributed system?

- Examples of consensus algorithms include Paxos, Raft, and Byzantine fault tolerance. These algorithms use various techniques, such as leader election, voting, and replication, to achieve consensus among the nodes in the system.

What is Concurrency Control?

- Locking : In this method, shared resources are protected by locks that allow only one node to access the resource at a time. Nodes request a lock on a resource before accessing it, and release the lock when they are done. This method is simple but can lead to contention and performance issues.

- - Exclusion locks

what is Process Synchronization?

- Process synchronization is the coordination of concurrent processes in a multi-processing or multi-threaded environment to ensure that they operate correctly and efficiently. In a concurrent system, multiple processes or threads can access the same shared resources such as memory, files, or input/output devices simultaneously. This can lead to race conditions, where the final output of the program depends on the relative timing of the processes' execution and the interleaving of their operations.

Why we need Process Synchronization?

- Process synchronization is important to ensure that concurrent processes access shared resources in a mutually exclusive and coordinated manner to avoid conflicts and race conditions.
