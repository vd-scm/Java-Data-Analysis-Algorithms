// CountryData class extends Country class and implements Findable interface
// includes data of a country's Factbook's attributes and Enrollment data year

import java.io.IOException;

public class CountryData extends Country implements Findable {
    private double[] enrollment = new double[60]; // Array; years 1960-2019
    private double[] facts = new double[45]; // Array; FactBook attributes (columns)

    // default constructor
    public CountryData() {}

    // getters
    public double[] getEnrollment() { return enrollment; }
    public double[] getFacts() { return facts; }

    // setters
    public void setEnrollment(double[] data) {
        // copy data into enrollment array
        for (int i = 0; i < data.length; ++i) {
            enrollment[i] = data[i];
        }
    }

    public void setFacts(double[] data) {
        // copy data into facts array
        for (int i = 0; i < data.length; ++i) {
            facts[i] = data[i];
        }
    }

    // gets specific FactBook attribute
    public double findFact(int attribute) throws IOException {
        if (attribute < AREA || attribute > UNEMPLOYMENT) {
            throw new IOException("Bad attribute provided " + attribute);
        }
        return facts[attribute - 1] != 0 ? facts[attribute - 1] : NO_DATA;
    }

    // gets specifc Enrollment year
    public double findEnrollmentByYear(int year) throws IOException {
        if (year < 1960 || year > 2019) {
            throw new IOException("Bad year provided " + year);
        }
        return enrollment[year - 1960] != 0 ? enrollment[year - 1960] : NO_DATA;
    }

    // toString public instance method
    public String toString() {
        return "CountryData{code='" + getCode() + "', name='" + getName() +
                "', enrollment=" + java.util.Arrays.toString(enrollment) +
                ", facts=" + java.util.Arrays.toString(facts) + "}";
    }
}
