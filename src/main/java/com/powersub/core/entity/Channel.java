package com.powersub.core.entity;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "channels")
@EqualsAndHashCode(of = "channelId")
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
  
  @Column(name = "created_at")
  private ZonedDateTime createdAt;
  
  @ManyToOne
  @JoinColumn(name = "owner_id")
  private Account owner;
  
}
