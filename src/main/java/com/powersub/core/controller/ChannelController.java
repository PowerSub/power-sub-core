package com.powersub.core.controller;

import com.powersub.core.entity.Account;
import com.powersub.core.entity.Channel;
import com.powersub.core.entity.ChannelDTO;
import com.powersub.core.mappers.ChannelMapper;
import com.powersub.core.service.ChannelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RequestMapping("/channels")
@RestController
public class ChannelController {

    private final ChannelService channelService;

    @Operation(summary = "Return all channels", tags = "channels")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "all channels return",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    })
    })
    @GetMapping()
    public List<ChannelDTO> getAll() {
        return ChannelMapper.INSTANCE.listChannelsToChannelDTOList(channelService.getChannels());
    }

    @Operation(summary = "Return channel", tags = "channels")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "return channel",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    })
    })
    @GetMapping("{id}")
    public ChannelDTO getChannel(@PathVariable Long id) {
        return ChannelMapper.INSTANCE.channelToChannelDTO(channelService.getChannelById(id));
    }

    @Operation(summary = "Create channel", tags = "channels")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Channel created",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    })
    })
    @PostMapping("/create")
    public ChannelDTO createChannel(@RequestBody @Valid ChannelDTO channel,
                                    @AuthenticationPrincipal Account account) {
        return ChannelMapper.INSTANCE.channelToChannelDTO(channelService.createChannel(channel, account));
    }

    @Operation(summary = "Update channel", tags = "channels")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Channel updated",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    })
    })
    @PutMapping("{id}")
    public ChannelDTO updateChannel(@PathVariable Long id,
                                    @RequestBody @Valid ChannelDTO channelDTO,
                                    @AuthenticationPrincipal Account account) {
        Channel channel = channelService.updateChannel(id, channelDTO, account);
        return ChannelMapper.INSTANCE.channelToChannelDTO(channel);
    }
}