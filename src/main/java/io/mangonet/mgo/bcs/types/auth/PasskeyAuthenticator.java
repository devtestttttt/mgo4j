package io.mangonet.mgo.bcs.types.auth;

import java.util.Arrays;
import java.util.Objects;

public class PasskeyAuthenticator {
    
    private final byte[] authenticatorData;
    private final String clientDataJson;
    private final byte[] userSignature;
    
    public PasskeyAuthenticator(byte[] authenticatorData, String clientDataJson, byte[] userSignature) {
        this.authenticatorData = Arrays.copyOf(authenticatorData, authenticatorData.length);
        this.clientDataJson = Objects.requireNonNull(clientDataJson);
        this.userSignature = Arrays.copyOf(userSignature, userSignature.length);
    }
    
    public byte[] getAuthenticatorData() {
        return Arrays.copyOf(authenticatorData, authenticatorData.length);
    }
    
    public String getClientDataJson() {
        return clientDataJson;
    }
    
    public byte[] getUserSignature() {
        return Arrays.copyOf(userSignature, userSignature.length);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PasskeyAuthenticator that = (PasskeyAuthenticator) obj;
        return Arrays.equals(authenticatorData, that.authenticatorData) &&
               Objects.equals(clientDataJson, that.clientDataJson) &&
               Arrays.equals(userSignature, that.userSignature);
    }
    
    @Override
    public int hashCode() {
        int result = Objects.hash(clientDataJson);
        result = 31 * result + Arrays.hashCode(authenticatorData);
        result = 31 * result + Arrays.hashCode(userSignature);
        return result;
    }
    
    @Override
    public String toString() {
        return "PasskeyAuthenticator{" +
               "authenticatorData=" + Arrays.toString(authenticatorData) +
               ", clientDataJson='" + clientDataJson + '\'' +
               ", userSignature=" + Arrays.toString(userSignature) +
               '}';
    }
} 