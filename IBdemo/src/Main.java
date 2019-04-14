/*
https://stackoverflow.com/questions/43083416/java-interactive-brokers-api-historical-data-and-tick
        brian was able to get a working example of this on 9.72 and with slight modification it can probably run in 9.73. The question was simple: look at current price and compare to previous day session high and low. If price is above it prints out buy, and if below low of historical previous session it would print out sell. At first I struggled due to my weak knowledge of Java, but brian displayed in his answer selected as best, how to use the current and historical prices for comparison. Very rudimentary, but important.

        This is the current code that is supposed to take current price and compare it to prior session and if above high give a buy print out and if below low give a sell print out. In the 9.72 API this code did nothing because it did not properly connect data types.
*/

package apidemo;

import java.io.IOException;
import java.util.Set;
import com.ib.client.TickType;
import com.ib.client.CommissionReport;
import com.ib.client.Contract;
import com.ib.client.ContractDetails;
import com.ib.client.DeltaNeutralContract;
import com.ib.client.EClientSocket;
import com.ib.client.EJavaSignal;
import com.ib.client.EReader;
import com.ib.client.EWrapper;
import com.ib.client.Execution;
import com.ib.client.Order;
import com.ib.client.OrderState;
import com.ib.client.SoftDollarTier;
import com.ib.client.TagValue;
import java.util.Vector;
import com.ib.client.*;
import com.ib.contracts.StkContract;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

class xxx implements EWrapper {
    EJavaSignal m_signal = new EJavaSignal();
    EClientSocket m_s = new EClientSocket(this, m_signal);
    // Keep track of the next ID
    private int nextOrderID = 0;
    // The IB API Client Socket object
    private EClientSocket client = null;
    private double high = Double.MAX_VALUE;
    private double low = -Double.MAX_VALUE;
    public static void main(String[] args) {
        new xxx().run();

    }






