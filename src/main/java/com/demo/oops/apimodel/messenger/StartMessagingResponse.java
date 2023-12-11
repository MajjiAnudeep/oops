package com.demo.oops.apimodel.messenger;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
public class StartMessagingResponse {

    @NonNull
    private boolean countMatch;

    @NonNull
    private List<String> sentMessages;

    @NonNull
    private List<String> processedMessages;

}
