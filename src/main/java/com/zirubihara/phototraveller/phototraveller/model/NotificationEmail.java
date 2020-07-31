package com.zirubihara.phototraveller.phototraveller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "NOTIFICATION_EMAIL")
public class NotificationEmail {
    @Column(name = "SUBJECT")
    private String subject;

    @Column(name = "RECIPENT")
    private String recipient;

    @Column(name = "BODY")
    private String body;
}
