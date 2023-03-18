package com.powersub.core.service;

import com.powersub.core.entity.Account;
import com.powersub.core.entity.Channel;
import com.powersub.core.repository.AccountRepository;
import com.powersub.core.repository.ChannelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
class SubscribeServiceTest {

    SubscribeService subscribeService;
    private static final Set<Account> SUBSCRIBERS = new HashSet<>();
    private static final Set<Channel> CHANNELS = new HashSet<>();

    @MockBean
    ChannelRepository channelRepository;
    @MockBean
    AccountRepository accountRepository;

    private static Account createAccount() {
        return new Account(1,
                "test@email.com",
                "12345678",
                ZonedDateTime.now(),
                CHANNELS);
    }

    private static Channel createChannel() {
        return new Channel(1,
                "test",
                "description test",
                ZonedDateTime.now(),
                createAccount(),
                SUBSCRIBERS
        );
    }

    @BeforeEach
    void before() {
        subscribeService = new SubscribeService(accountRepository, channelRepository);
    }

    @Test
    void whenSubscribeToChannel_thenSuccess() {
        when(channelRepository.findById(any())).thenReturn(Optional.of(createChannel()));
        when(accountRepository.findById(any())).thenReturn(Optional.of(createAccount()));
        String channelString = subscribeService.addSubscriber(1L, createAccount());
        Assertions.assertNotNull(channelString);
        Assertions.assertEquals("YOU SUBSCRIBED TO CHANNEL test", channelString);
        System.out.println(channelString);
    }
}