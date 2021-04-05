package model.constanta;

public enum Gender {
    MALE(1),
    FEMALE(2);

    private final int gender;

    Gender(int method) {
        gender = method;
    }

    public int getGender() {
        return gender;
    }

    public static Gender convert(int source) {
        for(Gender mop : Gender.values()) {
            if(mop.getGender() == source) {
                return mop;
            }
        }
        return null;
    }
}
