package io.mangonet.mgo.bcs.types.signature;

import java.util.Arrays;

public abstract class PublicKey {
    
    /**
     * ED25519 public key
     */
    public static class ED25519 extends PublicKey {
        private final byte[] publicKey;
        
        public ED25519(byte[] publicKey) {
            if (publicKey.length != 32) {
                throw new IllegalArgumentException("ED25519 public key must be 32 bytes");
            }
            this.publicKey = Arrays.copyOf(publicKey, publicKey.length);
        }
        
        public byte[] getPublicKey() {
            return Arrays.copyOf(publicKey, publicKey.length);
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            ED25519 ed25519 = (ED25519) obj;
            return Arrays.equals(publicKey, ed25519.publicKey);
        }
        
        @Override
        public int hashCode() {
            return Arrays.hashCode(publicKey);
        }
        
        @Override
        public String toString() {
            return "ED25519{publicKey=" + Arrays.toString(publicKey) + "}";
        }
    }
    
    /**
     * Secp256k1 public key
     */
    public static class Secp256k1 extends PublicKey {
        private final byte[] publicKey;
        
        public Secp256k1(byte[] publicKey) {
            if (publicKey.length != 33) {
                throw new IllegalArgumentException("Secp256k1 public key must be 33 bytes");
            }
            this.publicKey = Arrays.copyOf(publicKey, publicKey.length);
        }
        
        public byte[] getPublicKey() {
            return Arrays.copyOf(publicKey, publicKey.length);
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Secp256k1 secp256k1 = (Secp256k1) obj;
            return Arrays.equals(publicKey, secp256k1.publicKey);
        }
        
        @Override
        public int hashCode() {
            return Arrays.hashCode(publicKey);
        }
        
        @Override
        public String toString() {
            return "Secp256k1{publicKey=" + Arrays.toString(publicKey) + "}";
        }
    }
    
    /**
     * Secp256r1 public key
     */
    public static class Secp256r1 extends PublicKey {
        private final byte[] publicKey;
        
        public Secp256r1(byte[] publicKey) {
            if (publicKey.length != 33) {
                throw new IllegalArgumentException("Secp256r1 public key must be 33 bytes");
            }
            this.publicKey = Arrays.copyOf(publicKey, publicKey.length);
        }
        
        public byte[] getPublicKey() {
            return Arrays.copyOf(publicKey, publicKey.length);
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Secp256r1 secp256r1 = (Secp256r1) obj;
            return Arrays.equals(publicKey, secp256r1.publicKey);
        }
        
        @Override
        public int hashCode() {
            return Arrays.hashCode(publicKey);
        }
        
        @Override
        public String toString() {
            return "Secp256r1{publicKey=" + Arrays.toString(publicKey) + "}";
        }
    }
    
    /**
     * ZkLogin public key
     */
    public static class ZkLogin extends PublicKey {
        private final byte[] publicKey;
        
        public ZkLogin(byte[] publicKey) {
            this.publicKey = Arrays.copyOf(publicKey, publicKey.length);
        }
        
        public byte[] getPublicKey() {
            return Arrays.copyOf(publicKey, publicKey.length);
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            ZkLogin zkLogin = (ZkLogin) obj;
            return Arrays.equals(publicKey, zkLogin.publicKey);
        }
        
        @Override
        public int hashCode() {
            return Arrays.hashCode(publicKey);
        }
        
        @Override
        public String toString() {
            return "ZkLogin{publicKey=" + Arrays.toString(publicKey) + "}";
        }
    }
} 