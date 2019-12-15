package model.verkoop;

public interface State {
    default void betaal(){throw new IllegalStateException("IllegalStateException betalen"); }
    /*default void maak(){throw new IllegalStateException("IllegalStateException maak");}
    default void brengTerug(){throw new IllegalStateException("IllegalStateException brengterug"); }*/
    default void eindigVerkoop() {
        throw new IllegalStateException("IllegalState: Beeindig");
    }
    default void reset(){throw new IllegalStateException("IllegalStateException reset");}
    default void annuleer(){throw new IllegalStateException("IllegalStateException betalen"); }
    default void onHold()  {
        throw new IllegalStateException("IllegalState: OnHold");
    }
    default void genoegGeld() {
        throw new IllegalStateException("IllegalState: GenoegGeld");
    }

    default void nietGenoegGeld() {
        throw new IllegalStateException("IllegalState: NietGenoegGeld");
    }

}
