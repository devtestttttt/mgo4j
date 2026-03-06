package io.mangonet.mgo.bcs.types.signature;

import java.util.Arrays;

public abstract class CompressedSignature {
    
    /**
     * ED25519 sign
     */
    public static class ED25519 extends CompressedSignature {
        private final byte[] signature;
        
        public ED25519(byte[] signature) {
            if (signature.length != 64) {
                throw new IllegalArgumentException("ED25519 signature must be 64 bytes");
            }
            this.signature = Arrays.copyOf(signature, signature.length);
        }
        
        public byte[] getSignature() {
            return Arrays.copyOf(signature, signature.length);
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            ED25519 ed25519 = (ED25519) obj;
            return Arrays.equals(signature, ed25519.signature);
        }
        
        @Override
        public int hashCode() {
            return Arrays.hashCode(signature);
        }
        
        @Override
        public String toString() {
            return "ED25519{signature=" + Arrays.toString(signature) + "}";
        }
    }
    
    /**
     * Secp256k1 sign
     */
    public static class Secp256k1 extends CompressedSignature {
        private final byte[] signature;
        
        public Secp256k1(byte[] signature) {
            if (signature.length != 64) {
                throw new IllegalArgumentException("Secp256k1 signature must be 64 bytes");
            }
            this.signature = Arrays.copyOf(signature, signature.length);
        }
        
        public byte[] getSignature() {
            return Arrays.copyOf(signature, signature.length);
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Secp256k1 secp256k1 = (Secp256k1) obj;
            return Arrays.equals(signature, secp256k1.signature);
        }
        
        @Override
        public int hashCode() {
            return Arrays.hashCode(signature);
        }
        
        @Override
        public String toString() {
            return "Secp256k1{signature=" + Arrays.toString(signature) + "}";
        }
    }
    
    /**
     * Secp256r1 sign
     */
    public static class Secp256r1 extends CompressedSignature {
        private final byte[] signature;
        
        public Secp256r1(byte[] signature) {
            if (signature.length != 64) {
                throw new IllegalArgumentException("Secp256r1 signature must be 64 bytes");
            }
            this.signature = Arrays.copyOf(signature, signature.length);
        }
        
        public byte[] getSignature() {
            return Arrays.copyOf(signature, signature.length);
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Secp256r1 secp256r1 = (Secp256r1) obj;
            return Arrays.equals(signature, secp256r1.signature);
        }
        
        @Override
        public int hashCode() {
            return Arrays.hashCode(signature);
        }
        
        @Override
        public String toString() {
            return "Secp256r1{signature=" + Arrays.toString(signature) + "}";
        }
    }
    
    /**
     * ZkLogin sign
     */
    public static class ZkLogin extends CompressedSignature {
        private final byte[] signature;
        
        public ZkLogin(byte[] signature) {
            this.signature = Arrays.copyOf(signature, signature.length);
        }
        
        public byte[] getSignature() {
            return Arrays.copyOf(signature, signature.length);
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            ZkLogin zkLogin = (ZkLogin) obj;
            return Arrays.equals(signature, zkLogin.signature);
        }
        
        @Override
        public int hashCode() {
            return Arrays.hashCode(signature);
        }
        
        @Override
        public String toString() {
            return "ZkLogin{signature=" + Arrays.toString(signature) + "}";
        }
    }
} 