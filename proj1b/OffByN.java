public class OffByN implements CharacterComparator{
    private int N;
    @Override
    public boolean equalChars(char x, char y) {
        if(Math.abs(x - y) == this.N) return true;
        return false;
    }

    /** Constructor */
    public OffByN(int N) {
        this.N = N;
    }

}
