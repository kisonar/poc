package com.mmigdal.mossad.poc.sftp;

import java.util.ArrayList;
import java.util.List;
import org.apache.sshd.common.NamedFactory;
import org.apache.sshd.SshServer;

import org.apache.sshd.server.Command;
import org.apache.sshd.server.auth.UserAuthNone;
import org.apache.sshd.server.command.ScpCommandFactory;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.sftp.SftpSubsystem;
import org.apache.sshd.server.UserAuth;


public class MossadSftpServer {

    private SshServer sshd;

    public MossadSftpServer(){

    }

    public void start(){
        sshd = SshServer.setUpDefaultServer();
        sshd.setPort(22);
        sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider("hostkey.ser"));

        List<NamedFactory<UserAuth>> userAuthFactories = new ArrayList<NamedFactory<UserAuth>>();
        userAuthFactories.add(new UserAuthNone.Factory());
        sshd.setUserAuthFactories(userAuthFactories);

        sshd.setCommandFactory(new ScpCommandFactory());

        List<NamedFactory<Command>> namedFactoryList = new ArrayList<NamedFactory<Command>>();
        namedFactoryList.add(new SftpSubsystem.Factory());
        sshd.setSubsystemFactories(namedFactoryList);

        try {
            sshd.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
