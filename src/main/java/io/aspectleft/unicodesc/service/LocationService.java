package io.aspectleft.unicodesc.service;


import io.aspectleft.unicodesc.dto.LocationRequest;
import io.aspectleft.unicodesc.dto.LocationResponse;
import io.aspectleft.unicodesc.exception.UnknownLocationException;
import io.aspectleft.unicodesc.mapper.LocationMapper;
import io.aspectleft.unicodesc.model.Location;
import io.aspectleft.unicodesc.model.User;
import io.aspectleft.unicodesc.repository.LocationRepository;
import io.aspectleft.unicodesc.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class LocationService {
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;
    private final AuthService authService;
    private final UserRepository userRepository;

    public void save(LocationRequest locationRequest) {
        locationRepository.save(locationMapper.map(locationRequest, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public LocationResponse getLatestLocationByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return locationMapper.mapToDto(locationRepository.findTopByUserOrderByCreatedDesc(user).orElseThrow(() ->
                new UnknownLocationException(username + "'s location is unknown")));
    }

    @Transactional(readOnly = true)
    public List<String> findNearUsers() {
        User user = authService.getCurrentUser();
        Location centerLocation = locationRepository.findTopByUserOrderByCreatedDesc(user).orElseThrow(() ->
                new UnknownLocationException("Your location is unknown"));
        return locationRepository.getCurrentLocations()
                .stream()
                .filter(o -> distance(centerLocation.getLatitude(), o.getLatitude(),
                        centerLocation.getLongitude(), o.getLongitude(), 0.0, 0.0) < 1000)
                .sorted(Comparator.comparingDouble(o -> distance(centerLocation.getLatitude(), o.getLatitude(),
                        centerLocation.getLongitude(), o.getLongitude(), 0.0, 0.0)))
                .limit(50)
                .map(location -> location.getUser().getUsername())
                .collect(Collectors.toList());
    }

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     * @return Distance in Meters
     */
    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

    @Transactional(readOnly = true)
    public List<LocationResponse> getAllCurrentLocation() {
        return locationRepository.getCurrentLocations()
                .stream()
                .map(locationMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
