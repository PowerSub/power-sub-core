package com.powersub.core.service;

import com.powersub.core.entity.Channel;
import com.powersub.core.entity.ChannelDTO;
import com.powersub.core.repository.AccountRepository;
import com.powersub.core.repository.ChannelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChannelService {

  private final ChannelRepository channelRepository;
  private final AccountRepository accountRepository;

  public List<Channel> getChannels() {
    return channelRepository.findAll();
  }

  public Channel getChannelById(Long id) {

    Optional<Channel> foundChannel = channelRepository.findById(id);
    return foundChannel.orElse(null);
  }

  public void createChannel(ChannelDTO channel) {
    Channel ch = Channel.builder().
            title(channel.getTitle()).
            description(channel.getDescription()).
            build();

    channelRepository.save(ch);
  }

  public Channel updateChannel(Long id, ChannelDTO channelDTO) {
    Optional<Channel> channelToUpdate = channelRepository.findById(id);
    if (channelToUpdate.isPresent()) {
      channelToUpdate.get().setTitle(channelDTO.getTitle());
      channelToUpdate.get().setDescription(channelDTO.getDescription());
      channelRepository.save(channelToUpdate.orElseThrow());
    }
    return channelToUpdate.orElse(null);
  }

  public void delete(Long id) {
    channelRepository.deleteById(id);
  }
}
