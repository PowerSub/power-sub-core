package com.powersub.core.controller;

import com.powersub.core.entity.ChannelDTO;
import com.powersub.core.service.ChannelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
public class ChannelController {

  private final ChannelService channelService;

  @PostMapping("/createchannel")
   public ResponseEntity<ChannelDTO> createChannel(@RequestBody @Valid ChannelDTO channel)  {
    channelService.createChannel(channel);
    return new ResponseEntity<>(channel, HttpStatus.OK);
   }

}
