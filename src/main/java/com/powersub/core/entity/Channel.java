package com.powersub.core.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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

  @NotEmpty
  @Size(min = 3, max = 256)
  @Column(name = "title")
  private String title;

  @Size(max = 1024)
  @Column(name = "description")
  private String description;
}
