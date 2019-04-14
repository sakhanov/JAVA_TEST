// https://holowczak.com/ib-api-java-realtime/14/

// Import Java utilities and Interactive Brokers API
import com.ib.client.*;
//import com.ib.client.AnyWrapperMsgGenerator;
import com.ib.client.CommissionReport;
import com.ib.client.Contract;
import com.ib.client.ContractDetails;
import com.ib.client.EClientSocket;
import com.ib.client.EWrapper;
import com.ib.client.EWrapperMsgGenerator;
import com.ib.client.Execution;
import com.ib.client.Order;
import com.ib.client.OrderState;
//import com.ib.client.UnderComp;
import com.ib.client.Util;
import com.ib.client.ComboLeg;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import java.io.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.lang.Thread;
import java.util.Set;
import java.util.Vector;

import static java.time.LocalDate.*;

//import  com.ib.*;
// import com.ib.client.UnderComp;   // Comment out for API version 9.72
// Add the following for API version 9.72

// look up history data stock
class ibHistoryData implements  EWrapper{
    EJavaSignal m_signal = new EJavaSignal();
    EClientSocket m_s = new EClientSocket(this, m_signal);
    //Keep track ID
    private  int nextOrderId = 0;
    private EClientSocket client = null;
    private double high = Double.MAX_VALUE;
    private double low = - Double.MAX_VALUE;
    public void run(){
        System.out.println("ibHistorial");
        m_s.eConnect("localhost", 7497, 0);
        final EReader reader = new EReader(m_s, m_signal);
        new Thread(){
            public void run(){
                while(m_s.isConnected()){
                    m_signal.waitForSignal();
                    try{
                        javax.swing.SwingUtilities
                                .invokeAndWait(new Runnable() {
                                    @Override
                                    public void run() {
                                        try{
                                            reader.processMsgs();
                                        }catch (IOException e){
                                            error(e);
                                        }
                                    }
                                });
                    }catch (Exception e){
                        error(e);
                    }
                }
            }
        }.start();
        Contract contract = new Contract();
        Order order = new Order();
        m_s.reqMarketDataType(3);
    }

