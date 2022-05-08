package com.busbooking.Mail.Domain;

import lombok.*;

import javax.persistence.*;

@Entity(name = "mail_registry")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String mailTo;

  private String mailFrom;

  private String mailMessage;
}
