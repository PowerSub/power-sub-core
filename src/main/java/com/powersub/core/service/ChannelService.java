package com.powersub.core.service;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.powersub.core.entity.Account;
import com.powersub.core.entity.Channel;
import com.powersub.core.entity.ChannelDTO;
import com.powersub.core.repository.ChannelRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ChannelService {

    private final ChannelRepository channelRepository;
    private final Clock clock;

    public List<Channel> getChannels() {
        return channelRepository.findAll();
    }

    public Channel getChannelById(Long id) {

        Optional<Channel> foundChannel = channelRepository.findById(id);
        return foundChannel.orElse(null);
    }

    public void createChannel(ChannelDTO channel, Account account) {
        Channel ch = Channel.builder()
                .title(channel.getTitle())
                .description(channel.getDescription())
                .owner(account)
                .createdAt(ZonedDateTime.now(clock))
                .build();

        channelRepository.save(ch);
    }

    public Channel updateChannel(Long id, ChannelDTO channelDTO) {
        //TODO owner validation (чтобы владелец мог менять только свой канал)
        return channelRepository.findById(id).map(channel -> {
            channel.setTitle(channelDTO.getTitle());
            channel.setDescription(channelDTO.getDescription());
            return channelRepository.save(channel);
        }).orElse(null);
    }
}
