2.11 Consider a simple server that carries out client requests without accessing other servers. Explain why it is generally not possible to set a limit on the time taken by such a server to respond to a client request. What would need to be done to make the server able to execute requests within a bounded time? Is this a practical option? page 64

a)In a distributed system it is hard to set limits on the time that can be taken for process execution, message delivery or clock drift. It is possible to suggest likely upper and lower bounds for process execution time, message delay and clock drift rates in a distributed system, but it is difficult to arrive at realistic values and to provide guarantees of the chosen values.

b)

```
To execute requests within bounded time, limit the number of clients to suit its capacity. To deal with more clients, use a server with more processors. After that, (or instead) replicate the service
```

```Chat gpt的回答
limits on the amount of resources (such as CPU, memory, etc.) that a single request is allowed to consume. This could be done by implementing resource usage quotas for requests, or by using a technique like containerization or virtualization to limit the amount of resources a request can consume.

However, it is worth noting that this may not be a practical option in all scenarios, especially when the server is dealing with a large number of requests with variable complexities and the complexity can't be determined upfront. In such cases, these options may not be practical as they may lead to limiting the throughput of the server, and could result in some requests not being processed at all.

It's also important to note that many servers are not built to operate within fixed time limits and such designs will have trade-offs on the performance and scalability of the server.
```

2.14 consider two communication services for use in asynchronous distributed systems. In service A, messages may be lost, duplicated or delayed and checksums apply only to headers. In service B, messages may be lost, delayed or delivered too fast for the recipient to handle them, but those that are delivered arrive with the correct contents.
Describe the classes of failure exhibited by each service. Classify their failures according to their effects on the properties of validity and integrity. Can service B be described as a reliable communication service? page 67, page 71

2.14 Ans.

Service A can have:
arbitrary failures and omission failure(Communication omission failures)

Service A can have arbitrary failures because checksums do not apply to message bodies, message bodies can corrupted and it got duplicated messages.Also it can got omission failure(Communication omission failures) because messages may be lost

Service B can have:

omission failures (lost messages, dropped messages).

Because the distributed system in which it is used is asynchronous, it cannot suffer from timing failures.

It passes the integrity test, but not the validity test, therefore it cannot be called reliable.

It will not have timing failure because it's asynchronous distributed systems.
