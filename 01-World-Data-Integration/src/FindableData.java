//
// Project: FindableData
// TODO 1
// Author: Vince David Muego
//

// main class, handles exceptions, loads data for queries
// static method showData for array values where there's no data

import java.io.IOException;

public class FindableData {
    public static void showData(double data) {
        System.out.println((data == CountryData.NO_DATA) ? "no data available" : data);
    }

    public static void main(String[] args) throws IOException {
        WorldData wd = new WorldData();
        CountryData c;
        wd.loadEnrollmentData();
        try {
            wd.importFactBookData();
        } catch (ImportException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("-----------------------------------");
        c = wd.lookup("DJI");
        System.out.print("Djibouti's Area: ");
        showData(c.findFact(CountryData.AREA));
        c = wd.lookup("YEM");
        System.out.print("Yemen's Birth Rate: ");
        showData(c.findFact(CountryData.BIRTH_RATE));

        // TODO 2: Add same queries for the other 10 attributes
        c = wd.lookup("AFG");
        System.out.print("Afghanistan's Death Rate: ");
        showData(c.findFact(CountryData.DEATH_RATE));

        c = wd.lookup("PHL");
        System.out.print("Philippines's GDP: ");
        showData(c.findFact(CountryData.GDP));

        c = wd.lookup("LKA");
        System.out.print("Sri Lanka's HIV Deaths: ");
        showData(c.findFact(CountryData.HIV_DEATHS));

        c = wd.lookup("EGY");
        System.out.print("Egypt Arab Rep's Infant Mortality: ");
        showData(c.findFact(CountryData.INFANT_MORTALITY));

        c = wd.lookup("CRI");
        System.out.print("Costa Rica's Labor Force: ");
        showData(c.findFact(CountryData.LABOR_FORCE));

        c = wd.lookup("IND");
        System.out.print("India's Life Expectancy: ");
        showData(c.findFact(CountryData.LIFE_EXPECTANCY));

        c = wd.lookup("TUR");
        System.out.print("Turkey's Population: ");
        showData(c.findFact(CountryData.POPULATION));

        c = wd.lookup("BRA");
        System.out.print("Brazil's Mobile Phones: ");
        showData(c.findFact(CountryData.MOBILE_PHONES));

        c = wd.lookup("USA");
        System.out.print("United State's Fertility Rate: ");
        showData(c.findFact(CountryData.FERTILITY_RATE));

        c = wd.lookup("ZWE");
        System.out.print("Zimbabwe's Unemployment: ");
        showData(c.findFact(CountryData.UNEMPLOYMENT));

        c = wd.lookup("MNA");
        System.out.println("Middle East / North Africa");
        System.out.print("enrollment (1960): ");
        showData(c.findEnrollmentByYear(1960));
        System.out.print("enrollment (1970): ");
        showData(c.findEnrollmentByYear(1970));

        // TODO 3: Add one more enrollment by year query
        System.out.print("enrollment (2000): ");
        showData(c.findEnrollmentByYear(2000));

        // TODO 4: Add try/catch with invalid findFact()
        try {
            c.findFact(13);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        // TODO 5: Add try/catch with invalid findEnrollmentByYear()
        try {
            c.findEnrollmentByYear(1950);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("-----------------------------------");
    }
}

// TODO 6
/*
[output]
81 non-matching or failed imports
-----------------------------------
Djibouti's Area: 23000.0
Yemen's Birth Rate: 43.07
Afghanistan's Death Rate: 20.75
Philippines's GDP: 4.306E11
Sri Lanka's HIV Deaths: 200.0
Egypt Arab Rep's Infant Mortality: 32.59
Costa Rica's Labor Force: 1810000.0
India's Life Expectancy: 64.35
Turkey's Population: 6.9660559E7
Brazil's Mobile Phones: 4.63733E7
United State's Fertility Rate: 2.08
Zimbabwe's Unemployment: 70.0
Middle East / North Africa
enrollment (1960): no data available
enrollment (1970): 21.34574
enrollment (2000): 60.76744
Bad attribute provided 13
Bad year provided 1950
-----------------------------------

Process finished with exit code 0

*/