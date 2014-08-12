package io.gmind7.spring.foundation.exception;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage implements Serializable {
	
	private static final long serialVersionUID = -4658901645508955399L;
	
	private List<String> messages;
	
    public ErrorMessage() {
    }
 
    public ErrorMessage(List<String> messages) {
        this.messages = messages;
    }
 
    public ErrorMessage(String messages) {
        this(Collections.singletonList(messages));
    }
 
    public ErrorMessage(String ... messages) {
        this(Arrays.asList(messages));
    }
 
    public List<String> getMessages() {
        return messages;
    }
 
    public void setMessage(List<String> messages) {
        this.messages = messages;
    }
}
