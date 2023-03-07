package com.powersub.core.service;

import com.powersub.core.entity.Account;
import com.powersub.core.entity.Channel;
import com.powersub.core.entity.ChannelDTO;
import com.powersub.core.repository.ChannelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
//@RunWith(SpringRunner.class) - todo почему не запускается.
public class ChannelServiceTest {

    @InjectMocks
    @Autowired
    ChannelService channelService;

    @MockBean
    ChannelRepository channelRepository;
    @Autowired
    Clock clock;

    private static Account createAccount() {
        return new Account(1,
                "test@email.com",
                "12345678",
                ZonedDateTime.now());
    }

    private static Channel createChannel() {
        return new Channel(1,
                "test",
                "description test",
                ZonedDateTime.now(),
                createAccount());
    }

    private static ChannelDTO createChannelDTO() {
        return new ChannelDTO("test", "description test");
    }

    @Test
    void whenCreateChannel_thenSuccess() {
        Mockito.when(channelRepository.save(any(Channel.class))) //todo почему нельзя вставить метод createChannel()
                .thenReturn(createChannel());
        ChannelDTO input = createChannelDTO();
        ChannelDTO output = channelService.createChannel(input, createAccount());
        Assertions.assertNotNull(output);
        Assertions.assertEquals(input.getTitle(), output.getTitle());
    }

    @Test
    void whenGetAllChannels_thenSuccess() {
        Mockito.when(channelRepository.findAll())
                .thenReturn(List.of(createChannel()));
        List<Channel> channels = channelService.getChannels();
        Assertions.assertNotNull(channels);
        Assertions.assertEquals(1, channels.size());
    }

    @Test
    void whenGetIdChannel_thenSuccess() {
        Mockito.when(channelRepository.findById(any()))
                .thenReturn(Optional.of(createChannel()));
        Channel channelById = channelService.getChannelById(1L);
        Assertions.assertNotNull(channelById);
        Assertions.assertEquals("test", channelById.getTitle());
    }

    @Test
    void whenUpdateChannel_thenSuccess() {
        //todo тест не проходил. Null
        Mockito.when(channelRepository.findById(1L))
                .thenReturn(Optional.of(createChannel()));

//        Mockito.when(channelRepository.save(createChannel()))
//                .thenReturn(createChannel());

        Channel updateChannel = channelService.updateChannel(1L,
                new ChannelDTO("tttt", "rfefsdv"),
                createAccount());

        Assertions.assertNotNull(updateChannel);
        Assertions.assertEquals("test", updateChannel.getTitle());

    }
}