    private void run() {
        m_s.eConnect("localhost", 7497, 0);

        final EReader reader = new EReader(m_s, m_signal);

        reader.start();

        new Thread() {
            public void run() {
                while (m_s.isConnected()) {
                    m_signal.waitForSignal();
                    try {
                        javax.swing.SwingUtilities
                                .invokeAndWait(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            reader.processMsgs();
                                        } catch (IOException e) {
                                            error(e);
                                        }
                                    }
                                });
                    } catch (Exception e) {
                        error(e);
                    }
                }
            }
        }.start();
        Contract contract = new Contract ();
        Order order = new Order();
        m_s.reqMarketDataType(3);
        //contract.m_localSymbol = "ESM7";

    }


    @Override public void nextValidId(int orderId) {
        System.out.println("id "+orderId);
        nextOrderID = orderId;
        Contract c = new Contract ();
        //contract.m_localSymbol = "ESM7";
        c.m_symbol = "EUR";
        c.m_exchange = "IDEALPRO";

        c.m_secType = "CASH";
        c.m_currency = "USD";
        m_s.reqHistoricalData(1, c,
                LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)+ " 16:00:00",
                "2 D", "1 day", "MIDPOINT", 1, 1, null);
        m_s.reqMktData(1, c, "", false, null);

    }

    @Override
    public void contractDetails(int i, ContractDetails contractDetails) {

    }

    @Override
    public void bondContractDetails(int i, ContractDetails contractDetails) {

    }

    @Override
    public void contractDetailsEnd(int i) {

    }

    @Override
    public void execDetails(int i, Contract contract, Execution execution) {

    }

    @Override
    public void execDetailsEnd(int i) {

    }

    @Override
    public void updateMktDepth(int i, int i1, int i2, int i3, double v, int i4) {

    }

    @Override
    public void updateMktDepthL2(int i, int i1, String s, int i2, int i3, double v, int i4) {

    }

    @Override
    public void updateNewsBulletin(int i, int i1, String s, String s1) {

    }

    @Override
    public void managedAccounts(String s) {

    }

    @Override
    public void receiveFA(int i, String s) {

    }

    public void historicalData(int reqId, String date, double open, double high, double low, double close, int volume, int count, double WAP, boolean hasGaps) {
        System.out.println("date xxx"+date);
        this.high = high;
        this.low = low;
        System.out.println(high);
        System.out.println(low);
        System.out.println("xxx");
    }

    @Override
    public void scannerParameters(String s) {

    }

    @Override
    public void scannerData(int i, int i1, ContractDetails contractDetails, String s, String s1, String s2, String s3) {

    }

    @Override
    public void scannerDataEnd(int i) {

    }

    @Override
    public void realtimeBar(int i, long l, double v, double v1, double v2, double v3, long l1, double v4, int i1) {

    }

    @Override
    public void currentTime(long l) {

    }

    @Override
    public void fundamentalData(int i, String s) {

    }

    @Override
    public void deltaNeutralValidation(int i, DeltaNeutralContract deltaNeutralContract) {

    }

    @Override
    public void tickSnapshotEnd(int i) {

    }

    @Override
    public void marketDataType(int i, int i1) {

    }

    @Override
    public void commissionReport(CommissionReport commissionReport) {

    }

    @Override
    public void position(String s, Contract contract, double v, double v1) {

    }

    @Override
    public void positionEnd() {

    }

    @Override
    public void accountSummary(int i, String s, String s1, String s2, String s3) {

    }

    @Override
    public void accountSummaryEnd(int i) {

    }

    @Override
    public void verifyMessageAPI(String s) {

    }

    @Override
    public void verifyCompleted(boolean b, String s) {

    }

    @Override
    public void verifyAndAuthMessageAPI(String s, String s1) {

    }

    @Override
    public void verifyAndAuthCompleted(boolean b, String s) {

    }

    @Override
    public void displayGroupList(int i, String s) {

    }

    @Override
    public void displayGroupUpdated(int i, String s) {

    }

    @Override
    public void error(Exception e) {

    }

    @Override
    public void error(String s) {

    }

    @Override
    public void error(int i, int i1, String s) {

    }

    public void tickPrice(int orderId, int field, double price,
                          int canAutoExecute)
    {
        System.out.println(high);
        System.out.println(low);
        System.out.println("idx: "+orderId + " " + TickType.getField(field) + " pricex: "+price);
        if (field == TickType.LAST.index()){
            if (price > high) {
                System.out.println("buy");
            }
            if (price < low){
                System.out.println("sell");
            }
        }
    }

    @Override
    public void tickSize(int i, int i1, int i2) {

    }

    @Override
    public void tickOptionComputation(int i, int i1, double v, double v1, double v2, double v3, double v4, double v5, double v6, double v7) {

    }

    @Override
    public void tickGeneric(int i, int i1, double v) {

    }

    @Override
    public void tickString(int i, int i1, String s) {

    }

    @Override
    public void tickEFP(int i, int i1, double v, String s, double v1, int i2, String s1, double v2, double v3) {

    }

    @Override
    public void orderStatus(int i, String s, double v, double v1, double v2, int i1, int i2, double v3, int i3, String s1) {

    }

    @Override
    public void openOrder(int i, Contract contract, Order order, OrderState orderState) {

    }

    @Override
    public void openOrderEnd() {

    }

    @Override
    public void updateAccountValue(String s, String s1, String s2, String s3) {

    }

    @Override
    public void updatePortfolio(Contract contract, double v, double v1, double v2, double v3, double v4, double v5, String s) {

    }

    @Override
    public void updateAccountTime(String s) {

    }

    @Override
    public void accountDownloadEnd(String s) {

    }

    @Override public void connectionClosed() {
    }

    @Override
    public void connectAck() {

    }

    @Override
    public void positionMulti(int i, String s, String s1, Contract contract, double v, double v1) {

    }

    @Override
    public void positionMultiEnd(int i) {

    }

    @Override
    public void accountUpdateMulti(int i, String s, String s1, String s2, String s3, String s4) {

    }

    @Override
    public void accountUpdateMultiEnd(int i) {

    }

    @Override
    public void securityDefinitionOptionalParameter(int i, String s, int i1, String s1, String s2, Set<String> set, Set<Double> set1) {

    }

    @Override
    public void securityDefinitionOptionalParameterEnd(int i) {

    }

    @Override
    public void softDollarTiers(int i, SoftDollarTier[] softDollarTiers) {

    }


}
public class Main {

    public static void main(String[] args) {
       // RealTimeData ib_connector = new RealTimeData();
        System.out.println("IB Connect");
    }
}
