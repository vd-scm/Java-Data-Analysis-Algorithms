// imports data from FactBook & Enrollment CSV files

import java.io.*;
import java.util.*;

public class WorldData {
    private CountryData[] countryData = new CountryData[265]; // CountryData objects, 265 countries/rows
    private int count = 0; // numbers of countries in array

    // default constructor
    public WorldData() {}

    // instance method, add CountryData object to countryData array
    public void addCountry(CountryData country) {
        countryData[count++] = country; // increments count
    }

    public void loadEnrollmentData() throws FileNotFoundException {
        File file = new File("/Users/david/Desktop/API_SE.SEC.NENR_DS2_en_csv_v2_1347167.csv");
        Scanner scanner = new Scanner(file);
        scanner.nextLine(); // skip headers
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            String[] data = scanner.nextLine().split(","); // Split using comma
            if (data.length < 2) continue; // Skip invalid rows

            String countryName = data[0].replace("\"", "").trim();
            String countryCode = data[1].replace("\"", "").trim();
            double[] enrollment = new double[60]; // data starts from 1960

            for (int i = 2; i < 62; ++i) {
                String value = data[i].trim().replace("\"", "");
                enrollment[i - 2] = value.isEmpty() ? CountryData.NO_DATA : Double.parseDouble(value);
            }
            CountryData country = new CountryData();
            country.setName(countryName);
            country.setCode(countryCode);
            country.setEnrollment(enrollment);
            addCountry(country);
        }
        scanner.close();
    }

    public void importFactBookData() throws ImportException, FileNotFoundException {
        File file = new File("/Users/david/Desktop/factbook.csv");
        Scanner scanner = new Scanner(file);

        scanner.nextLine();
        scanner.nextLine();
        int failCount = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] data = line.split(";", -1); // split using semi-colon
            if (data.length < 45) {
                failCount++;
                continue;
            }

            String countryName = data[0].replace("\"", "").trim();
            double[] facts = new double[45];

            for (int i = 1; i < 45; ++i) {
                facts[i - 1] = data[i].isEmpty() ? CountryData.NO_DATA : parseDouble(data[i]);
            }

            // maps column indices to 12 attributes
            facts[Findable.AREA - 1] = parseDouble(data[1]);
            facts[Findable.BIRTH_RATE - 1] = parseDouble(data[2]);
            facts[Findable.DEATH_RATE - 1] = parseDouble(data[4]);
            facts[Findable.GDP - 1] = parseDouble(data[9]);
            facts[Findable.HIV_DEATHS - 1] = parseDouble(data[13]);
            facts[Findable.INFANT_MORTALITY - 1] = parseDouble(data[18]);
            facts[Findable.LABOR_FORCE - 1] = parseDouble(data[23]);
            facts[Findable.LIFE_EXPECTANCY - 1] = parseDouble(data[24]);
            facts[Findable.POPULATION - 1] = parseDouble(data[37]);
            facts[Findable.MOBILE_PHONES - 1] = parseDouble(data[42]);
            facts[Findable.FERTILITY_RATE - 1] = parseDouble(data[43]);
            facts[Findable.UNEMPLOYMENT - 1] = parseDouble(data[44]);

            boolean found = false;
            for (int i = 0; i < count; i++) {
                String enrollmentCountryName = countryData[i].getName().trim();
                if (normalizeName(enrollmentCountryName).equalsIgnoreCase(normalizeName(countryName))) {
                    countryData[i].setFacts(facts);
                    found = true;
                    break;
                }
            }
            if (!found) {
                failCount++;
            }
        }
        scanner.close();

        if (failCount > 0) {
            throw new ImportException(failCount);
        }
    }

    // parse a string into double; handle null, empty, and invalid values and returns -1
    private double parseDouble(String value) {
        if (value == null || value.trim().isEmpty() || value.equals("\"\"")) {
            return -1; // Default missing values to -1
        }
        try {
            return Double.parseDouble(value.replace("\"", "").trim());
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format: " + value);
            return -1;
        }
    }

    // generalize country names in factbook & enrollment csv
    private String normalizeName(String name) {
        name = name.trim().replaceAll("[^a-zA-Z ]", "").toLowerCase();

        if (name.contains(" rep")) {
            name = name.replace(" rep", "");
        }
        if (name.contains(" federation")) {
            name = name.replace(" federation", "");
        }
        if (name.contains(" arab") || name.contains(" united")) {
            name = name.replace(" arab", "").replace(" united", "");
        }
        return name;

    }

    // Finds a country by its country's code or name to String data
    public CountryData lookup(String codeOrName) {
        for (CountryData c : countryData) {
            if (c != null && (c.getCode().equalsIgnoreCase(codeOrName) || c.getName().equalsIgnoreCase(codeOrName))) {
                return c;
            }
        }
        return null;
    }

    // toString public instance method
    public String toString() {
        StringBuilder sb = new StringBuilder("WorldData{ countries=[");
        for (int i = 0; i < count; i++) {
            sb.append(countryData[i].toString());
            if (i < count - 1) sb.append(", ");
        }
        sb.append("]}");
        return sb.toString();
    }
}
