package com.powersub.core.controller;

import com.powersub.core.entity.Channel;
import com.powersub.core.entity.ChannelDTO;
import com.powersub.core.service.ChannelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<List<ChannelDTO>> getAll() {
    //todo как это сделать с помощью стрима
    List<Channel> channels = channelService.getChannels();
    List<ChannelDTO> listDTO = new ArrayList<>();
    for (Channel ch : channels) {
      ChannelDTO chDTO = new ChannelDTO();
      chDTO.setDescription(ch.getDescription());
      chDTO.setTitle(ch.getTitle());
      listDTO.add(chDTO);
    }

    return new ResponseEntity<>(listDTO, HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<ChannelDTO> getChannel(@PathVariable Long id) {
    Channel channelById = channelService.getChannelById(id);
    ChannelDTO channelDTO = new ChannelDTO(channelById.getTitle(),
            channelById.getDescription());

    return new ResponseEntity<>(channelDTO, HttpStatus.OK);
  }

  @PostMapping("/create-channel")
  public ResponseEntity<ChannelDTO> createChannel(@RequestBody @Valid ChannelDTO channel) {
    channelService.createChannel(channel);
    return new ResponseEntity<>(channel, HttpStatus.OK);
  }

  @PutMapping("{id}")
  public ResponseEntity<ChannelDTO> updateChannel(@PathVariable Long id,
                                                  @RequestBody @Valid ChannelDTO channelDTO) {

    Channel channel = channelService.updateChannel(id, channelDTO);
    channelDTO = new ChannelDTO(channel.getTitle(),
            channel.getDescription());
    return new ResponseEntity<>(channelDTO, HttpStatus.OK);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<HttpStatus> deleteChannel(@PathVariable Long id) {
    channelService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}