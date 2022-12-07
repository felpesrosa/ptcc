public class PrimeNumber {
    static int numberToCheck;
    static int[] testedNumbers;
    static boolean isPrime;

    public PrimeNumber(int numberToCheck) {
        this.numberToCheck = numberToCheck;
        this.testedNumbers = new int[numberToCheck / 2];

        isPrimeNumber();
    }

    public static boolean isPrimeNumber() {
        isPrime = true;
        for (int factor = 2; factor < numberToCheck / 2; factor++) {
            testedNumbers[factor - 2] = factor;
            if (numberToCheck % factor == 0) {
                isPrime = false;
                break;
            }
        }

        return isPrime;
    }

    private String testedNumbersToString() {
        String s = "{ ";
        for (int i : testedNumbers) {
            if(i == 0) { break; }
            s += i + " ";
        }
        s += "}";

        return s;
    }

    @Override
    public String toString() {
        return numberToCheck + ", " + isPrime + ", " + testedNumbersToString();
    }

}
