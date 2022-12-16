package com.reservation.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.reservation.exception.ReservationNotFoundException;
import com.reservation.model.Reservation;
import com.reservation.service.ReservationServiceImplementation;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
	@Autowired
	private ReservationServiceImplementation service;

	@Autowired
	private ReservationAuthenticationService authenticationService;

	Logger log = LoggerFactory.getLogger(ReservationController.class);

	@GetMapping("/all")
	public ResponseEntity<List<Reservation>> showAllReservationDetails(@RequestHeader("Authorization") String token)
			throws ReservationNotFoundException {
		try {
			if (authenticationService.isSessionValid(token)) {
				List<Reservation> reservations = service.showAllReservationDetails();
				if (reservations.isEmpty()) {
					return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
				}
				log.debug("Reservations are {}", reservations);
				return new ResponseEntity<>(reservations, HttpStatus.OK);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Reservation> showReservationDetailsById(@PathVariable("id") int id,
			@RequestHeader("Authorization") String token) throws ReservationNotFoundException {
		try {
			if (authenticationService.isSessionValid(token)) {
				Reservation reservation = service.showReservationById(id);
				if (reservation != null) {
					log.debug("Reservations: {}", reservation);
					return new ResponseEntity<>(reservation, HttpStatus.OK);
				} else
					return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@PostMapping("/addreservation")
	public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation,
			@RequestHeader("Authorization") String token) throws ReservationNotFoundException {
		try {
			if (authenticationService.isSessionValid(token)) {
				Reservation r = service.addReservation(reservation);
				if (reservation != null) {
					log.debug("Reservation: {}", r);
					return new ResponseEntity<>(r, HttpStatus.OK);
				} else
					return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@PutMapping("/updatereservation")
	public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation,
			@RequestHeader("Authorization") String token) throws ReservationNotFoundException {
		try {
			if (authenticationService.isSessionValid(token)) {
				Reservation r = service.updateReservation(reservation);
				if (r != null) {
					log.debug("Reservation: {}", r);
					return new ResponseEntity<>(r, HttpStatus.CREATED);
				}
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteReservation(@PathVariable("id") int id,
			@RequestHeader("Authorization") String token) throws ReservationNotFoundException {
		try {
			if (authenticationService.isSessionValid(token)) {
				service.deleteReservation(id);
				return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}
}
