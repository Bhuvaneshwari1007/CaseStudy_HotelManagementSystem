package com.receptionist.feignclient;

import java.util.List;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.receptionist.model.Room;

@FeignClient(name="RoomService", url="http://localhost:9001/room")
public interface RoomFeignClient {
							
			@GetMapping("/all")
			public ResponseEntity<List<Room>> showAllRoom(@RequestHeader("Authorization") String token);
			
			@GetMapping("/{id}")
			public ResponseEntity<Room> showRoomById(int id,@RequestHeader("Authorization") String token);
}
