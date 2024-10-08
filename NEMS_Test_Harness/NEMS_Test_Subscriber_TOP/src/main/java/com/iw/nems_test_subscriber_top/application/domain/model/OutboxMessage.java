package com.iw.nems_test_subscriber_top.application.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "outbox_table")
@NoArgsConstructor
@AllArgsConstructor
public class OutboxMessage {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sequenceNum;

    private String subscriberId;
    private String messageId;
    
    private String nhi;
    private TimeStampedMessage message;
    private String status = "new";

    public OutboxMessage(TimeStampedMessage tsMessage){
        try{
            this.nhi = tsMessage.getContent().get("nhi").asText();
        } catch (NullPointerException e) {
            this.nhi = "";
        }
        this.message = tsMessage;
    }

    public OutboxMessage(TimeStampedMessage tsMessage, String messageId){
        try{
            this.nhi = tsMessage.getContent().get("nhi").asText();
        } catch (NullPointerException e) {
            this.nhi = "";
        }
        this.message = tsMessage;
        this.messageId = messageId;
    }

}