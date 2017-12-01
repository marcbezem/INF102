package readCountries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by knutandersstokke on 15 15.10.2017.
 */
public class ReadCountries {

    public static void main(String[] args) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream resource = classloader.getResourceAsStream("country-capitals.csv")) {
            List<Country> countries = new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8)).lines()
                    .skip(1)
                    .map(
                            line -> {
                                String[] fields = line.split(",");
                                return new Country(fields[0], fields[1], fields[5], Double.valueOf(fields[2]), Double.valueOf(fields[3]));
                            }
                    ).collect(Collectors.toList());
            System.out.println(countries);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Country {
        String name, capital, continent;
        double latitude, longitude;

        public Country(String name, String capital, String continent, double latitude, double longitude) {
            this.name = name;
            this.capital = capital;
            this.continent = continent;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        @Override
        public String toString() {
            return "Country{" +
                    "name='" + name + '\'' +
                    ", capital='" + capital + '\'' +
                    ", continent='" + continent + '\'' +
                    ", latitude=" + latitude +
                    ", longitude=" + longitude +
                    '}';
        }
    }
}
