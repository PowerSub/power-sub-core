package com.powersub.core.service;

import com.powersub.core.entity.Channel;
import com.powersub.core.repository.ChannelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@AllArgsConstructor
@Service
public class ProfileService {

    ChannelRepository channelRepository;

    public Set<Channel> getAll(long id) {
       return channelRepository.findChannelsBySubscribersId(id); //todo помоему ненужный метод
    }
}
