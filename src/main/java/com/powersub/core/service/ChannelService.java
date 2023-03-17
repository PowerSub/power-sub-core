package com.powersub.core.service;

import com.powersub.core.entity.Account;
import com.powersub.core.entity.Channel;
import com.powersub.core.entity.ChannelDTO;
import com.powersub.core.exception.ChannelDoesNotExistException;
import com.powersub.core.exception.InvalidChannelPermissionException;
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
        return foundChannel.orElseThrow(() -> new ChannelDoesNotExistException("This channel does not exist"));
    }

    public ChannelDTO createChannel(ChannelDTO channelDTO, Account authAccount) {
      //todo return Channel.class
        Channel ch = Channel.builder()
                .title(channelDTO.getTitle())
                .description(channelDTO.getDescription())
                .owner(authAccount)
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
                return channelRepository.save(channel);
            } else {
                throw new InvalidChannelPermissionException("Update failed, permission denied");
            }
        }).orElseThrow(() -> new ChannelDoesNotExistException("This channel does not exist"));
    }
}