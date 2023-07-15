package com.example.chat.user.domain.entity;

import com.example.chat.message.domain.entity.Message;
import com.example.chat.room.domain.entity.Room;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String username;
    private String password;
    private Timestamp createdAt;

    @ManyToMany
    @JoinTable(name = "user_room",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id"))
    private List<Room> rooms;

    @OneToMany(mappedBy = "user")
    private List<Message> messages;
}
