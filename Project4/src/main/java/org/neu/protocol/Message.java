package org.neu.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * The consensus message that contains the proposal number, message type, and
 * key value info.
 */
@Data
@AllArgsConstructor
@ToString
public class Message implements Serializable {

    private static final long serialVersionUID = 1234567L;

    private double proposalNum;

    private Type messageType;

    private KVP value;

}
