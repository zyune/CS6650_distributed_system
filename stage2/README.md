#### what's the difference between rmi and rpc

RMI (Remote Method Invocation) and RPC (Remote Procedure Call) are both techniques for allowing a program to invoke methods or procedures that are executed on a remote machine. However, they have
some key differences:

- RMI is a Java-specific technology that allows Java objects to invoke methods on remote Java objects as if they were local objects. RMI uses the Java Virtual Machine's (JVM) built-in support for object serialization to transmit objects and method calls over the network. RMI is part of the Java standard library and can be used to build distributed systems in a Java-only environment.

- RPC, on the other hand, is a more general-purpose technology that can be used with a variety of programming languages. It allows a program to make a procedure call to a function that is executed on a remote machine, and it uses a protocol such as TCP/IP to transmit data over the network. RPC is not limited to Java and can be used in distributed systems across different languages and platforms.

In summary, RMI and RPC are both used for remote method invocation and procedure calls, but RMI is a Java-specific technology and RPC is a more general-purpose technology that can be used with a variety of programming languages.
