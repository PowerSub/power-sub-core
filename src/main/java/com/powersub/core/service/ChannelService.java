package com.powersub.core.service;

import com.powersub.core.entity.Channel;
import com.powersub.core.entity.ChannelDTO;
import com.powersub.core.repository.ChannelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChannelService {

  private final ChannelRepository channelRepository;

  public void createChannel(ChannelDTO channel) {
    Channel ch = Channel.builder().
            title(channel.getTitle()).
            description(channel.getDescription()).
            build();

    channelRepository.save(ch);
  }
}
