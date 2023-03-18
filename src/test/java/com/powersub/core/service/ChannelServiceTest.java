package com.powersub.core.service;

import com.powersub.core.entity.Account;
import com.powersub.core.entity.Channel;
import com.powersub.core.entity.ChannelDTO;
import com.powersub.core.repository.ChannelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
class ChannelServiceTest {

    ChannelService channelService;
    private static final Set<Account> SUBSCRIBERS = new HashSet<>();
    private static final Set<Channel> CHANNELS = new HashSet<>();

    @MockBean
    ChannelRepository channelRepository;

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

    private static ChannelDTO createChannelDTO() {
        return new ChannelDTO("test", "description test");
    }

    @BeforeEach
    void before() {
        Clock clock = Clock.systemUTC();
        channelService = new ChannelService(channelRepository, clock);
    }

    @Test
    void whenCreateChannel_thenSuccess() {
        when(channelRepository.save(any(Channel.class))) //todo почему нельзя вставить метод createChannel()
                .thenReturn(createChannel());
        ChannelDTO input = createChannelDTO();
        ChannelDTO output = channelService.createChannel(input, createAccount());
        Assertions.assertNotNull(output);
        Assertions.assertEquals(input.getTitle(), output.getTitle());
    }

    @Test
    void whenGetAllChannels_thenSuccess() {
        when(channelRepository.findAll())
                .thenReturn(List.of(createChannel()));
        List<Channel> channels = channelService.getChannels();
        Assertions.assertNotNull(channels);
        Assertions.assertEquals(1, channels.size());
    }

    @Test
    void whenGetIdChannel_thenSuccess() {
        when(channelRepository.findById(any()))
                .thenReturn(Optional.of(createChannel()));
        Channel channelById = channelService.getChannelById(1L);
        Assertions.assertNotNull(channelById);
        Assertions.assertEquals("test", channelById.getTitle());
    }

    @Test
    void whenUpdateChannel_thenSuccess() {
        when(channelRepository.findById(1L))
                .thenReturn(Optional.of(createChannel()));

        when(channelRepository.save(createChannel()))
                .thenReturn(createChannel());

        Channel updateChannel = channelService.updateChannel(1L,
                new ChannelDTO("tttt", "rfefsdv"),
                createAccount());

        Assertions.assertNotNull(updateChannel);
        Assertions.assertEquals("test", updateChannel.getTitle());

    }
}
