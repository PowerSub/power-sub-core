package com.powersub.core.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

  @ManyToMany(mappedBy = "channelsSubscriber")
  private Set<Account> accountsSubscriber = new HashSet<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Channel channel = (Channel) o;
    return Objects.equals(title, channel.title) && Objects.equals(description, channel.description) && Objects.equals(accountsSubscriber, channel.accountsSubscriber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, description, accountsSubscriber);
  }
}
