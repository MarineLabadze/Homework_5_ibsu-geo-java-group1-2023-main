package ge.ibsu.demo.services;

import ge.ibsu.demo.dto.AddAddress;
import ge.ibsu.demo.entities.Address;
import ge.ibsu.demo.entities.City; // Import City class
import ge.ibsu.demo.repositories.AddressRepository;
import ge.ibsu.demo.repositories.CityRepository; // Import CityRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final CityRepository cityRepository; // Add CityRepository

    @Autowired
    public AddressService(AddressRepository addressRepository, CityRepository cityRepository) {
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
    }

    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    @Transactional
    public Address getAddress(AddAddress addAddress) {
        Address address = addressRepository.findOneByAddress(addAddress.getAddress());
        if (address != null) {
            return address;
        }
        address = new Address();
        address.setAddress(addAddress.getAddress());
        address.setPostalCode(addAddress.getPostalCode());

        return addressRepository.save(address);
    }

    public Address addAddress(Address address) {
        City existingCity = address.getCity();
        if (existingCity != null) {
            City savedCity = cityRepository.save(existingCity);
            address.setCity(savedCity);
        }

        return addressRepository.save(address);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
}

