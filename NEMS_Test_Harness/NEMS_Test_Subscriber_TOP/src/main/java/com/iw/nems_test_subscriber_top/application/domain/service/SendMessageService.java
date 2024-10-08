package com.iw.nems_test_subscriber_top.application.domain.service;

import java.util.List;

import com.iw.nems_test_subscriber_top.application.domain.model.OutboxMessage;
import com.iw.nems_test_subscriber_top.application.domain.model.TimeStampedMessage;
import com.iw.nems_test_subscriber_top.application.port.in.SendMessageUseCase;
import com.iw.nems_test_subscriber_top.application.port.out.DatabasePort;
import com.iw.nems_test_subscriber_top.application.port.out.GetMessagePort;
import com.iw.nems_test_subscriber_top.infrastructure.UseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class SendMessageService implements SendMessageUseCase{

    private final GetMessagePort getEventPort;
    private final DatabasePort dbPort;

    @Override
    public void processNewOutboxMessages(List<OutboxMessage> obMessages) {
        for (OutboxMessage outboxMessage : obMessages) {
            processMessage(outboxMessage);
        }
    }

    @Override
    public boolean sendEventPayload(TimeStampedMessage timeStampedMessage) {
        return ( getEventPort.getEventPayload(timeStampedMessage) != null);
    }

    @Override
    public void processMessage(OutboxMessage obMessage) {
        boolean sentSuccessfully= sendEventPayload(obMessage.getMessage());
        if(sentSuccessfully){
            dbPort.deleteMessage(obMessage.getSequenceNum());
        } else {
            dbPort.failMessage(obMessage.getSequenceNum());
        }
        return;
    }

}
