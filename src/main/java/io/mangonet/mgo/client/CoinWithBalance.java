package io.mangonet.mgo.client;


import io.mangonet.mgo.bcs.types.gas.MgoObjectRef;
import io.mangonet.mgo.model.coin.Coin;
import io.mangonet.mgo.model.coin.PageForCoinAndString;
import io.mangonet.mgo.protocol.MgoClient;
import io.mangonet.mgo.protocol.constant.MgoSystem;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class CoinWithBalance {

    /**
     * Get the required amount of gas
     * @param mgoClient
     * @param owner
     * @param requiredBalance
     * @return
     */
    public static List<MgoObjectRef> getMergeGas(MgoClient mgoClient, String owner, BigInteger requiredBalance) {
        List<Coin> selectedCoins = getCoinsOfType(mgoClient, owner, MgoSystem.MGO_TYPE, requiredBalance);
        List<MgoObjectRef> gasList = new ArrayList<>(selectedCoins.size());
        for (Coin coin : selectedCoins) {
            gasList.add(new MgoObjectRef(coin.getCoinObjectId(), coin.getVersion(), coin.getDigest()));
        }
        return gasList;
    }

    /**
     * Get the target amount of coins
     * @param mgoClient
     * @param owner
     * @param type
     * @param requiredBalance
     * @return
     */
    public static List<Coin> getCoinsOfType(MgoClient mgoClient, String owner, String type, BigInteger requiredBalance) {
        List<Coin> selectedCoins = new ArrayList<>();
        BigInteger remaining = requiredBalance;
        String cursor = null;

        while (true) {
            PageForCoinAndString response = QueryBuilder.getCoins(mgoClient, owner, type, cursor);
            List<Coin> coinList = response.getData();
            for (Coin coin : coinList) {
                selectedCoins.add(coin);
                remaining = remaining.subtract(coin.getBalance());
                if (remaining.compareTo(BigInteger.ZERO) <= 0) {
                    return selectedCoins;
                }
            }
            if (!response.getHasNextPage()) break;
            cursor = response.getNextCursor();
        }

        throw new IllegalStateException("Not enough coins of type " + type + " to satisfy requested balance");
    }
}
