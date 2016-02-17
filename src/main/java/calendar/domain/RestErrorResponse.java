/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.domain;

import java.util.UUID;

/**
 *
 * @author ChriConn
 */
public class RestErrorResponse {
    
        private String uuid;
        private String message;

    public RestErrorResponse() {
        this.uuid = UUID.randomUUID().toString(); 
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
        
}
