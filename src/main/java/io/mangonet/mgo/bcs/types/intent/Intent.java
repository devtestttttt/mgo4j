package io.mangonet.mgo.bcs.types.intent;

import java.util.Objects;

public class Intent {
    
    private final IntentScope scope;
    private final IntentVersion version;
    private final AppId appId;
    
    public Intent(IntentScope scope, IntentVersion version, AppId appId) {
        this.scope = Objects.requireNonNull(scope);
        this.version = Objects.requireNonNull(version);
        this.appId = Objects.requireNonNull(appId);
    }

    public Intent(IntentScope scope) {
        this.scope = scope;
        this.version = IntentVersion.V0.INSTANCE;
        this.appId = AppId.Mgo.INSTANCE;
    }
    
    public IntentScope getScope() {
        return scope;
    }
    
    public IntentVersion getVersion() {
        return version;
    }
    
    public AppId getAppId() {
        return appId;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Intent intent = (Intent) obj;
        return Objects.equals(scope, intent.scope) &&
               Objects.equals(version, intent.version) &&
               Objects.equals(appId, intent.appId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(scope, version, appId);
    }
    
    @Override
    public String toString() {
        return "Intent{" +
               "scope=" + scope +
               ", version=" + version +
               ", appId=" + appId +
               '}';
    }
} 