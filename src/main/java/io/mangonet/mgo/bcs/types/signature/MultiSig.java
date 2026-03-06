package io.mangonet.mgo.bcs.types.signature;

import java.util.List;
import java.util.Objects;

public class MultiSig {
    
    private final List<CompressedSignature> sigs;
    private final int bitmap;
    private final MultiSigPublicKey multisigPk;
    
    public MultiSig(List<CompressedSignature> sigs, int bitmap, MultiSigPublicKey multisigPk) {
        this.sigs = Objects.requireNonNull(sigs);
        this.bitmap = bitmap;
        this.multisigPk = Objects.requireNonNull(multisigPk);
    }
    
    public List<CompressedSignature> getSigs() {
        return sigs;
    }
    
    public int getBitmap() {
        return bitmap;
    }
    
    public MultiSigPublicKey getMultisigPk() {
        return multisigPk;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MultiSig multiSig = (MultiSig) obj;
        return bitmap == multiSig.bitmap &&
               Objects.equals(sigs, multiSig.sigs) &&
               Objects.equals(multisigPk, multiSig.multisigPk);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(sigs, bitmap, multisigPk);
    }
    
    @Override
    public String toString() {
        return "MultiSig{" +
               "sigs=" + sigs +
               ", bitmap=" + bitmap +
               ", multisigPk=" + multisigPk +
               '}';
    }
} 