package com.app.impl;

import java.io.IOException;

public class RetryLogic {

    int retryAttempts; // count for re-attempts the operation.
    long TIME_TO_WAIT; // time interval between re-attempts

    RetryLogic(int retryAttempts, long timeToWait){
        this.retryAttempts = retryAttempts;
        this.TIME_TO_WAIT = timeToWait;
    }

    public void retryImpl(RetryImplementation retryImplementation) throws IOException {

        if(shouldRetry()){

            System.out.println("Attempt Remaining is "+ retryAttempts);
            retryAttempts--;

            retryImplementation.run();

            waitBeforeNextRetry();

        } else{

            System.out.println("** IO Exception during creation **");
            throw new IOException();
        }
    }

    public boolean shouldRetry(){
        return retryAttempts > 0;
    }

    public void waitBeforeNextRetry(){
        try{
            Thread.sleep(TIME_TO_WAIT);
        }catch(Exception ex){
            System.out.println("** Exception during sleep thread **" + ex.getLocalizedMessage());
        }
    }
}
