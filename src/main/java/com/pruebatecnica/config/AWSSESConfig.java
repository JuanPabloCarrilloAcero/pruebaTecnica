package com.pruebatecnica.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import com.pruebatecnica.DTO.EmailRequestDTO;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.internet.MimeBodyPart;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class AWSSESConfig {


    @Value("${aws.secret.key}")
    private String awsSecretKey;

    @Value("${aws.access.key}")
    private String awsAccessKey;

    private AWSCredentials awsCredentials() {
        String AWS_ACCESS_KEY = awsAccessKey;
        String AWS_SECRET_KEY = awsSecretKey;
        AWSCredentials credentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY);
        return credentials;
    }

    private AmazonSimpleEmailService amazonClientBuilder() {
        AmazonSimpleEmailService amazonSimpleEmailService = AmazonSimpleEmailServiceClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials())).withRegion(Regions.US_EAST_1).build();
        return amazonSimpleEmailService;
    }

    public String sendSESEmail(EmailRequestDTO emailRequest, String filePath) {

        try {
            MimeBodyPart att = new MimeBodyPart();
            DataSource fds = new FileDataSource(filePath);
            att.setDataHandler(new DataHandler(fds));
            att.setFileName(fds.getName());

            AmazonSimpleEmailService amazonSimpleEmailService = amazonClientBuilder();
            SendEmailRequest sendEmailRequest = new SendEmailRequest()
                    .withDestination(new Destination().withToAddresses(emailRequest.getTo())
                    )
                    .withMessage(
                            new Message()
                                    .withBody(
                                            new Body().withHtml(new Content().withCharset("UTF-8").withData(att.getContent().toString()))
                                    )
                                    .withSubject(new Content().withCharset("UTF-8").withData(emailRequest.getSubject()))
                    )
                    .withSource(emailRequest.getFrom())
                    ;

            amazonSimpleEmailService.sendEmail(sendEmailRequest);
            return "Email sent successfully";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Email not sent";
        } finally {
            new File(filePath).delete();
        }

    }


}
