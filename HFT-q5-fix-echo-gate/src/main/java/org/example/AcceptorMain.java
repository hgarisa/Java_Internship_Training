package org.example;

import quickfix.*;

public class AcceptorMain
{
    public static void main(String[] args) throws Exception {

        SessionSettings settings = new SessionSettings(
                AcceptorMain.class.getResourceAsStream("/acceptor.cfg"));
        Application app = new AcceptorApp();
        MessageStoreFactory storeFactory = new FileStoreFactory(settings);
        LogFactory logFactory = new FileLogFactory(settings);
        MessageFactory msgFactory = new DefaultMessageFactory();

        Acceptor acceptor = new SocketAcceptor(app, storeFactory, settings, logFactory, msgFactory);

        acceptor.start();
        System.out.println("Acceptor up. Press Ctrl+C to exit.");
        Runtime.getRuntime().addShutdownHook(new Thread(acceptor::stop));
        Thread.currentThread().join();

    }



}
