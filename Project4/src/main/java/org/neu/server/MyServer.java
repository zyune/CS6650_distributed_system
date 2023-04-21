package org.neu.server;

import lombok.extern.slf4j.Slf4j;
import org.neu.api.PaxosAPI;
import org.neu.protocol.KeyValuePacket;
import org.neu.protocol.Message;

import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

/**
 * MyServer.
 */
@Slf4j
public class MyServer extends UnicastRemoteObject implements PaxosAPI {

    private final Proposer proposer;

    private final Acceptor acceptor;

    private final Learner learner;

    protected MyServer(Map<String, String> data, int port)
            throws RemoteException, UnknownHostException, NotBoundException {
        this.proposer = new Proposer(data, port);
        this.acceptor = new Acceptor();
        this.learner = new Learner();
        CG crashGenerator = new CG(this.proposer, this.acceptor);
        start(crashGenerator);
        log.info("MyServer started ...");
    }

    /**
     * Start a daemon thread.
     *
     * @param object the runnable object to be started
     */
    public void start(Runnable object) {
        Thread thread = new Thread(object);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public String get(KeyValuePacket message) throws RemoteException {
        log.info("Received GET request: key: " + message.getKey());
        String res = learner.commit(message);
        log.info("Sent GET response: " + res);
        return res;
    }

    @Override
    public String put(KeyValuePacket message) throws RemoteException {
        log.info("Received PUT request: key: " + message.getKey() + ", value: " + message.getValue());
        String res = proposer.propose(message);
        log.info("Sent PUT response: " + res);
        return res;
    }

    @Override
    public String delete(KeyValuePacket message) throws RemoteException {
        log.info("Received DELETE request: key: " + message.getKey() + ", value: " + message.getValue());
        String res = proposer.propose(message);
        log.info("Sent DELETE response: " + res);
        return res;
    }

    @Override
    public Message prepare(double proposeNum) throws RemoteException {
        return acceptor.prepare(proposeNum);
    }

    @Override
    public Message accept(Message message) throws RemoteException {
        return acceptor.accept(message);
    }

    @Override
    public void onClose() throws RemoteException {
        acceptor.resetProposalAccepted();
    }

    @Override
    public String commit(Message message) throws RemoteException {
        log.info("Commit proposal No. " + message.getProposalNum() + ", value: " + message.getValue());
        return learner.commit(message.getValue());
    }
}