    @Override
    public void tickPrice(int orderId, int field, double price, int canAutoExecute) {
        System.out.println(high);
        System.out.println(low);
        System.out.println("idx: "+orderId + " " + TickType.getField(field) + " pricex: "+price);
        if (field == TickType.LAST.index()){
            if (price > high){
                System.out.println("buy");
            }
            if (price < low){
                System.out.println("SELL");
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

    @Override
    public void nextValidId(int orderId) {
        System.out.println("ID  " + orderId);
        nextOrderId = orderId;
        Contract c = new Contract();
        c.symbol("AAPL");
        c.exchange("SMART");
        c.secType("STK");
        c.currency("USD");
        System.out.println(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE) + " 16:00:00");
        m_s.reqHistoricalData(1, c,
                LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE) + " 16:00:00",
                "2 D",  "1 day", "MIDPOINT", 1, 1, null);
        m_s.reqMktData(1,c,"",false, null);

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

    @Override
    public void historicalData(int regId, String date, double open, double high, double low, double Close, int volume, int count, double WAP, boolean hasGAP) {
        System.out.println("Date xxx " + date);
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

    @Override
    public void connectionClosed() {

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


class RealTimeData implements EWrapper
{
    // Add for API 9.72 and newer
    private EJavaSignal m_signal = new EJavaSignal();
    private EReader m_reader;

    // Keep track of the next ID
    private int nextOrderID = 0;
    // The IB API Client Socket object
    private EClientSocket client = null;
    // Keep track of prices for Moving Average
    private double priceTotal;
    private int numberOfPrices;
    void LookHistorialData()  {
        System.out.println("Look Historial data");
        Contract contract = new Contract();
        contract.symbol("AAPL");
        contract.secType("STK");
        contract.currency("USD");
        //In the API side, NASDAQ is always defined as ISLAND
        contract.exchange("SMART");

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -6);
        SimpleDateFormat form = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        String formatted = form.format(cal.getTime());
        //TagValue  list ;
        //List<TagValue> AAPLConIDTag = Collections.singletonList(new TagValue("underConID", "265598"));
        client.reqHistoricalData(4001,contract, now().format(DateTimeFormatter.BASIC_ISO_DATE)+ " 16:00:00",
                "2 D", "1 day", "TRADES", 1, 1, null);
        //client.reqHistoricalData(4001, ContractSamples.EurGbpFx(), formatted, "1 M", "1 day", "MIDPOINT", 1, 1, false, null);
        //client.reqHistoricalData(4002, ContractSamples.EuropeanStock(), formatted, "10 D", "1 min", "TRADES", 1, 1, false, null);
        //Thread.sleep(2000);
        /*** Canceling historical data requests ***/
        client.cancelHistoricalData(4001);
        //client.cancelHistoricalData(4002);

    }
    void LookContract(){
        System.out.println("Look contract");
        Contract contract = new Contract();
        contract.symbol("AAPL");
        contract.secType("STK");
        contract.currency("USD");
        //In the API side, NASDAQ is always defined as ISLAND
        contract.exchange("SMART");

    }
    public RealTimeData ()
    {
        // Initialize to 0
        priceTotal = 0.0;
        numberOfPrices = 0;
        // Create a new EClientSocket object version 9.71
        // client = new EClientSocket (this);
        client = new EClientSocket( this, m_signal);
        // Connect to the TWS or IB Gateway application
        // Leave null for localhost
        // Port Number (should match TWS/IB Gateway configuration
        client.eConnect (null, 7497, 0);
        LookContract();
        LookHistorialData();
        // Pause here for connection to complete
        try
        {
            // Thread.sleep (1000);
            while (! (client.isConnected()));
            // Can also try: while (client.NextOrderId <= 0);
        } catch (Exception e)
        {
        };

        // API Version 9.72 and later Launch EReader Thread
        m_reader = new EReader(client, m_signal);
        m_reader.start();
        new Thread() {
            public void run() {
                processMessages();
            }
        }.start();

        // Create a new contract
        System.out.println("Test equty");
        Contract contract = new Contract ();
        contract.symbol("ES");
        //contract.expiry("20160318");
        System.out.println("Find equty");
        //contract.lastTradeDateOrContractMonth("20190418");
     //   contract.exchange("GLOBEX");
        contract.exchange("SMART");
      //  contract.secType("FUT");
        contract.secType("STK");
        contract.currency("USD");
        // Create a TagValue list
        Vector<TagValue> mktDataOptions = new Vector<TagValue>();
        // Make a call to start off data retrieval
        /*** Switch to live (1) frozen (2) delayed (3) or delayed frozen (4)***/
        client.reqMarketDataType(3);
        client.reqMktData(0, contract, null, false, mktDataOptions);
        // For API Version 9.73 and higher, add one more parameter: regulatory snapshot
        // client.reqMktData(0, contract, null, false, false, mktDataOptions);

        // At this point our call is done and any market data events
        // will be returned via the tickPrice method

    } // end RealTimeData

    private void processMessages()
    {
        while(true)
        {
            try {
                m_reader.processMsgs();
            } catch (Exception e) {
                error(e);
            }
        } // end while
    } // end processMessages()


    // New for API version 9.72.14
    public void securityDefinitionOptionalParameter(int reqId, String exchange, int underlyingConId, String tradingClass,
                                                    String multiplier, Set expirations, Set strikes) {
        // TODO Auto-generated method stub
    }
    // New for API version 9.72.14
    public void securityDefinitionOptionalParameterEnd(int reqId) {
        // TODO Auto-generated method stub
    }

    @Override
    public void softDollarTiers(int i, SoftDollarTier[] softDollarTiers) {

    }

    // New for API version 9.72.14
    public void accountUpdateMulti( int reqId, String account, String modelCode, String key, String value, String currency) {
        // TODO Auto-generated method stub
    }
    // New for API version 9.72.14
    public void accountUpdateMultiEnd( int reqId) {
        // TODO Auto-generated method stub
    }
    // New for API version 9.72.14
    public void positionMulti( int reqId, String account, String modelCode, Contract contract, double pos, double avgCost) {
        // TODO Auto-generated method stub
    }
    // New for API version 9.72.14
    public void positionMultiEnd( int reqId) {
        // TODO Auto-generated method stub
    }


    public void bondContractDetails(int reqId, ContractDetails contractDetails)
    {
    }

    public void contractDetails(int reqId, ContractDetails contractDetails)
    {
    }

    public void contractDetailsEnd(int reqId)
    {
    }

    public void fundamentalData(int reqId, String data)
    {
    }

    public void bondContractDetails(ContractDetails contractDetails)
    {
    }

    public void contractDetails(ContractDetails contractDetails)
    {
    }

    public void currentTime(long time)
    {
    }

    public void displayGroupList(int requestId, String contraftInfo)
    {
    }


    public void displayGroupUpdated(int requestId, String contractInfo)
    {
    }

    // Add for API version 9.72
    public void verifyAndAuthCompleted(boolean isSuccessful, String errorText)
    {
    }

    // Add for API version 9.72
    public void verifyAndAuthMessageAPI(String apiData, String xyzChallange)
    {
    }

    public void verifyCompleted(boolean completed, String contractInfo)
    {
    }
    public void verifyMessageAPI(String message)
    {
    }

    public void execDetails(int orderId, Contract contract, Execution execution)
    {
    }

    public void execDetailsEnd(int reqId)
    {
    }

    public void historicalData(int reqId, String date, double open,
                               double high, double low, double close, int volume, int count,
                               double WAP, boolean hasGaps)
    {
    }

    public void managedAccounts(String accountsList)
    {
    }

    public void commissionReport(CommissionReport cr)
    {
    }

    // For API Version 9.72 pos is now a double
    public void position(String account, Contract contract, double pos, double avgCost)
    {
    }

    // Below is API version 9.71
    public void position(String account, Contract contract, int pos, double avgCost)
    {
    }

    public void positionEnd()
    {
    }

    public void accountSummary(int reqId, String account, String tag, String value, String currency)
    {
    }

    public void accountSummaryEnd(int reqId)
    {
    }

    public void accountDownloadEnd(String accountName)
    {
    }

    public void openOrder(int orderId, Contract contract, Order order,
                          OrderState orderState)
    {
    }

    public void openOrderEnd()
    {
    }


    // For API Version 9.72
    public void orderStatus(int orderId, String status, double filled,
                            double remaining, double avgFillPrice, int permId, int parentId,
                            double lastFillPrice, int clientId, String whyHeld)
    {
    }

    // For API Version 9.71
    public void orderStatus(int orderId, String status, int filled,
                            int remaining, double avgFillPrice, int permId, int parentId,
                            double lastFillPrice, int clientId, String whyHeld)
    {
    }

    public void receiveFA(int faDataType, String xml)
    {
    }

    public void scannerData(int reqId, int rank,
                            ContractDetails contractDetails, String distance, String benchmark,
                            String projection, String legsStr)
    {
    }

    public void scannerDataEnd(int reqId)
    {
    }

    public void scannerParameters(String xml)
    {
    }

    public void tickEFP(int symbolId, int tickType, double basisPoints,
                        String formattedBasisPoints, double impliedFuture, int holdDays,
                        String futureExpiry, double dividendImpact, double dividendsToExpiry)
    {
    }

    public void tickGeneric(int symbolId, int tickType, double value)
    {
    }

    public void tickOptionComputation( int tickerId, int field,
                                       double impliedVol, double delta, double optPrice,
                                       double pvDividend, double gamma, double vega,
                                       double theta, double undPrice)
    {
    }


    //     public void deltaNeutralValidation(int reqId, UnderComp underComp)
    public void deltaNeutralValidation(int reqId, DeltaNeutralContract underComp)
    {
    }


    public void updateAccountTime(String timeStamp)
    {
    }

    public void updateAccountValue(String key, String value, String currency,
                                   String accountName)
    {
    }

    public void updateMktDepth(int symbolId, int position, int operation,
                               int side, double price, int size)
    {
    }

    public void updateMktDepthL2(int symbolId, int position,
                                 String marketMaker, int operation, int side, double price, int size)
    {
    }

    public void updateNewsBulletin(int msgId, int msgType, String message,
                                   String origExchange)
    {
    }

    // For API Version 9.72
    public void updatePortfolio(Contract contract, double position,
                                double marketPrice, double marketValue, double averageCost,
                                double unrealizedPNL, double realizedPNL, String accountName)
    {
    }

    // For API Version 9.71:
    public void updatePortfolio(Contract contract, int position,
                                double marketPrice, double marketValue, double averageCost,
                                double unrealizedPNL, double realizedPNL, String accountName)
    {
    }

    public void marketDataType(int reqId, int marketDataType)
    {
    }

    public void tickSnapshotEnd(int tickerId)
    {
    }

    public void connectionClosed()
    {
    }
    // Add connectAck for API version 9.72
    public void connectAck()
    {
    }


    public void realtimeBar (int reqId, long time, double open, double high,
                             double low, double close, long volume, double wap, int count)
    {
    }


    public void error(Exception e)
    {
// Print out a stack trace for the exception
        e.printStackTrace ();
    }

    public void error(String str)
    {
// Print out the error message
        System.err.println (str);
    }

    public void error(int id, int errorCode, String errorMsg)
    {
// Overloaded error event (from IB) with their own error
// codes and messages
        System.err.println ("error: " + id + "," + errorCode + "," + errorMsg);
    }

    public void nextValidId (int orderId)
    {
// Return the next valid OrderID
        nextOrderID = orderId;
    }


    public void tickPrice(int orderId, int field, double price,
                          int canAutoExecute)
    {
        double movingAverage = 0.0;
        try
        {
            // Print out the current price
            // field will provide the price type:
            // 1 = bid,  2 = ask, 4 = last
            // 6 = high, 7 = low, 9 = close
            if (field == 4)
            {
                numberOfPrices++;
                priceTotal += price;
                movingAverage = priceTotal / numberOfPrices;
                System.out.println("tickPrice: " + orderId + "," + field + "," + price + ", " + movingAverage);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace ();
        }


    }

    public void tickSize (int orderId, int field, int size)
    {
        // field will provide the size type:
        // 0=bid size, 3=ask size, 5=last size, 8=volume
        //System.out.println("tickSize: " + orderId + "," + field + "," + size);
    }

    public void tickString (int orderId, int tickType, String value)
    {
    }


    public static void main (String args[])
    {
        try
        {
            // Create an instance
            // At this time a connection will be made
            // and the request for market data will happen
            RealTimeData myData = new RealTimeData();
        }
        catch (Exception e)
        {
            e.printStackTrace ();
        }
    } // end main

} // end public class RealTimeData

public class Main {

    public static void main(String[] args) {
        //RealTimeData ib_connector = new RealTimeData();
        ibHistoryData ibData = new ibHistoryData();
        ibData.run();
        System.out.println("IB Connect");
    }
}
