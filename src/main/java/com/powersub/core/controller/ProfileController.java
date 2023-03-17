package com.powersub.core.controller;

import com.powersub.core.entity.Channel;
import com.powersub.core.entity.ChannelDTO;
import com.powersub.core.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("profiles")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("{id}")
    public List<ChannelDTO> getAllChannels(@PathVariable long id) {
        Set<Channel> allChannels = profileService.getAll(id);
        List<Channel> list = new ArrayList<>(allChannels);
        List<ChannelDTO> listDTO = new ArrayList<>();
        for (Channel ch : list) {
            ChannelDTO chDTO = new ChannelDTO();
            chDTO.setDescription(ch.getDescription());
            chDTO.setTitle(ch.getTitle());
            listDTO.add(chDTO);
        }
        return listDTO;
    }
}
