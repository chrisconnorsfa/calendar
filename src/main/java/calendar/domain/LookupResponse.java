/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.domain;

/**
 *
 * @author ChriConn
 */
public class LookupResponse {
    
    private String text;
    private Long value;

    public LookupResponse() {
        
    }
    
    public LookupResponse(String text, Long value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    
}
