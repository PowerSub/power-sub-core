package com.powersub.core.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "channels")
public class Channel {

  @Id
  @Column(name = "channel_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long channelId;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;
}
