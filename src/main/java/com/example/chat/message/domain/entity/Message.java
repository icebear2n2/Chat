package com.example.chat.message.domain.entity;

import com.example.chat.room.domain.entity.Room;
import com.example.chat.user.domain.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Getter
@Table(name = "messages")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Timestamp createdAt;

    @ManyToOne
    private User user;

    @ManyToOne
    private Room room;

}
