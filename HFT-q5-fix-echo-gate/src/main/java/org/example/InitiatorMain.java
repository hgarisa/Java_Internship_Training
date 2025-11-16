package org.example;

import quickfix.*;

public class InitiatorMain
{

    public static void main(String[] args) throws Exception
    {

        SessionSettings settings = new SessionSettings(
                InitiatorMain.class.getResourceAsStream("/initiator.cfg"));

        // This loads the QuickFIX/J session settings from a classpath resource named initiator.cfg.
        // This means the file must be in src/main/resources/initiator.cfg
        // so that it’s on the classpath at runtime.

        // If the file isn’t found, getResourceAsStream(...) returns null , you’ll get an error when QuickFIX/J
        // tries to read it.
        Application app = new InitiatorApp();

        MessageStoreFactory storeFactory = new FileStoreFactory(settings);
        // MessageStoreFactory: tells QuickFIX/J where to persist session state (sequence numbers, outbox, etc.).

        LogFactory logFactory = new FileLogFactory(settings);
        // LogFactory: configures protocol-level logging  (admin/app messages) to files (FileLogPath from the cfg).


        MessageFactory msgFactory = new DefaultMessageFactory();
        // MessageFactory: Creates FIX message objects for the right FIX version (here FIX.4.4 by default).


        Initiator initiator = new SocketInitiator(app, storeFactory, settings, logFactory, msgFactory );

        // Builds a TCP client (the FIX initiator). It will read your cfg (config file) to know where to connect
        // (host/port) , and who it is (SenderCompID) vs counterparty (TargetCompID).

        initiator.start();
        System.out.println("Initiator up. Press Ctrl+C to exit.");
        // Starts the FIX engine: opens sockets, performs Logon (A), starts heartbeats, etc.
        // Once logged on, your  InitiatorApp.onLogon will run and will  begin sending NOS messages periodically.


        Runtime.getRuntime().addShutdownHook(new Thread(initiator::stop));
        //  Registers a shutdown hook , so that when you kill the app (Ctrl+C), QuickFIX/J stops cleanly:
        // sockets closed, store/logs flushed, logout is sent if possible.


        Thread.currentThread().join();

        // Keeps the main thread alive forever (park it) so that the process doesn’t exit.
        // All the real work happens in the background with  QuickFIX/J threads and your scheduler . This line just
        // prevents main from finishing.


        /*
        *
        * Mental model


Settings: from initiator.cfg (who we are, where to connect, heartbeats, stores/logs paths).


App callbacks: your InitiatorApp handles events and sends orders.


Factories: plug in file-based persistence and logging.


SocketInitiator: the engine that manages the FIX TCP session.


Lifecycle: start() → logon → run; Ctrl+C → shutdown hook → stop() → clean exit.


Common gotchas to watch


Ensure initiator.cfg is in resources (src/main/resources/initiator.cfg), otherwise it won’t load.


Ensure the acceptor is running on the host/port you configured (e.g., 127.0.0.1:9880) and that Sender/Target IDs match on both sides.


File paths in the cfg (e.g., target/store, target/log) must be writable by the process.



        *
        * */

    }
}
