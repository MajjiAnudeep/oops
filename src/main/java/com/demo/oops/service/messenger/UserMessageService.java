package com.demo.oops.service.messenger;

import com.demo.oops.apimodel.messenger.StartMessagingResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserMessageService {
    StartMessagingResponse startMessaging(int users);
}
