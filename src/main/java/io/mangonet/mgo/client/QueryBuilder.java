package io.mangonet.mgo.client;

import io.mangonet.mgo.model.Request;
import io.mangonet.mgo.model.coin.Coin;
import io.mangonet.mgo.model.coin.PageForCoinAndString;
import io.mangonet.mgo.model.filter.MgoObjectDataFilter;
import io.mangonet.mgo.model.move.MgoMoveNormalizedFunction;
import io.mangonet.mgo.model.move.kind.MoveValue;
import io.mangonet.mgo.model.move.kind.data.MoveObject;
import io.mangonet.mgo.model.move.kind.struct.MoveStructMap;
import io.mangonet.mgo.model.object.*;
import io.mangonet.mgo.model.object.*;
import io.mangonet.mgo.protocol.MgoClient;
import io.mangonet.mgo.protocol.exceptions.RpcRequestFailedException;
import io.mangonet.mgo.protocol.http.request.GetCoins;
import io.mangonet.mgo.protocol.http.request.GetNormalizedMoveFunction;
import io.mangonet.mgo.protocol.http.request.GetObject;
import io.mangonet.mgo.protocol.http.request.GetOwnedObjects;
import io.mangonet.mgo.protocol.http.response.PageForCoinAndStringWrapper;
import io.mangonet.mgo.protocol.http.response.PageForMgoObjectResponseAndObjectIdWrapper;
import io.mangonet.mgo.protocol.http.response.MgoMoveNormalizedFunctionWrapper;
import io.mangonet.mgo.protocol.http.response.MgoObjectResponseWrapper;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class QueryBuilder {

    private static final Map<String, MgoMoveNormalizedFunction> MOVE_FUNCTION_CACHE = new ConcurrentHashMap<>();

    private static final String BALANCE = "balance";

    /**
     * Get list of objectData for address
     * @param mgoClient
     * @param address
     * @param types
     * @return
     */
    public static List<ObjectData> getOwnerObjectData(MgoClient mgoClient, String address, List<String> types) {
        List<MgoObjectDataFilter> filters = types.stream()
                .map(type -> {
                    // Set structTypeFilter field
                    return new MgoObjectDataFilter()
                            .setStructTypeFilter(type);
                })
                .collect(Collectors.toList());

        MgoObjectDataFilter mgoObjectDataFilter = new MgoObjectDataFilter();
        mgoObjectDataFilter.setMatchAny(filters);

        ObjectResponseQuery objectResponseQuery = new ObjectResponseQuery();
        objectResponseQuery.setFilter(mgoObjectDataFilter);
        objectResponseQuery.setOptions(ObjectDataOptions.ownerAndTypeAndContentTrue());

        // 1. Get tableId
        GetOwnedObjects data = new GetOwnedObjects();
        data.setAddress(address);
        data.setQuery(objectResponseQuery);
        data.setCursor(null);
        data.setLimit(null);

        Request<?, PageForMgoObjectResponseAndObjectIdWrapper> request = mgoClient.getOwnedObjects(data);
        PageForMgoObjectResponseAndObjectIdWrapper response;
        try {
            response = request.send();
        } catch (IOException e) {
            throw new RpcRequestFailedException("Get objectData failed! address = " + address, e);
        }
        PageForMgoObjectResponseAndObjectId result = response.getResult();
        List<MgoObjectResponse> resultData = result.getData();
        if (resultData == null || resultData.isEmpty()) {
            return null;
        }
        return resultData.stream()
                .map(MgoObjectResponse::getData)
                .collect(Collectors.toList());
    }

    /**
     * Get objectData for address
     * @param mgoClient
     * @param address
     * @param type
     * @return
     */
    public static ObjectData getOwnerObjectData(MgoClient mgoClient, String address, String type) {
        MgoObjectDataFilter mgoObjectDataFilter = new MgoObjectDataFilter();
        mgoObjectDataFilter.setStructTypeFilter(type);

        ObjectResponseQuery objectResponseQuery = new ObjectResponseQuery();
        objectResponseQuery.setFilter(mgoObjectDataFilter);
        objectResponseQuery.setOptions(ObjectDataOptions.ownerAndTypeAndContentTrue());

        GetOwnedObjects data = new GetOwnedObjects();
        data.setAddress(address);
        data.setQuery(objectResponseQuery);
        data.setCursor(null);
        data.setLimit(null);

        Request<?, PageForMgoObjectResponseAndObjectIdWrapper> request = mgoClient.getOwnedObjects(data);
        PageForMgoObjectResponseAndObjectIdWrapper response;
        try {
            response = request.send();
        } catch (IOException e) {
            throw new RpcRequestFailedException("Get objectData failed! address = " + address, e);
        }
        PageForMgoObjectResponseAndObjectId result = response.getResult();
        List<MgoObjectResponse> resultData = result.getData();
        if (resultData == null || resultData.isEmpty()) {
            return null;
        }
        return resultData.getFirst().getData();
    }

    /**
     * Get objectData for address token
     * @param mgoClient
     * @param address
     * @param type
     * @return
     */
    public static List<ObjectData> getTokenObjectData(MgoClient mgoClient, String address, String type) {
        MgoObjectDataFilter mgoObjectDataFilter = new MgoObjectDataFilter();
        mgoObjectDataFilter.setStructTypeFilter(type);

        ObjectResponseQuery objectResponseQuery = new ObjectResponseQuery();
        objectResponseQuery.setFilter(mgoObjectDataFilter);
        objectResponseQuery.setOptions(ObjectDataOptions.ownerAndTypeAndContentTrue());

        GetOwnedObjects data = new GetOwnedObjects();
        data.setAddress(address);
        data.setQuery(objectResponseQuery);
        data.setCursor(null);
        data.setLimit(null);

        Request<?, PageForMgoObjectResponseAndObjectIdWrapper> request = mgoClient.getOwnedObjects(data);
        PageForMgoObjectResponseAndObjectIdWrapper response;
        try {
            response = request.send();
        } catch (IOException e) {
            throw new RpcRequestFailedException("Get objectData failed! address = " + address, e);
        }
        PageForMgoObjectResponseAndObjectId result = response.getResult();
        List<MgoObjectResponse> resultData = result.getData();
        if (resultData == null || resultData.isEmpty()) {
            return null;
        }
        return resultData.stream()
                .map(MgoObjectResponse::getData)
                .collect(Collectors.toList());
    }

    /**
     * Get objectData
     * @param mgoClient
     * @param objectId
     * @param options
     * @return
     */
    public static ObjectData getObjectData(MgoClient mgoClient, String objectId, ObjectDataOptions options) {
        // Get object
        GetObject data = new GetObject();
        data.setObjectId(objectId);
        data.setOptions(options);

        Request<?, MgoObjectResponseWrapper> request = mgoClient.getObject(data);
        MgoObjectResponseWrapper response;
        try {
            response = request.send();
        } catch (IOException e) {
            throw new RpcRequestFailedException("Get objectData failed! objectId = " + objectId, e);
        }
        MgoObjectResponse result = response.getResult();
        return result.getData();
    }

    /**
     * Get objectData
     * @param mgoClient
     * @param objectId
     * @return
     */
    public static ObjectData getObjectData(MgoClient mgoClient, String objectId) {
        // Get object
        GetObject data = new GetObject();
        data.setObjectId(objectId);
        data.setOptions(ObjectDataOptions.ownerAndTypeTrue());

        Request<?, MgoObjectResponseWrapper> request = mgoClient.getObject(data);
        MgoObjectResponseWrapper response;
        try {
            response = request.send();
        } catch (IOException e) {
            throw new RpcRequestFailedException("Get objectData failed! objectId = " + objectId, e);
        }
        MgoObjectResponse result = response.getResult();
        return result.getData();
    }

    /**
     * Get coins
     * @param mgoClient
     * @param address
     * @param type
     * @return
     */
    public static List<Coin> getCoins(MgoClient mgoClient, String address, String type) {
        // Get object
        GetCoins data = new GetCoins();
        data.setOwner(address);
        data.setCoinType(type);

        Request<?, PageForCoinAndStringWrapper> request = mgoClient.getCoins(data);
        PageForCoinAndStringWrapper response;
        try {
            response = request.send();
        } catch (IOException e) {
            throw new RpcRequestFailedException("Get coins failed! address = " + address + ", type = " + type, e);
        }
        PageForCoinAndString result = response.getResult();
        return result.getData();
    }

    /**
     * get coins
     * @param mgoClient
     * @param address
     * @param type
     * @param cursor
     * @return
     */
    public static PageForCoinAndString getCoins(MgoClient mgoClient, String address, String type, String cursor) {
        GetCoins data = new GetCoins();
        data.setOwner(address);
        data.setCoinType(type);
        data.setCursor(cursor);

        Request<?, PageForCoinAndStringWrapper> request = mgoClient.getCoins(data);
        PageForCoinAndStringWrapper response;
        try {
            response = request.send();
        } catch (IOException e) {
            throw new RpcRequestFailedException("Get coins failed! address = " + address + ", type = " + type, e);
        }
        return response.getResult();
    }

    /**
     * get max balance from ObjectData
     * @param mgoClient
     * @param address
     * @param type
     * @return
     */
    public static ObjectData getMaxBalanceObjectData(MgoClient mgoClient, String address, String type) {
        List<ObjectData> objectDataList = getTokenObjectData(mgoClient, address, type);
        if (objectDataList == null || objectDataList.isEmpty()) {
            return null;
        }

        AtomicReference<BigInteger> max = new AtomicReference<>(BigInteger.ZERO);
        AtomicReference<ObjectData> maxObject = new AtomicReference<>();
        objectDataList.forEach(objectData -> {
            // find ObjectData which has max balance
            BigInteger balance = getBalance(objectData);
            BigInteger maxBalance = max.get() == null ? BigInteger.ZERO : max.get();
            if (balance.compareTo(maxBalance) > 0) {
                max.set(balance);
                maxObject.set(objectData);
            }
        });
        return maxObject.get();
    }

    /**
     * get balance from objectData
     * @param objectData
     * @return
     */
    public static BigInteger getBalance(ObjectData objectData) {
        if (objectData == null ||
                objectData.getContent() == null ||
                !(objectData.getContent() instanceof MoveObject moveObject)) {
            return BigInteger.ZERO;
        }
        if (moveObject.getFields() instanceof MoveStructMap moveStructMap) {
            MoveValue balanceValue = moveStructMap.getValues().get(BALANCE);

            if (balanceValue != null && balanceValue.getValue() != null) {
                try {
                    String balanceStr = balanceValue.getValue().toString();
                    return new BigInteger(balanceStr);
                } catch (NumberFormatException e) {
                    return BigInteger.ZERO;
                }
            }
        }
        return BigInteger.ZERO;
    }

    /**
     * get move function
     * @param mgoClient
     * @param packageId
     * @param module
     * @param function
     * @return
     */
    public static MgoMoveNormalizedFunction getMoveFunction(MgoClient mgoClient, String packageId, String module, String function) {
        String key = packageId + module + function;
        MgoMoveNormalizedFunction mgoMoveNormalizedFunction = MOVE_FUNCTION_CACHE.get(key);
        if (mgoMoveNormalizedFunction != null) {
            return mgoMoveNormalizedFunction;
        }
        GetNormalizedMoveFunction data = new GetNormalizedMoveFunction();
        data.setObjectId(packageId);
        data.setModuleName(module);
        data.setFunctionName(function);
        Request<?, MgoMoveNormalizedFunctionWrapper> request = mgoClient.getNormalizedMoveFunction(data);
        MgoMoveNormalizedFunctionWrapper response = null;
        try {
            response = request.send();
        } catch (IOException e) {
            throw new RpcRequestFailedException("Failed to get normalized move function", e);
        }
        MgoMoveNormalizedFunction result = response.getResult();
        MOVE_FUNCTION_CACHE.put(key, result);
        return result;
    }

}
