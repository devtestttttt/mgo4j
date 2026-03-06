package io.mangonet.mgo.bcs.types.intent;

import java.util.Objects;

public class IntentMessage<T> {

    private final Intent intent;
    private final T value;
    
    public IntentMessage(Intent intent, T value) {
        this.intent = Objects.requireNonNull(intent);
        this.value = Objects.requireNonNull(value);
    }

    public IntentMessage(IntentScope intentScope, T value) {
        this.intent = new Intent(intentScope);
        this.value = Objects.requireNonNull(value);
    }
    
    public Intent getIntent() {
        return intent;
    }
    
    public T getValue() {
        return value;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        IntentMessage<?> that = (IntentMessage<?>) obj;
        return Objects.equals(intent, that.intent) &&
               Objects.equals(value, that.value);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(intent, value);
    }
    
    @Override
    public String toString() {
        return "IntentMessage{" +
               "intent=" + intent +
               ", value=" + value +
               '}';
    }
} 