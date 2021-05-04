package lt.codeacademy.model.constanta;

public enum Candidates {
    KAZYS_VOLCIUNAS(1),
    PRANAS_NUZMAUSKAS(2);

    private final int candidateValue;

    Candidates(int value) {
        candidateValue = value;
    }

    public int getCandidate() {
        return candidateValue;
    }

    public static Candidates convert(int source) {
        for (Candidates mop : Candidates.values()) {
            if (mop.getCandidate() == source) {
                return mop;
            }
        }
        return null;
    }
}
