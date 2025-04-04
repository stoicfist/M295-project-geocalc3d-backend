package ch.ilv.m295.demoapp.dataaccess;

import ch.ilv.m295.demoapp.vehicle.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(VehicleRepository repository) {

        return args -> {
            //log.info("Preloading " + repository.save(new Vehicle("BL 123456", null, VehicleType.CAR)));
            //log.info("Preloading " + repository.save(new Vehicle(null, "Bike 1", VehicleType.BICYCLE)));
        };
    }
}