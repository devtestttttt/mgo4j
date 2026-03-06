package io.mangonet.mgo.protocol;

import io.mangonet.mgo.model.Request;
import io.mangonet.mgo.model.coin.Balance;
import io.mangonet.mgo.model.coin.PageForCoinAndString;
import io.mangonet.mgo.protocol.http.HttpService;
import io.mangonet.mgo.protocol.http.request.GetBalance;
import io.mangonet.mgo.protocol.http.request.GetCoins;
import io.mangonet.mgo.protocol.http.response.BalanceWrapper;
import io.mangonet.mgo.protocol.http.response.PageForCoinAndStringWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith(IntervalExtension.class)
public class MgoClientTest extends DeserializerTest {

    public static final String TEST_URL = "https://fullnode.devnet.mangonetwork.io:443";

    protected MgoService mgoService;

    protected MgoClient mgoClient;

    protected static final boolean ENABLE_SEND = false;

    @BeforeEach
    protected void setUp() {
        this.mgoService = new HttpService(TEST_URL);
        this.mgoClient = MgoClient.build(mgoService);
    }

    // --------------------- Query API ---------------------

    @Test
    @Tag("mgote")
    void testGetBalance() throws IOException {
        GetBalance data = new GetBalance();
        data.setOwner("0xc70a44bfedc8c36d775cd2775c67845ac2e25694a094fea601966786a708435a");
        data.setCoinType("0x2::mgo::MGO");
        Request<?, BalanceWrapper> request = mgoClient.getBalance(data);
        BalanceWrapper send = request.send();
        Balance result = send.getResult();
        log.info("testGetBalance result: {}", result);

        // verify result type
        assertThat(result)
                .isInstanceOf(Balance.class);
    }

    @Test
    @Tag("mgote")
    void testGetCoins() throws IOException {
        GetCoins data = new GetCoins();
        data.setOwner("0xc70a44bfedc8c36d775cd2775c67845ac2e25694a094fea601966786a708435a");
        data.setCoinType("0x2::mgo::MGO");
        data.setCursor(null);
        data.setLimit(null);
        Request<?, PageForCoinAndStringWrapper> request = mgoClient.getCoins(data);
        PageForCoinAndStringWrapper send = request.send();
        PageForCoinAndString result = send.getResult();
        log.info("testGetCoins result: {}", result);

        // verify result type
        assertThat(result)
                .isInstanceOf(PageForCoinAndString.class);
    }

    // --------------------- todo Extended API ---------------------

}
