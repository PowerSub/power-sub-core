package com.powersub.core.mappers;

import com.powersub.core.entity.Channel;
import com.powersub.core.entity.ChannelDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ChannelMapper {

    ChannelMapper INSTANCE = Mappers.getMapper(ChannelMapper.class);

    ChannelDTO channelToChannelDTO(Channel channel);


    List<ChannelDTO> listChannelsToChannelDTOList(List<Channel> channels);
}
