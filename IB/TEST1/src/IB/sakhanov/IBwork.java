package IB.sakhanov;
//https://holowczak.com/ib-api-java-realtime/14/
// Стратегия выстраивания объема продаж в зависимиости от цены актиыа
import com.ib.client.EWrapper;
import com.ib.client.Order;
import com.ib.client.OrderType;
import com.ib.client.Types.Action;
import com.ib.client.Types.VolatilityType;

import java.io.IOException;
import java.util.Set;

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
import  com.ib.client.EWrapper;


// RealTimeData.java
// API Version 9.72 and newer
// Version 1.3
// 20170506
// R. Holowczak

// Import Java utilities and Interactive Brokers API
import java.util.Vector;
import java.util.Set;   // Needed for IB API 9.72.14
import com.ib.client.Contract;
import com.ib.client.ContractDetails;
import com.ib.client.EClientSocket;
import com.ib.client.EWrapper;
import com.ib.client.Execution;
import com.ib.client.Order;
import com.ib.client.OrderState;
import com.ib.client.TagValue;
import com.ib.client.CommissionReport;
// import com.ib.client.UnderComp;   // Comment out for API version 9.72
// Add the following for API version 9.72
import com.ib.client.DeltaNeutralContract;  // Add for API version 9.72
import com.ib.client.EJavaSignal;
import com.ib.client.EReader;
import com.ib.client.EWrapperMsgGenerator;



public class IBwork extends Order  {

    //private  static  IB_Ladder ibLadder; // класс для работы с инетерактив брокерс

        //private static  EClientSocket *client;

        public static void IB_Ladder(){
           // client = new EClientSocket (this);

           // clientId(0);
            //orderId(id);
            //permId(id);

            //account(account);
            //InteractiveBrokersClientInterface ibClient = InteractiveBrokersClient.getInstance("localhost", 7999, 1);
            System.out.println("Подключено");
        }


    public static void main(String[] args) {
	// write your code here
        System.out.println("Подключение к торговому терминалу ID");
        IB_Ladder();
    }
}

