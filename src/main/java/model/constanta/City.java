package model.constanta;

public enum City {
    VILNIUS(1),
    KAUNAS(2);

    private final int city;

    City(int method) {
        city = method;
    }

    public int getCity() {
        return city;
    }

    public static City convert(int source) {
        for(City mop : City.values()) {
            if(mop.getCity() == source) {
                return mop;
            }
        }
        return null;
    }
}
