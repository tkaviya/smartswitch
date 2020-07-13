package net.symbiosis.notification.persistence.log;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.symbiosis.common.persistence.entity.enumeration.sym_notification_status;
import net.symbiosis.common.persistence.entity.enumeration.sym_notification_type;
import net.symbiosis.persistence.entity.super_class.sym_entity;

import javax.persistence.*;
import java.util.Date;

/***************************************************************************
 *                                                                         *
 * Created:     11 / 03 / 2020                                             *
 * Author:      Tsungai Kaviya                                             *
 * System:      IntelliJ 2019 / Windows 10                                 *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 *                                                                         *
 ***************************************************************************/

@Entity
@Builder
@Getter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "notification_id"))
public class sym_notification extends sym_entity<sym_notification> {

	@Column(nullable = false)
	private Long sym_user_id;
	@Column(nullable = false)
	private Long auth_user_id;
	@Basic(optional = false)
	private String recipient;
	@ManyToOne
	@JoinColumn(name = "notification_type_id")
	private sym_notification_type notification_type;
	@Basic
	private String remote_reference;
	@ManyToOne
	@JoinColumn(name = "notification_status_id")
	private sym_notification_status notification_status;
	@Basic(optional = false)
	private Date notification_time;
	@Basic(optional = false)
	private String notification;
	@Basic
	private String submit_response;

	public sym_notification(Long sym_user_id, Long auth_user_id, String recipient, sym_notification_type notification_type,
	                        String remote_reference, sym_notification_status notification_status,
	                        Date notification_time, String notification, String submit_response) {
		this.sym_user_id = sym_user_id;
		this.auth_user_id = auth_user_id;
		this.recipient = recipient;
		this.notification_type = notification_type;
		this.remote_reference = remote_reference;
		this.notification_status = notification_status;
		this.notification_time = notification_time;
		this.notification = notification;
		this.submit_response = submit_response;
	}

	public sym_notification setSym_user_id(Long sym_user_id) {
		this.sym_user_id = sym_user_id;
		return this;
	}

	public sym_notification setAuth_user_id(Long auth_user_id) {
		this.auth_user_id = auth_user_id;
		return this;
	}

	public sym_notification setRecipient(String recipient) {
		this.recipient = recipient;
		return this;
	}

	public sym_notification setNotification_type(sym_notification_type notification_type) {
		this.notification_type = notification_type;
		return this;
	}

	public sym_notification setRemote_reference(String remote_reference) {
		this.remote_reference = remote_reference;
		return this;
	}

	public sym_notification setNotification_status(sym_notification_status notification_status) {
		this.notification_status = notification_status;
		return this;
	}

	public sym_notification setNotification_time(Date notification_time) {
		this.notification_time = notification_time;
		return this;
	}

	public sym_notification setNotification(String notification) {
		this.notification = notification;
		return this;
	}

	public sym_notification setSubmit_response(String submit_response) {
		this.submit_response = submit_response;
		return this;
	}
}
