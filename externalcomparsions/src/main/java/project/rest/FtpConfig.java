package project.rest;

import org.apache.commons.net.ftp.FTPFile;
import org.joda.time.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.filters.AcceptOnceFileListFilter;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.ftp.inbound.FtpInboundFileSynchronizer;
import org.springframework.integration.ftp.inbound.FtpInboundFileSynchronizingMessageSource;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.messaging.MessageHandler;
import project.file.ExtFile;

import java.io.File;

@Configuration
public class FtpConfig{

    @Autowired
    ExtFile extFile;

    @Value("${ftp.url}")
    private String url;

    @Value("${ftp.remotedirectory}")
    private String remotedirectory;

    @Value("${ftp.localdirectory}")
    private String localdirectory;

    @Value("${ftp.login}")
    private String login;

    @Value("${ftp.password}")
    private String password;

    @Value("${ftp.port}")
    private int port;

    @Bean
    public SessionFactory<FTPFile> ftpSessionFactory() {
        DefaultFtpSessionFactory sf = new DefaultFtpSessionFactory();
        sf.setHost(url);
        sf.setPort(port);
        sf.setUsername(login);
        sf.setPassword(password);
        return new CachingSessionFactory<>(sf);
    }

    @Bean
    public FtpInboundFileSynchronizer ftpInboundFileSynchronizer() {
        FtpInboundFileSynchronizer fileSynchronizer = new FtpInboundFileSynchronizer(ftpSessionFactory());
        fileSynchronizer.setDeleteRemoteFiles(false);
        fileSynchronizer.setRemoteDirectory(remotedirectory);
        return fileSynchronizer;
    }

    @Bean
    @InboundChannelAdapter(channel = "ftpChannel", poller = @Poller(fixedDelay = "5000"))
    public MessageSource<File> ftpMessageSource() {
        FtpInboundFileSynchronizingMessageSource source =
                new FtpInboundFileSynchronizingMessageSource(ftpInboundFileSynchronizer());
        source.setLocalDirectory(new File(localdirectory));
        source.setAutoCreateLocalDirectory(true);
        source.setLocalFilter(new AcceptOnceFileListFilter<>());
        source.setMaxFetchSize(10);
        return source;
    }

    @Bean
    @ServiceActivator(inputChannel = "ftpChannel")
    public MessageHandler handler() {
        return message -> {
            System.out.println(message);
//            double jday=((DateTimeUtils.toJulianDayNumber(System.currentTimeMillis()))-2400002D)/1000D;
//            System.out.println("\\\\192.168.101.141\\6rrt\\BIPM\\6RRT"+ (int)(jday*1000D)+".dat");
//            System.out.println(new File("\\\\192.168.101.141\\6rrt\\BIPM\\6RRT"+ (int)(jday*1000D)+".dat").exists());
//            System.out.println("ftp\\GMSU19"+jday);
//            System.out.println(new File("ftp\\GMSU19"+jday).exists());
//            if ((message.getPayload().toString().contains(String.valueOf(jday))) && (new File("\\\\192.168.101.141\\6rrt\\BIPM\\6RRT"+ (int)(jday*1000D)+".dat").exists())){
//                System.out.println(
//                extFile.mnc(extFile.findCrossing(extFile.parse(new File(localdirectory +"6RRT"+ (int)(jday*1000D)+".dat")),extFile.parse(new File(message.getPayload().toString()))))
//                );
//            }
        };

    }

}
