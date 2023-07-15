package com.example.chat.room.service;

import com.example.chat.room.domain.entity.Room;
import com.example.chat.room.domain.request.RoomRequest;
import com.example.chat.room.domain.response.RoomResponse;
import com.example.chat.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

   public void joinRoom(RoomRequest request) {
       roomRepository.save(request.toEntity());
   }

   public List<RoomResponse> findAll() {
       List<Room> all = roomRepository.findAll();
       return all.stream().map(RoomResponse::new).toList();
   }

   public RoomResponse findById(Long id) {
       Optional<Room> byId = roomRepository.findById(id);
       Room room = byId.orElseThrow(() -> new RuntimeException("NOT FOUND ROOM BY " + id));
       return new RoomResponse(room);
   }

   public void leaveRoom(Long id) {
       roomRepository.deleteById(id);
   }
}
