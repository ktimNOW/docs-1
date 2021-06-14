// SearchTxAddresssigtype.java
// requires java-algorand-sdk 1.4.0 or higher (see pom.xml)
package com.algorand.javatest.indexer;

import com.algorand.algosdk.v2.client.common.IndexerClient;
import com.algorand.algosdk.v2.client.common.Response;
import com.algorand.algosdk.v2.client.model.Enums.SigType;
import com.algorand.algosdk.v2.client.common.Client;
import com.algorand.algosdk.v2.client.model.TransactionsResponse;
import org.json.JSONObject;
import com.algorand.algosdk.crypto.Address;

public class SearchTxAddresssigtype {
    public Client indexerInstance = null;
    // utility function to connect to a node
    private Client connectToNetwork(){
        final String INDEXER_API_ADDR = "localhost";
        final int INDEXER_API_PORT = 8980;       
        IndexerClient indexerClient = new IndexerClient(INDEXER_API_ADDR, INDEXER_API_PORT); 
        return indexerClient;
    }

    public static void main(String args[]) throws Exception {
        SearchTxAddresssigtype ex = new SearchTxAddresssigtype();
        IndexerClient indexerClientInstance = (IndexerClient) ex.connectToNetwork();
        Address account = new Address("RBSTLLHK2NJDL3ZH66MKSEX3BE2OWQ43EUM7S7YRVBJ2PRDRCKBSDD3YD4");
        SigType sig_type = SigType.MSIG;    
        Response<TransactionsResponse> response = indexerClientInstance
            .searchForTransactions()
            .address(account)
            .sigType(sig_type).execute();
        if (!response.isSuccessful()) {
            throw new Exception(response.message());
        }         
        JSONObject jsonObj = new JSONObject(response.toString());
        System.out.println("Transaction Info SigType msig: " + jsonObj.toString(2)); // pretty print json
    }
 }