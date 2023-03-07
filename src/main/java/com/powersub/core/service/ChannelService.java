package com.powersub.core.service;

import com.powersub.core.entity.Account;
import com.powersub.core.entity.Channel;
import com.powersub.core.entity.ChannelDTO;
import com.powersub.core.repository.ChannelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

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

    public ChannelDTO createChannel(ChannelDTO channel, Account account) {
        Channel ch = Channel.builder()
                .title(channel.getTitle())
                .description(channel.getDescription())
                .owner(account)
                .createdAt(ZonedDateTime.now(clock))
                .build();

        Channel save = channelRepository.save(ch);

        return new ChannelDTO(save.getTitle(),
                save.getDescription());

    }

    public Channel updateChannel(Long id, ChannelDTO channelDTO, Account account) {
        Optional<Channel> channelById = channelRepository.findById(id);

        return channelById.map(channel -> {
            if (account.getId() == channelById.get().getOwner().getId()) {
                channel.setTitle(channelDTO.getTitle());
                channel.setDescription(channelDTO.getDescription());
                channel.setCreatedAt(ZonedDateTime.now());
                return channelRepository.save(channel);
            } else {
                return null;
            }
        }).orElse(null);
    }
}