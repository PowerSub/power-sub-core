package com.powersub.core.repository;

import com.powersub.core.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {
    Set<Channel> findChannelsBySubscribersId(Long id);
}
