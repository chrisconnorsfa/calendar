/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.controllers.rest;

/**
 *
 * @author ChriConn
 */
public class ForbiddenException extends Exception {

    /**
     * Creates a new instance of <code>Forbidden</code> without detail message.
     */
    public ForbiddenException() {
    }

    /**
     * Constructs an instance of <code>Forbidden</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ForbiddenException(String msg) {
        super(msg);
    }
}
