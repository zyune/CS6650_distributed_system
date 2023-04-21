package org.neu.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * The key value packet that stores the type of the request and key value pair.
 */
@Data
@AllArgsConstructor
@ToString
public class KVP implements Serializable {

    private static final long serialVersionUID = 1234567L;

    // put or delete
    private Type type;

    private String key;

    private String value;

}
