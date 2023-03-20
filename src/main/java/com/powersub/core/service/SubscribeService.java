package com.powersub.core.service;

import com.powersub.core.entity.Account;
import com.powersub.core.entity.Channel;
import com.powersub.core.exception.AccountDoesNotExistException;
import com.powersub.core.exception.ChannelDoesNotExistException;
import com.powersub.core.repository.AccountRepository;
import com.powersub.core.repository.ChannelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SubscribeService {

    private final AccountRepository accountRepository;

    private final ChannelRepository channelRepository;

    public String addSubscriber(Long id, Account account) {
        Channel channel = channelRepository
                .findById(id)
                .orElseThrow(() -> new ChannelDoesNotExistException("Channel does not exist"));

        Account repoAcc = accountRepository
                .findById(account.getId())
                .orElseThrow(() -> new AccountDoesNotExistException("Account does not exist"));

        if (repoAcc.getChannels().contains(channel)) {
            repoAcc.getChannels().remove(channel);
            accountRepository.save(repoAcc);
            return "YOU UNSUBSCRIBED TO CHANNEL " + channel.getTitle();
        } else {
            repoAcc.getChannels().add(channel);
            accountRepository.save(repoAcc);
            return "YOU SUBSCRIBED TO CHANNEL " + channel.getTitle();
        }
    }
}
