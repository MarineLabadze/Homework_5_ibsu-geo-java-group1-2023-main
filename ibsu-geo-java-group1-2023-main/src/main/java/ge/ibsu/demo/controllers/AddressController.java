package ge.ibsu.demo.controllers;

import ge.ibsu.demo.entities.Address;
import ge.ibsu.demo.entities.City;
import ge.ibsu.demo.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/add-address")
    public ResponseEntity<Address> addAddress(@RequestBody Address address) {
        Address addedAddress = addressService.addAddress(address);
        return new ResponseEntity<>(addedAddress, HttpStatus.CREATED);
    }

    @GetMapping("/all-cities")
    public ResponseEntity<List<City>> getAllCities() {
        List<City> cities = addressService.getAllCities();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Address> getAll() {
        return addressService.getAll();
    }
}

