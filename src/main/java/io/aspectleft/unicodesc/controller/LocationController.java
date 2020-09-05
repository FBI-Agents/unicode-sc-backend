package io.aspectleft.unicodesc.controller;

import io.aspectleft.unicodesc.dto.LocationRequest;
import io.aspectleft.unicodesc.dto.LocationResponse;
import io.aspectleft.unicodesc.dto.TakeCourseRequest;
import io.aspectleft.unicodesc.exception.UnknownLocationException;
import io.aspectleft.unicodesc.model.User;
import io.aspectleft.unicodesc.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/location")
@AllArgsConstructor
public class LocationController {
    private final LocationService locationService;

    @PostMapping
    public ResponseEntity<Void> updateLocation(@RequestBody LocationRequest locationRequest) {
        locationService.save(locationRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("by-user/{username}")
    public ResponseEntity<LocationResponse> getUserLocation(@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK).body(locationService.getLatestLocationByUsername(username));
    }

    @GetMapping("near")
    public ResponseEntity<List<String>> findNear() {
        return ResponseEntity.status(HttpStatus.OK).body(locationService.findNearUsers());
    }
//
//    @GetMapping("all")
//    public ResponseEntity<List<LocationResponse>> getAllCurrentLocation() {
//        return ResponseEntity.status(HttpStatus.OK).body(locationService.getAllCurrentLocation());
//    }
}
