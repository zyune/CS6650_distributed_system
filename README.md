# CS6650_distributed_system

Distributed Systems Concepts and Design
全书架构
![output](img/bookstructure.png)

No global clock:
Independent failures:
Concurrency

1.5 Challenges

- Heterogeneity
  different computer system, different language
- Openness
- Security
- Scalability
- Failure handling
- Concurrency
- Transparency
- Quality of service

#### Architectural elements

- Communicating entitie
- Communication paradigms
- Roles and responsibilities
- - peer-to-peer
- - Client-server:
- Placement
- - Mapping of services to multiple servers
- - Caching

### Communication paradigms in distributed system

- Interprocess communication

```
Interprocess communication (IPC) refers to the mechanisms that a distributed system uses to allow different processes to communicate and share data with one another. In a distributed system, processes may run on different machines, and IPC mechanisms provide a way for these processes to share information and coordinate their actions.

There are several different types of IPC mechanisms that can be used in a distributed system, including:

Shared Memory: This is a way for processes to access and manipulate shared memory segments, allowing them to directly read and write data from the same location in memory.

Message Passing: This is a way for processes to communicate by sending messages to one another. The sender sends a message to a specific process or group of processes, and the receiver receives the message and processes it.

Pipes: A pipe is a special type of IPC mechanism that allows processes to communicate by writing to and reading from a shared buffer.

Sockets: A socket is a endpoint of communication channel between two programs running on a network. It can be used to send and receive data between two processes running on the same machine or on different machines.

Remote Procedure Calls (RPCs): This is a way for a process to invoke a function or method on a remote machine, and wait for the result to be returned.

Each of these IPC mechanisms have its own advantages and trade offs . The specific IPC method you choose will depend on the needs of your distributed system, such as the programming languages and platforms being used, the level of security required, and the type of workload being handled.
```

- Remote invocation

```
In a distributed system, remote invocation refers to the process of calling a function or method that is located on a remote machine, rather than on the same machine where the call is being made. This allows different parts of a distributed system to communicate with each other and perform operations on shared resources.

There are several different ways to perform remote invocation in a distributed system, including:

Remote Procedure Calls (RPCs): This is a protocol for making a request to a remote system, which then executes the requested function and returns the result to the caller.

Remote Method Invocation (RMI): This is a Java-specific approach for making remote method calls. It uses the Java Remote Method Protocol (JRMP) to transfer information between the caller and the remote method.

Message-Passing: instead of making a direct function call, a message is sent to the remote system and then a function call is made locally after message is recieved.

Web Services: This is a widely used standard for remote invocation that allows different systems to communicate over the internet, using a variety of protocols, including HTTP and SOAP.

The specific method you choose will depend on the needs of your distributed system, such as the programming languages and platforms being used, the level of security required, and the type of workload being handled.
```

- - Request-reply protocols
- - Remote procedure calls
- - Remote method invocation:

- Indirect communication
- - Group communication
- - Publish-subscribe systems
- - Message queues
- - Tuple spaces
- - Distributed shared memory

两个 example
Client-server:
peer-to-peer

#### Architectural patterns

Layering
Tiered architecture
Thin clients

test
