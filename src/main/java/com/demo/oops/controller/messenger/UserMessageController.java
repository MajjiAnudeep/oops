package com.demo.oops.controller.messenger;

import com.demo.oops.apimodel.messenger.StartMessagingResponse;
import com.demo.oops.service.messenger.UserMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/message")
@RequiredArgsConstructor
@Slf4j
public class UserMessageController {

    private final UserMessageService userMessageService;

    @GetMapping()
    public ResponseEntity<StartMessagingResponse> sendMessages(@RequestParam(value = "users", required = false, defaultValue = "5") Integer users)
            throws Exception {
        StartMessagingResponse response = userMessageService.startMessaging(users);
        return ResponseEntity.ok(response);
    }
}
