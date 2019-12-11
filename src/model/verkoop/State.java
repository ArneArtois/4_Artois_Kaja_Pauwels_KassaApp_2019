package model.verkoop;

public interface State {
    default void betaal(){throw new IllegalStateException("IllegalStateException betalen"); }
    default void maak(){throw new IllegalStateException("IllegalStateException maak");}
    default void brengTerug(){throw new IllegalStateException("IllegalStateException brengterug"); }
    default void reset(){throw new IllegalStateException("IllegalStateException reset");}
    default void annuleer(){throw new IllegalStateException("IllegalStateException betalen"); }
}
