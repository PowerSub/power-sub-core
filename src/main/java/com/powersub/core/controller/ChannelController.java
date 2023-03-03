package com.powersub.core.controller;

import com.powersub.core.entity.Account;
import com.powersub.core.entity.Channel;
import com.powersub.core.entity.ChannelDTO;
import com.powersub.core.service.ChannelService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RequestMapping("/channels")
@RestController
public class ChannelController {

    private final ChannelService channelService;

    @GetMapping()
    public List<ChannelDTO> getAll() {
        //todo to mapstruct - НЕ СЕЙЧАС
        List<Channel> channels = channelService.getChannels();
        List<ChannelDTO> listDTO = new ArrayList<>();
        for (Channel ch : channels) {
            ChannelDTO chDTO = new ChannelDTO();
            chDTO.setDescription(ch.getDescription());
            chDTO.setTitle(ch.getTitle());
            listDTO.add(chDTO);
        }

        return listDTO;
    }

    @GetMapping("{id}")
    public ChannelDTO getChannel(@PathVariable Long id) {
        Channel channelById = channelService.getChannelById(id);
        return new ChannelDTO(channelById.getTitle(),
                channelById.getDescription());
    }

    @PostMapping("/create")
    public ChannelDTO createChannel(@RequestBody @Valid ChannelDTO channel, @AuthenticationPrincipal Account account) {
        return channelService.createChannel(channel, account);
    }

    @PutMapping("{id}")
    public ChannelDTO updateChannel(@PathVariable Long id,
                                    @RequestBody @Valid ChannelDTO channelDTO,
                                    @AuthenticationPrincipal Account account) {
        Channel channel = channelService.updateChannel(id, channelDTO, account);
        return new ChannelDTO(channel.getTitle(),
                channel.getDescription());
    }

}