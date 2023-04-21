package org.neu.server;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Manually generate crash to disable the function of acceptor or proposer.
 * To simulate network traffic
 */
@Slf4j
public class CG implements Runnable {

    private final Proposer proposer;
    private final Acceptor acceptor;

    public CG(Proposer proposer, Acceptor acceptor) {
        this.proposer = proposer;
        this.acceptor = acceptor;
    }

    @Override
    public void run() {
        log.info("Crash generator started ...");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            log.info("Please input command: 0 to suspend the acceptor, 1 to recover the acceptor, ");
            log.info("2 to suspend the proposer at phase 1, 3 to recover the proposer");
            log.info(
                    "Once enable the suspend function, the acceptor will fail at either phase 1 or phase 2, the proposer will only fail at phase 1");
            try {
                String command = reader.readLine();
                int i = Integer.parseInt(command);
                switch (i) {
                    case 0:
                        log.info("Acceptor is down");
                        acceptor.setDown(true);
                        break;
                    case 1:
                        acceptor.setDown(false);
                        log.info("Acceptor recovered");
                        break;
                    case 2:
                        log.info("Proposer will be down at phase 1");
                        proposer.setDown(true);
                        break;
                    case 3:
                        proposer.setDown(false);
                        log.info("Proposer recovered");
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown command");
                }
            } catch (IOException e) {
                log.info(e.getMessage());
                try {
                    reader.close();
                } catch (IOException ignored) {
                }
            } catch (IllegalArgumentException iae) {
                log.error(iae.getMessage());
            }
        }
    }
}
