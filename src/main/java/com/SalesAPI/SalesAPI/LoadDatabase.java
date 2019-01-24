package com.SalesAPI.SalesAPI;

import com.SalesAPI.SalesAPI.ItemData.ItemRepository;
import com.SalesAPI.SalesAPI.ItemData.SalesItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatab(ItemRepository repository) {
        return args -> {

            log.info("Preloading " + repository.save(new SalesItem("a", "good quality for a low price", 70,
                    23, "Lithuania", "Vilnius", "Gedimino g", "Latitude: 68.24242, Longitude: -144.71742, Distortion: 7.28")));

            log.info("Preloading " + repository.save(new SalesItem("c", "Procesoriaus modelis Intel. Spartinanti atmintis (MB) 22 MB Sisteminė magistralė (MHz)2666 MHz Procesoriaus lizdo tipas LGA2066", 1800,
                    17, "Lithuania", "Vilnius", "Kalvariju g", "Latitude: -28.50547, Longitude: 140.96112, Distortion: 1.29")));

            log.info("Preloading " + repository.save(new SalesItem("b", "Instaliuota vaizdo atmintis 11264 MB, HDMI jungtis 1. Atminties magistralė 352-bit", 1218,
                    23, "Lithuania", "Vilnius", "Gedimino g", "Latitude: -78.37753, Longitude: -55.66267, Distortion: 24.64")));

        };
    }
}
