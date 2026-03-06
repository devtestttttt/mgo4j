package io.mangonet.mgo;

import io.mangonet.mgo.client.TransactionBuilder;
import io.mangonet.mgo.crypto.MgoKeyPair;
import io.mangonet.mgo.model.Request;
import io.mangonet.mgo.model.coin.Coin;
import io.mangonet.mgo.model.transaction.ExecuteTransactionRequestType;
import io.mangonet.mgo.model.transaction.Transaction;
import io.mangonet.mgo.model.transaction.TransactionBlockBytes;
import io.mangonet.mgo.protocol.MgoClient;
import io.mangonet.mgo.protocol.constant.MgoSystem;
import io.mangonet.mgo.protocol.http.HttpService;
import io.mangonet.mgo.protocol.http.request.GetCoins;
import io.mangonet.mgo.protocol.http.request.UnsafePayAllMgo;
import io.mangonet.mgo.protocol.http.response.MgoTransactionBlockResponseWrapper;
import io.mangonet.mgo.protocol.http.response.TransactionBlockBytesWrapper;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

public class Mgo4jBasicExample {

    private static final MgoClient mgoClient = MgoClient.build(new HttpService("fullnode endpoint"));

    public static void main(String[] args) throws IOException {

        MgoKeyPair mgoKeyPair = MgoKeyPair.decodeMgoPrivateKey("mgopriv key");

        final String receipt = "recipient address";

        UnsafePayAllMgo payAllMgo = new UnsafePayAllMgo();
        payAllMgo.setSigner(mgoKeyPair.address());

        GetCoins getCoins = new GetCoins();
        getCoins.setOwner(mgoKeyPair.address());
        getCoins.setCoinType(MgoSystem.MGO_TYPE);

        List<String> coinObjs = mgoClient.getCoins(getCoins).send().getResult().getData().stream().map(Coin::getCoinObjectId).toList();

        payAllMgo.setInputCoins(coinObjs);
        payAllMgo.setRecipient(receipt);
        payAllMgo.setGasBudget(BigInteger.valueOf(100000000));

        Request<?, TransactionBlockBytesWrapper> request = mgoClient.payAllMgo(payAllMgo);

        TransactionBlockBytes txBytes = request.send().getResult();

        Transaction transaction = TransactionBuilder.buildTransaction(txBytes.getTxBytes(), mgoKeyPair);

        transaction.setRequestType(ExecuteTransactionRequestType.WAIT_FOR_LOCAL_EXECUTION.getType());
        Request<?, MgoTransactionBlockResponseWrapper> tx = mgoClient.executeTransactionBlock(transaction);


        MgoTransactionBlockResponseWrapper result = tx.send();

        System.out.println(result.getResult().toString());
    }

}
